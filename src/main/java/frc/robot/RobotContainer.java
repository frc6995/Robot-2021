/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.robot.commands.drivebase.DriveAutoC;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickControllerC;
import frc.robot.constants.AutoConstants2021;
import frc.robot.constants.DriveConstants2021;
import frc.robot.constants.IntakeConstants2021;
import frc.robot.constants.interfaces.IntakeConstants;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.IntakeS;

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

  // Subsystems
  private IntakeS intakeS;
  private DrivebaseS drivebaseS;

  // Commands
  private DrivebaseArcadeDriveStickControllerC controllerDrive;
  private DriveAutoC driveAutoC;

  //Controllers
  private XboxController controller;
  private XboxController operator;

  private boolean init = false;

  private Spark lights;

  private Trajectory selectedTrajectory;
  private PowerDistributionPanel pdp;

  /**
   * The container for the robot. Contains constant files, controllers,
   * subsystems, trajectories, commands, and default command bindings, to be
   * created in that order.
   */
  public RobotContainer() {
    createConstantsFiles();
    createControllers(driveConstants);
    createSubsystems();
    createCommands();
    configureDefaultCommands();
    configureButtonBindings();
    lights = new Spark(0);
    lights.set(-0.97);
    init = true;
    pdp = new PowerDistributionPanel();
    //SmartDashboard.putData(pdp);
  }

  /**
   * Creates the constants files for each subsystem.
   */
  private void createConstantsFiles() {
    intakeConstants = new IntakeConstants2021();

    driveConstants = new DriveConstants2021();
    autoConstants = new AutoConstants2021(driveConstants);
  }

  /**
   * Creates the subsystems.
   */
  private void createSubsystems() {
    intakeS = new IntakeS(intakeConstants);

    drivebaseS = new DrivebaseS(driveConstants, autoConstants);
  }

  /**
   * Creates the commands that will be started. By creating them once and reusing
   * them, we should save on garbage collection.
   */
  private void createCommands() {
    controllerDrive = new DrivebaseArcadeDriveStickControllerC(drivebaseS, driveConstants, controller);

    //intakeToggleC = new IntakeSpinWhileHeldC(intakeS);
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
    //new JoystickButton(controller, XboxController.Button.kA.value).whenPressed(intakeToggleC);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new InstantCommand();
  }

  /**
   * Update the telemetry. This method in RobotContainer is mostly provided for
   * quick testing. Most telemetry should be in subsystems.
   */
  public void updateTelemetry() {
  }

  public void disabledInit() {
    //intakeS.retract();
    drivebaseS.setIdleMode(IdleMode.kCoast);
  }

  public void teleopInit() {
    drivebaseS.setIdleMode(IdleMode.kCoast);
  }

  public void autonomousInit() {
    drivebaseS.setIdleMode(IdleMode.kCoast);
  }
}
