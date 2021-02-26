package frc.robot;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.auto.NomadAutoCommandGenerator;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;
import frc.lib.wrappers.limelight.Limelight;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;
import frc.robot.auto.Trajectories;
import frc.robot.commands.agitator.AgitatorSpinC;
import frc.robot.commands.cannon.AimHoodC;
import frc.robot.commands.cannon.AimTurretC;
import frc.robot.commands.cannon.FindTargetC;
import frc.robot.commands.cannon.LaunchBallC;
import frc.robot.commands.cannon.SpinDownShooterC;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.commands.column.ColumnFeedC;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickControllerC;
import frc.robot.commands.intake.IntakeToggleC;
import frc.robot.commands.othercommands.ExpelBallsCG;
import frc.robot.commands.othercommands.StoreBallsCG;
import frc.robot.constants.AgitatorConstants2021;
import frc.robot.constants.AutoConstants2021;
import frc.robot.constants.CannonConstants2021;
import frc.robot.constants.ColumnConstants2021;
import frc.robot.constants.DriveConstants2021;
import frc.robot.constants.DriverStationConstants2021;
import frc.robot.constants.HoodConstants2021;
import frc.robot.constants.IntakeConstants2021;
import frc.robot.constants.LimelightConstants2021;
import frc.robot.constants.ShooterConstants2021;
import frc.robot.constants.TurretConstants2021;
import frc.robot.constants.interfaces.AgitatorConstants;
import frc.robot.constants.interfaces.CannonConstants;
import frc.robot.constants.interfaces.ColumnConstants;
import frc.robot.constants.interfaces.DriverStationConstants;
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
  private DriverStationConstants driverStationConstants;
  private IntakeConstants intakeConstants;
  private AgitatorConstants agitatorConstants;
  private ColumnConstants columnConstants;
  private LimelightConstants limelightConstants;

  private ShooterConstants shooterConstants;
  private HoodConstants hoodConstants;
  private TurretConstants turretConstants;
  private CannonConstants cannonConstants;

  private AgitatorS agitatorS;
  private IntakeS intakeS;
  private ColumnS columnS;
  private DrivebaseS drivebaseS;
  private LimelightS limelightS;
  private CannonS cannonS;

  private AgitatorSpinC agitatorSpinC;
  private IntakeToggleC intakeToggleC;
  private StoreBallsCG storeBallsCG;
  private ColumnFeedC columnFeedC;
  private DrivebaseArcadeDriveStickControllerC controllerDrive;
  private DrivebaseArcadeDriveStickC drivebaseArcadeDriveStickC;
  private RamseteCommand ramseteCommand;
  private SequentialCommandGroup ramseteCommandGroup;
  private SequentialCommandGroup aimCannonCG;
  private SequentialCommandGroup shootCannonCG;
  private WaitUntilCommand shooterWaitUntilReadyC;

  private XboxController controller;

  private boolean init = false;

  /**
   * The container for the robot. Contains constant files, controllers,
   * subsystems, trajectories, commands, and default command bindings, to be
   * created in that order.
   */
  public RobotContainer() {
    createConstantsFiles();
    createControllers(driveConstants, driverStationConstants, NomadMappingEnum.TRIGGER_DRIVE);
    Trajectories.createTrajectories(autoConstants.getTrajectoryConfig());
    createSubsystems();
    createCommands();
    configureDefaultCommands();
    configureButtonBindings();
    init = true;
  }

  /**
   * Creates the constants files for each subsystem.
   */
  private void createConstantsFiles() {
    agitatorConstants = new AgitatorConstants2021();
    intakeConstants = new IntakeConstants2021();

    driveConstants = new DriveConstants2021();
    autoConstants = new AutoConstants2021(driveConstants);
    driverStationConstants = new DriverStationConstants2021();

    limelightConstants = new LimelightConstants2021();

    columnConstants = new ColumnConstants2021();

    cannonConstants = new CannonConstants2021(new ShooterConstants2021(), new HoodConstants2021(),
        new TurretConstants2021());
  }

  /**
   * Creates the subsystems.
   */
  private void createSubsystems() {

    NomadSparkMax drivebaseLeftLeader = new NomadSparkMax(driveConstants.getCanIDLeftDriveMaster(),
        MotorType.kBrushless, driveConstants.getLeftDriveLeaderInverted());

    NomadSparkMax drivebaseRightLeader = new NomadSparkMax(driveConstants.getCanIDRightDriveMaster(),
        MotorType.kBrushless, driveConstants.getRightDriveLeaderInverted());

    NomadSparkMax drivebaseLeftFollower = new NomadSparkMax(driveConstants.getCanIDLeftDriveFollower(),
        MotorType.kBrushless, driveConstants.getLeftDriveFollowerInverted(), drivebaseLeftLeader);

    NomadSparkMax drivebaseRightFollower = new NomadSparkMax(driveConstants.getCanIDRightDriveFollower(),
        MotorType.kBrushless, driveConstants.getRightDriveFollowerInverted(), drivebaseRightLeader);

    Encoder drivebaseLeftEncoder = new Encoder(driveConstants.getLeftEncoderPorts()[0],
        driveConstants.getLeftEncoderPorts()[1], driveConstants.getLeftEncoderReversed());

    Encoder drivebaseRightEncoder = new Encoder(driveConstants.getRightEncoderPorts()[0],
        driveConstants.getRightEncoderPorts()[1], driveConstants.getRightEncoderReversed());

    AHRS gyro = new AHRS(SPI.Port.kMXP);

    drivebaseS = new DrivebaseS(driveConstants, autoConstants, drivebaseLeftLeader, drivebaseRightLeader,
        drivebaseLeftFollower, drivebaseRightFollower, drivebaseLeftEncoder, drivebaseRightEncoder, gyro);

    NomadVictorSPX agitatorLeft = new NomadVictorSPX(agitatorConstants.getLeftMotorID());
    NomadVictorSPX agitatorRight = new NomadVictorSPX(agitatorConstants.getRightMotorID());
    agitatorS = new AgitatorS(agitatorConstants, agitatorLeft, agitatorRight);

    NomadSparkMax intakeMotor = new NomadSparkMax(intakeConstants.getIntakeMotorPort());
    DoubleSolenoid intakeStopper = new DoubleSolenoid(1, intakeConstants.getSolenoidFwdPort(),
        intakeConstants.getSolenoidRevPort());
    intakeS = new IntakeS(intakeConstants, intakeMotor, intakeStopper);

    NomadTalonSRX columnFront = new NomadTalonSRX(columnConstants.getTalonID());
    NomadSparkMax columnAccelerator = new NomadSparkMax(columnConstants.getAcceleratorID(), MotorType.kBrushed);
    DoubleSolenoid solenoid = new DoubleSolenoid(1, columnConstants.getFwdPort(), columnConstants.getRevPort());
    columnS = new ColumnS(columnConstants, columnFront, columnAccelerator, solenoid);

    Limelight limelight = new Limelight("limelight");

    limelightS = new LimelightS(limelight, limelightConstants);

    HoodConstants hoodConstants = cannonConstants.getHoodConstants();
    ShooterConstants shooterConstants = cannonConstants.getShooterConstants();
    TurretConstants turretConstants = cannonConstants.getTurretConstants();

    Servo hoodLeftServo = new Servo(hoodConstants.getLeftServoPort());
    Servo hoodRightServo = new Servo(hoodConstants.getRightServoPort());

    NomadSparkMax shooterLeadMotor = new NomadSparkMax(shooterConstants.getLeadMotorID(), MotorType.kBrushless,
        shooterConstants.getLeadMotorInverted());
    NomadSparkMax shooterFollowerMotor = new NomadSparkMax(shooterConstants.getFollowerMotorID(), MotorType.kBrushless,
        shooterConstants.getFollowerMotorInverted(), shooterLeadMotor);

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
    drivebaseArcadeDriveStickC = new DrivebaseArcadeDriveStickC(drivebaseS, driveConstants);
    controllerDrive = new DrivebaseArcadeDriveStickControllerC(drivebaseS, driveConstants, controller);

    ramseteCommand = NomadAutoCommandGenerator.createRamseteCommand(Trajectories.exampleTrajectory, drivebaseS,
        driveConstants, autoConstants);

    ramseteCommandGroup = new SequentialCommandGroup(
        new InstantCommand(() -> drivebaseS.resetOdometry(Trajectories.exampleTrajectory.getInitialPose()), drivebaseS),
        new WaitCommand(0.2), ramseteCommand, new InstantCommand(() -> {
          System.out.println("Stopping trajectory");
          drivebaseS.tankDriveVolts(0, 0);
        }, drivebaseS));

    agitatorSpinC = new AgitatorSpinC(agitatorS);
    intakeToggleC = new IntakeToggleC(intakeS, agitatorS);
    columnFeedC = new ColumnFeedC(columnS);
    storeBallsCG = new StoreBallsCG(intakeS, agitatorS, columnS);

    shooterWaitUntilReadyC = new WaitUntilCommand(() -> cannonS.isShooterAtSpeed());
    // AimCannon Command Group - Update nulls with hardware once created
    /**
     * A command group that finds the target, aims the hood, aims the turret, and
     * preps the shooter for launch.
     */
    aimCannonCG = new SequentialCommandGroup(new FindTargetC(limelightS),
        new ParallelCommandGroup(new AimHoodC(limelightS, cannonS, false), new AimTurretC(limelightS, cannonS, false),
            new SpinUpShooterC(cannonS, false)));
    aimCannonCG.addRequirements(cannonS);
    // Shoot Cannon
    shootCannonCG = new SequentialCommandGroup(new SpinUpShooterC(cannonS, false), shooterWaitUntilReadyC,
        new LaunchBallC(cannonS, false), new SpinDownShooterC(cannonS, false));
    shootCannonCG.addRequirements(cannonS);
  }

  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
    drivebaseS.setDefaultCommand(controllerDrive);
  }

  /**
   * Creates the operator console. In actual use, this method would have more
   * constants files for other subsystems.
   * 
   * @param driveConstants Drivebase constants to use in the input map creation.
   * @param map            The map from NomadInputMaps to select.
   */
  private void createControllers(DriveConstants driveConstants, DriverStationConstants driverStationConstants,
      NomadMappingEnum map) {
    controller = new XboxController(0);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    new JoystickButton(controller, 1).whenPressed(intakeToggleC);
    new JoystickButton(controller, 2).whileHeld(agitatorSpinC);
    new JoystickButton(controller, 3).toggleWhenPressed(storeBallsCG);
    new JoystickButton(controller, 4).whileHeld(new ExpelBallsCG(intakeS, agitatorS, columnS));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Reset odometry to starting pose of trajectory.
    // Run path following command, then stop at the end.
    return ramseteCommandGroup;
  }

  /**
   * Update the telemetry. This method in RobotContainer is mostly provided for
   * quick testing. Most telemetry should be in subsystems.
   */
  public void updateTelemetry() {
  }

}
