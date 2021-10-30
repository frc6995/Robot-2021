/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.DoubleSupplier;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.lib.auto.NomadAutoCommandGenerator;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.wrappers.limelight.Limelight;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.robot.auto.Trajectories;
import frc.robot.commands.AutoShootAndDriveCG;
import frc.robot.commands.AutoShootAndDriveSequencingCG;
import frc.robot.commands.AutonomousAwardWinnerCG;
import frc.robot.commands.SixBallAutoCG;
import frc.robot.commands.cannon.AimTurretC;
import frc.robot.commands.cannon.SpinUpAndAimC;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.commands.cannon.SpinUpShooterDistanceC;
import frc.robot.commands.cannon.SpinUpShooterMidC;
import frc.robot.commands.cannon.TurretHomeC;
import frc.robot.commands.drivebase.DriveAutoC;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickControllerC;
import frc.robot.commands.intake.IntakeRetractC;
import frc.robot.commands.intake.IntakeSpinWhileHeldC;
import frc.robot.commands.intake.IntakeToggleC;
import frc.robot.commands.othercommands.AgitatorSpinC;
import frc.robot.commands.column.ColumnFeedC;
import frc.robot.commands.column.ColumnLoadC;
import frc.robot.commands.othercommands.ExpelBallsCG;
import frc.robot.commands.othercommands.StoreBallsCG;
import frc.robot.constants.AgitatorConstants2021;
import frc.robot.constants.AutoConstants2021;
import frc.robot.constants.CannonConstants2021;
import frc.robot.constants.ColumnConstants2021;
import frc.robot.constants.DriveConstants2021;
import frc.robot.constants.HoodConstants2021;
import frc.robot.constants.IntakeConstants2021;
import frc.robot.constants.LimelightConstants2021;
import frc.robot.constants.ShooterConstants2021;
import frc.robot.constants.TurretConstants2021;
import frc.robot.constants.interfaces.AgitatorConstants;
import frc.robot.constants.interfaces.CannonConstants;
import frc.robot.constants.interfaces.ColumnConstants;
import frc.robot.constants.interfaces.HoodConstants;
import frc.robot.constants.interfaces.IntakeConstants;
import frc.robot.constants.interfaces.LimelightConstants;
import frc.robot.constants.interfaces.ShooterConstants;
import frc.robot.constants.interfaces.TurretConstants;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
	// Constants Files
	private AutoConstants autoConstants;
	private DriveConstants driveConstants;
	private IntakeConstants intakeConstants;
	private AgitatorConstants agitatorConstants;
	private ColumnConstants columnConstants;
	private CannonConstants cannonConstants;
	private LimelightConstants limelightConstants;
	// Subsystems
	private AgitatorS agitatorS;
	private IntakeS intakeS;
	private ColumnS columnS;
	private CannonS cannonS;
	private LimelightS limelightS;

	// private DifferentialDrive differentialDrive;
	// Commands
	private AgitatorSpinC agitatorSpinC;
	private IntakeToggleC intakeToggleC;
	private StoreBallsCG storeBallsCG;
	private StoreBallsCG searchStoreBallsCG;
	private SpinUpShooterC spinShooterC;

	private DrivebaseArcadeDriveStickControllerC controllerDrive;
	private DriveAutoC driveAutoC;

	// private NomadMappedGenericHID driverController;
	private XboxController controller;
	private XboxController operator;

	// Subsystems
	private DrivebaseS drivebaseS;
	// Commands

	private RamseteCommand ramseteCommand;

	private SequentialCommandGroup ramseteCommandGroup;
	private SequentialCommandGroup bounceCommandGroup;

	private ParallelCommandGroup searchCommandGroup;

	// private NomadMappedGenericHID driverController;

	private boolean init = false;

	private enum SHOOTER_SPEEDS {
		GREEN(3500), YELLOW(2550), BLUE(2250), RED(2275);

		public double value;

		private SHOOTER_SPEEDS(double value) {
			this.value = value;
		}
	}

	private double shooterSetpoint = SHOOTER_SPEEDS.GREEN.value;

	private Spark lights;

	private Trajectory selectedTrajectory;
	private PowerDistributionPanel pdp;
	private DoubleSupplier shooterSpeedSupplier = (DoubleSupplier) () -> {
		return shooterSetpoint;
	};

	private SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * The container for the robot. Contains constant files, controllers,
	 * subsystems, trajectories, commands, and default command bindings, to be
	 * created in that order.
	 */
	public RobotContainer() {
		CameraServer.getInstance().startAutomaticCapture();
		createConstantsFiles();
		createControllers(driveConstants);
		Trajectories.createTrajectories(autoConstants.getTrajectoryConfig());
		selectedTrajectory = Trajectories.searchTrajectoryA;
		createSubsystems();
		createCommands();
		configureDefaultCommands();
		configureButtonBindings();
		lights = new Spark(0);
		lights.set(-0.97);
		init = true;
		pdp = new PowerDistributionPanel();
		// SmartDashboard.putData(pdp);
	}

	/**
	 * Creates the constants files for each subsystem.
	 */
	private void createConstantsFiles() {
		limelightConstants = new LimelightConstants2021();

		agitatorConstants = new AgitatorConstants2021();
		intakeConstants = new IntakeConstants2021();

		driveConstants = new DriveConstants2021();
		autoConstants = new AutoConstants2021(driveConstants);

		columnConstants = new ColumnConstants2021();

		cannonConstants = new CannonConstants2021(new ShooterConstants2021(), new HoodConstants2021(),
				new TurretConstants2021());
	}

	/**
	 * Creates the subsystems.
	 */
	private void createSubsystems() {
		limelightS = new LimelightS(new Limelight("limelight"), limelightConstants);

		NomadTalonSRX left = new NomadTalonSRX(agitatorConstants.getLeftMotorID());
		NomadTalonSRX right = new NomadTalonSRX(agitatorConstants.getRightMotorID(), true);
		agitatorS = new AgitatorS(agitatorConstants, left, right);

		NomadSparkMax intakeMotor = new NomadSparkMax(intakeConstants.getIntakeMotorPort());
		DoubleSolenoid intakeStopper = new DoubleSolenoid(1, intakeConstants.getSolenoidFwdPort(),
				intakeConstants.getSolenoidRevPort());
		intakeS = new IntakeS(intakeConstants, intakeMotor, intakeStopper);

		drivebaseS = new DrivebaseS(driveConstants, autoConstants);

		NomadSparkMax columnMotor = new NomadSparkMax(columnConstants.getTalonID(), MotorType.kBrushed, true);
		NomadTalonSRX acceleratorMotor = new NomadTalonSRX(columnConstants.getAcceleratorID(), true);
		DoubleSolenoid solenoid = new DoubleSolenoid(1, columnConstants.getFwdPort(),
				columnConstants.getRevPort());
		columnS = new ColumnS(columnConstants, columnMotor, acceleratorMotor, solenoid);

		HoodConstants hoodConstants = cannonConstants.getHoodConstants();
		ShooterConstants shooterConstants = cannonConstants.getShooterConstants();
		TurretConstants turretConstants = cannonConstants.getTurretConstants();

		Servo hoodLeftServo = new Servo(hoodConstants.getLeftServoPort());
		Servo hoodRightServo = new Servo(hoodConstants.getRightServoPort());

		NomadSparkMax shooterLeadMotor = new NomadSparkMax(shooterConstants.getLeadMotorID(),
				MotorType.kBrushless, shooterConstants.getLeadMotorInverted());
		NomadSparkMax shooterFollowerMotor = new NomadSparkMax(shooterConstants.getFollowerMotorID(),
				MotorType.kBrushless, shooterConstants.getFollowerMotorInverted(), shooterLeadMotor);

		NomadSparkMax turretMotor = new NomadSparkMax(turretConstants.getSparkMaxPortID(), MotorType.kBrushless,
				turretConstants.getLeadMotorInverted());
		DigitalInput turretLimitSwitch = new DigitalInput(turretConstants.getLimitSwitchChannelID());

		cannonS = new CannonS(cannonConstants, hoodLeftServo, hoodRightServo, shooterLeadMotor, turretMotor,
				turretLimitSwitch);
	}

	/**
	 * Creates the commands that will be started. By creating them once and reusing
	 * them, we should save on garbage collection.
	 */
	private void createCommands() {
		controllerDrive = new DrivebaseArcadeDriveStickControllerC(drivebaseS, driveConstants, controller);

		ramseteCommand = NomadAutoCommandGenerator.createRamseteCommand(Trajectories.lineToTrenchTrajectory,
				drivebaseS, driveConstants, autoConstants);

		ramseteCommandGroup = // ramseteCommand.andThen(new PrintCommand("done"));
				new InstantCommand(
						() -> drivebaseS.resetOdometry(
								Trajectories.lineToTrenchTrajectory.getInitialPose()),
						drivebaseS).andThen(
								NomadAutoCommandGenerator.createRamseteCommand(
										Trajectories.lineToTrenchTrajectory,
										drivebaseS, driveConstants,
										autoConstants))
								.andThen(new WaitCommand(
										3).andThen(NomadAutoCommandGenerator.createRamseteCommand(Trajectories.trenchToLineRevTrajectory, drivebaseS, driveConstants, autoConstants)));

		agitatorSpinC = new AgitatorSpinC(agitatorS);
		intakeToggleC = new IntakeToggleC(intakeS, agitatorS);
		storeBallsCG = new StoreBallsCG(intakeS, agitatorS, columnS);
		searchStoreBallsCG = new StoreBallsCG(intakeS, agitatorS, columnS);
		searchCommandGroup = createRamseteCommandGroup(Trajectories.searchTrajectoryA)
				.alongWith(searchStoreBallsCG);
		driveAutoC = new DriveAutoC(drivebaseS, 1, true);

		spinShooterC = new SpinUpShooterC(cannonS, false);

		chooser.setDefaultOption("Shoot 3 and Move Back", new AutoShootAndDriveCG(drivebaseS, cannonS,
				agitatorS, columnS, intakeS, limelightS, false));
		chooser.addOption("Shoot 3 and Move Fwd", new AutoShootAndDriveCG(drivebaseS, cannonS, agitatorS,
				columnS, intakeS, limelightS, true));
		// chooser.addOption("Shoot Seq and Move Back", new
		// AutoShootAndDriveSequencingCG(drivebaseS, cannonS, agitatorS, columnS,
		// intakeS, limelightS, false));
		// chooser.addOption("Shoot Seq and Move Fwd", new
		// AutoShootAndDriveSequencingCG(drivebaseS, cannonS, agitatorS, columnS,
		// intakeS, limelightS, true));
		chooser.addOption("Shoot 3 grab trench (wip)", ramseteCommandGroup);
		chooser.addOption("6 Ball Trench",
				new SixBallAutoCG(cannonS, limelightS, intakeS, agitatorS, columnS, drivebaseS));
		SmartDashboard.putData("Autonomous", chooser);
	}

	/**
	 * Configures the default Commands for the subsystems.
	 */
	private void configureDefaultCommands() {
		drivebaseS.setDefaultCommand(controllerDrive);
	}

	/**
	 * Creates the operator console.
	 */
	private void createControllers(DriveConstants driveConstants) {
		controller = new XboxController(0);
		operator = new XboxController(1);
	}

	/**
	 * Use this method to define your button->command mappings. Buttons can be
	 * created by instantiating a {@link GenericHID} or one of its subclasses
	 * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
	 * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
	 */
	private void configureButtonBindings() {
		IntakeSpinWhileHeldC intakeSpinWhileHeldC = new IntakeSpinWhileHeldC(intakeS);
		AgitatorSpinC agitatorSpinC = new AgitatorSpinC(agitatorS);
		ColumnLoadC columnLoadC = new ColumnLoadC(columnS);
		StartEndCommand storeSeqC = new StartEndCommand(
				() -> CommandScheduler.getInstance()
						.schedule(intakeSpinWhileHeldC.alongWith(agitatorSpinC)),
				() -> CommandScheduler.getInstance()
						.schedule(new IntakeRetractC(intakeS)
								.alongWith(new AgitatorSpinC(agitatorS).withTimeout(1))
								.alongWith(columnLoadC.withTimeout(2))),
				intakeS);
		// new JoystickButton(operator,
		// XboxController.Button.kA.value).whenPressed(intakeToggleC);
		new JoystickButton(controller, XboxController.Button.kX.value).toggleWhenPressed(storeBallsCG);
		new JoystickButton(controller, XboxController.Button.kA.value).toggleWhenPressed(storeBallsCG);
		// new JoystickButton(controller,
		// XboxController.Button.kA.value).toggleWhenPressed(storeSeqC);
		// new JoystickButton(controller, XboxController.Button.kA.value).whileHeld(new
		// AimTurretC(limelightS, cannonS));

		new JoystickButton(operator, XboxController.Button.kA.value)
				.toggleWhenPressed(new AimTurretC(limelightS, cannonS));
		new JoystickButton(operator, XboxController.Button.kB.value).whileHeld(new TurretHomeC(cannonS));
		new JoystickButton(operator, XboxController.Button.kX.value)
				.toggleWhenPressed(new SpinUpAndAimC(cannonS, limelightS, true));
		new JoystickButton(operator, XboxController.Button.kY.value)
				.whileHeld(new ExpelBallsCG(intakeS, agitatorS, columnS));
		new JoystickButton(operator, XboxController.Button.kStart.value)
				.toggleWhenPressed(new SpinUpShooterMidC(cannonS, true));
		new JoystickButton(operator, XboxController.Button.kBack.value)
				.toggleWhenPressed(new SpinUpShooterDistanceC(cannonS, limelightS, true));

		new JoystickButton(operator, XboxController.Button.kBumperRight.value).whenPressed(() -> {
			cannonS.turret.setSetpoint(cannonS.turret.getTurretEncoderPosition() - 5);
			cannonS.turret.runPID();
		}, cannonS);
		new JoystickButton(operator, XboxController.Button.kBumperLeft.value).whenPressed(() -> {
			cannonS.turret.setSetpoint(cannonS.turret.getTurretEncoderPosition() + 5);
			cannonS.turret.runPID();
		}, cannonS);
		/*
		 * new JoystickButton(operator,
		 * XboxController.Button.kA.value).whenPressed(intakeToggleC); new
		 * JoystickButton(operator,
		 * XboxController.Button.kB.value).whileHeld(agitatorSpinC); new
		 * JoystickButton(operator,
		 * XboxController.Button.kX.value).toggleWhenPressed(storeBallsCG); new
		 * JoystickButton(operator,
		 * XboxController.Button.kBumperLeft.value).whenPressed(new InstantCommand(() ->
		 * cannonS.stopShooter())); new JoystickButton(operator,
		 * XboxController.Button.kBumperRight.value).whenPressed(new
		 * SpinUpShooterC(cannonS,false , shooterSpeedSupplier)); new
		 * JoystickButton(operator, XboxController.Button.kY.value).whileHeld(new
		 * ExpelBallsCG(intakeS, agitatorS, columnS));//new ColumnFeedCG(columnS)); new
		 * JoystickButton(controller,
		 * XboxController.Button.kBumperLeft.value).whenPressed(() ->
		 * {cannonS.turret.setSetpoint(cannonS.turret.getTurretEncoderPosition() + 3);
		 * cannonS.turret.runPID();})//new PrintCommand("Turret left"); new
		 * JoystickButton(controller,
		 * XboxController.Button.kBumperRight.value).whenPressed(() ->
		 * {cannonS.turret.setSetpoint(cannonS.turret.getTurretEncoderPosition() - 3);
		 * cannonS.turret.runPID();}); new POVButton(operator, 0).whenPressed(new
		 * SpinUpShooterC(cannonS, true, SHOOTER_SPEEDS.GREEN.value)); new
		 * POVButton(operator, 90).whenPressed(new SpinUpShooterC(cannonS, true,
		 * SHOOTER_SPEEDS.RED.value)); new POVButton(operator, 180).whenPressed(new
		 * SpinUpShooterC(cannonS, true, SHOOTER_SPEEDS.BLUE.value)); new
		 * POVButton(operator, 270).whenPressed(new SpinUpShooterC(cannonS, true,
		 * SHOOTER_SPEEDS.YELLOW.value));
		 */
	}

	/**
	 * Use this to pass the autonomous command to the main {@link Robot} class.
	 *
	 * @return the command to run in autonomous
	 */
	public Command getAutonomousCommand() {
		// Reset odometry to starting pose of trajectory.

		// Run path following command, then stop at the end.
		return chooser.getSelected();
		// return new InstantCommand();
		// return awardWinnerCG;
	}

	/**
	 * Update the telemetry. This method in RobotContainer is mostly provided for
	 * quick testing. Most telemetry should be in subsystems.
	 */
	public void updateTelemetry() {
		SmartDashboard.putNumber("LimelightDistance", limelightS.getFilteredYOffset());
		SmartDashboard.putNumber("Limelight Filtered Offset", limelightS.getFilteredXOffset());
		// SmartDashboard.putData(pdp);
		// SmartDashboard.putNumber("shooter setpt", shooterSetpoint);
	}

	public void disabledInit() {
		columnS.enableStopper();
		cannonS.stopShooter();
		intakeS.retract();
		cannonS.periodic();
		drivebaseS.setIdleMode(IdleMode.kCoast);
	}

	public void teleopInit() {
		drivebaseS.setIdleMode(IdleMode.kCoast);
		columnS.enableStopper();
		intakeS.retract();
	}

	public void autonomousInit() {
		drivebaseS.setIdleMode(IdleMode.kCoast);
	}

	public SequentialCommandGroup createRamseteCommandGroup(Trajectory trajectory) {
		return new InstantCommand(
				() -> drivebaseS.resetOdometry(selectedTrajectory.getInitialPose()), drivebaseS)
						.andThen(NomadAutoCommandGenerator.createRamseteCommand(trajectory,
								drivebaseS, driveConstants, autoConstants))
						.andThen(() -> {
							System.out.println("Stopping trajectory");
							drivebaseS.tankDriveVolts(0, 0);
						}, drivebaseS);

	}

	public void setShooterSetpoint(double rpm) {
		shooterSetpoint = rpm;
	}
}
