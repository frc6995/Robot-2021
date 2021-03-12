/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.auto.NomadAutoCommandGenerator;
import frc.lib.constants.AgitatorConstants;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.ColumnConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.constants.DriverStationConstants;
import frc.lib.constants.IntakeConstants;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;
import frc.robot.auto.Trajectories;
import frc.robot.commands.AutonomousAwardWinnerCG;
import frc.robot.commands.cannon.SpinUpShooterC;
import frc.robot.commands.cannon.TurretMotionTester;
import frc.robot.commands.drivebase.DriveAutoC;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickControllerC;
import frc.robot.commands.intakecommands.IntakeToggleC;
import frc.robot.commands.othercommands.AgitatorSpinC;
import frc.robot.commands.othercommands.ColumnFeedC;
import frc.robot.commands.othercommands.ExpellBallsCG;
import frc.robot.commands.othercommands.StoreBallsCG;
import frc.robot.constants.AgitatorConstantsKRen;
import frc.robot.constants.AutoConstants2021;
import frc.robot.constants.CannonConstants2021;
import frc.robot.constants.ColumnConstantsKRen;
import frc.robot.constants.DriveConstants2021;
import frc.robot.constants.DriverStationConstants2021;
import frc.robot.constants.HoodConstants2021;
import frc.robot.constants.IntakeConstantsKRen;
import frc.robot.constants.ShooterConstants2021;
import frc.robot.constants.TurretConstants2021;
import frc.robot.constants.interfaces.CannonConstants;
import frc.robot.constants.interfaces.HoodConstants;
import frc.robot.constants.interfaces.ShooterConstants;
import frc.robot.constants.interfaces.TurretConstants;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;
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
  private CannonConstants cannonConstants;
  // Subsystems
  private AgitatorS agitatorS;
  private IntakeS intakeS;
  private ColumnS columnS;
  private CannonS cannonS;
  // Commands
  private AgitatorSpinC agitatorSpinC;
  private IntakeToggleC intakeToggleC;
  private StoreBallsCG storeBallsCG;
  private ColumnFeedC columnFeedC;
  private DrivebaseArcadeDriveStickControllerC controllerDrive;
  private DriveAutoC driveAutoC;
  private ParallelRaceGroup driveAutoTimeoutCG;

  private AutonomousAwardWinnerCG awardWinnerCG;

  // private NomadMappedGenericHID driverController;
  private XboxController controller;

  // Subsystems
  private DrivebaseS drivebaseS;
  // Commands
  private DrivebaseArcadeDriveStickC drivebaseArcadeDriveStickC;

  private RamseteCommand ramseteCommand;

  private SequentialCommandGroup ramseteCommandGroup;


  // private NomadMappedGenericHID driverController;

  private boolean init = false;

  /**
   * The container for the robot. Contains constant files, controllers, subsystems, trajectories, commands,
   * and default command bindings, to be created in that order.
   */
  public RobotContainer() {
    createConstantsFiles();
    //createControllers(driveConstants, driverStationConstants, NomadMappingEnum.DEFAULT_DRIVE);
    //createControllers(driveConstants, driverStationConstants, NomadMappingEnum.TRIGGER_DRIVE);
    //Trajectories.createTrajectories(autoConstants.getTrajectoryConfig());
    createSubsystems();
    //createCommands();
    //configureDefaultCommands();
    //configureButtonBindings();
    init = true;
  }

  /**
   * Creates the constants files for each subsystem.
   */
  private void createConstantsFiles() {
    agitatorConstants = new AgitatorConstantsKRen();
    intakeConstants = new IntakeConstantsKRen();
    
    driveConstants = new DriveConstants2021();
    autoConstants = new AutoConstants2021(driveConstants);
    driverStationConstants = new DriverStationConstants2021();

    columnConstants = new ColumnConstantsKRen();

    cannonConstants = new CannonConstants2021(new ShooterConstants2021(), new HoodConstants2021(),
        new TurretConstants2021());
  }

  /**
   * Creates the subsystems.
   */
  private void createSubsystems() {
    NomadTalonSRX left = new NomadTalonSRX(agitatorConstants.getLeftMotorID());
    NomadTalonSRX right = new NomadTalonSRX(agitatorConstants.getRightMotorID(), true);
    agitatorS = new AgitatorS(agitatorConstants, left, right);

    // NomadSparkMax intakeMotor = new NomadSparkMax(intakeConstants.getIntakeMotorPort());
    // DoubleSolenoid intakeStopper = new DoubleSolenoid(1, intakeConstants.getSolenoidFwdPort(), intakeConstants.getSolenoidRevPort());
    // intakeS = new IntakeS(intakeConstants, intakeMotor, intakeStopper);
    
    drivebaseS = new DrivebaseS(driveConstants, autoConstants);

    // NomadSparkMax front = new NomadSparkMax(columnConstants.getFrontMotorID(), MotorType.kBrushed, true);
    // NomadTalonSRX back = new NomadTalonSRX(columnConstants.getBackMotorID(), true);
    // DoubleSolenoid solenoid = new DoubleSolenoid(1, columnConstants.getFwdPort(), columnConstants.getRevPort());
    //columnS = new ColumnS(columnConstants, front, back, solenoid);

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
    
    SmartDashboard.putData(new TurretMotionTester(turretMotor, 90.25));
    
    cannonS = new CannonS(cannonConstants, hoodLeftServo, hoodRightServo, shooterLeadMotor, turretMotor, turretLimitSwitch);
  }

  /**
   * Creates the commands that will be started. By creating them once and reusing
   * them, we should save on garbage collection.
   */
  private void createCommands() {
    drivebaseArcadeDriveStickC = new DrivebaseArcadeDriveStickC(drivebaseS, driveConstants);
    controllerDrive = new DrivebaseArcadeDriveStickControllerC(drivebaseS, driveConstants, controller);

    ramseteCommand = NomadAutoCommandGenerator.createRamseteCommand(Trajectories.exampleTrajectory,
    drivebaseS, driveConstants, autoConstants);

    
    ramseteCommandGroup = new InstantCommand(() -> 
    drivebaseS.resetOdometry(Trajectories.exampleTrajectory.getInitialPose()), drivebaseS)
    .andThen(new WaitCommand(0.2))
    .andThen(ramseteCommand)
    .andThen(() -> {System.out.println("Stopping trajectory") ; drivebaseS.tankDriveVolts(0, 0);}, drivebaseS);

    agitatorSpinC = new AgitatorSpinC(agitatorS);
    intakeToggleC = new IntakeToggleC(intakeS, agitatorS);
    columnFeedC = new ColumnFeedC(columnS);
    storeBallsCG = new StoreBallsCG(intakeS, agitatorS, columnS);

    driveAutoC = new DriveAutoC(drivebaseS, 1, true);
    driveAutoTimeoutCG = driveAutoC.withTimeout(1);
    awardWinnerCG = new AutonomousAwardWinnerCG(drivebaseS, cannonS, agitatorS, columnS, intakeS);

    SmartDashboard.putData(awardWinnerCG);
    SpinUpShooterC spinShooterC = new SpinUpShooterC(cannonS, false);
    SmartDashboard.putData(spinShooterC);
    SmartDashboard.putBoolean("Shooter Ready", cannonS.isShooterAtSpeed());
  }

  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
    drivebaseS.setDefaultCommand(controllerDrive);
  }

  /**
   * Creates the operator console. In actual use, this method would have more constants files for other subsystems.
   * @param driveConstants Drivebase constants to use in the input map creation.
   * @param map The map from NomadInputMaps to select.
   */
  private void createControllers(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadMappingEnum map) {
    //Robot2021NomadInputMaps.createMaps(driveConstants, driverStationConstants);
    //NomadOperatorConsole.setMap(map);
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
    new JoystickButton(controller, 4).whileHeld(new ExpellBallsCG(intakeS, agitatorS, columnS));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // Reset odometry to starting pose of trajectory.

    // Run path following command, then stop at the end.
    //return ramseteCommandGroup;
    return awardWinnerCG;
  }
  /**
   * Update the telemetry. This method in RobotContainer is mostly provided for quick testing. Most telemetry should be in subsystems. 
   */
  public void updateTelemetry() {
    //if (init) {
     // SmartDashboard.putNumber("driveFwdBack",
        //  NomadOperatorConsole.getRawAxis(driveConstants.getDriveControllerFwdBackAxis()));
      //SmartDashboard.putString("Driver Map", NomadOperatorConsole.getSelectedMap().toString());
    //}
  }

public void disabledInit() {
  //columnS.enableStopper();
//  cannonS.stopShooter();
}

}
