/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.auto.NomadAutoCommandGenerator;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.constants.DriverStationConstants;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;
import frc.robot.auto.Trajectories;
import frc.robot.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.robot.constants.AutoConstants2021;
import frc.robot.constants.DriveConstants2021;
import frc.robot.constants.DriveConstantsKren2021;
import frc.robot.constants.DriverStationConstants2021;
import frc.robot.subsystems.DrivebaseS;

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
    createControllers(driveConstants, driverStationConstants, NomadMappingEnum.TRIGGER_DRIVE);
    createSubsystems();
    Trajectories.createTrajectories(autoConstants.getTrajectoryConfig());
    createCommands();
    configureDefaultCommands();
    init = true;
  }

  /**
   * Creates the constants files for each subsystem.
   */
  private void createConstantsFiles() {
    driveConstants = new DriveConstantsKren2021();
    autoConstants = new AutoConstants2021(driveConstants);
    driverStationConstants = new DriverStationConstants2021();
  }

  /**
   * Creates the subsystems.
   */
  private void createSubsystems() {
    drivebaseS = new DrivebaseS(driveConstants, autoConstants);

  }

  /**
   * Creates the commands that will be started. By creating them once and reusing
   * them, we should save on garbage collection.
   */
  private void createCommands() {
    drivebaseArcadeDriveStickC = new DrivebaseArcadeDriveStickC(drivebaseS, driveConstants);

    ramseteCommand = NomadAutoCommandGenerator.createRamseteCommand(Trajectories.exampleTrajectory,
    drivebaseS, driveConstants, autoConstants);

    ramseteCommandGroup = new InstantCommand(() -> 
    drivebaseS.resetOdometry(Trajectories.exampleTrajectory.getInitialPose()), drivebaseS)
    .andThen(new WaitCommand(2))
    .andThen(ramseteCommand)
    .andThen(new RunCommand(() -> {System.out.println("Stopping trajectory") ; drivebaseS.tankDriveVelocity(0, 0);}, drivebaseS).withTimeout(1));
  }

  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
    drivebaseS.setDefaultCommand(drivebaseArcadeDriveStickC);
  }

  /**
   * Creates the operator console. In actual use, this method would have more constants files for other subsystems.
   * @param driveConstants Drivebase constants to use in the input map creation.
   * @param map The map from NomadInputMaps to select.
   */
  private void createControllers(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadMappingEnum map) {
    Robot2021NomadInputMaps.createMaps(driveConstants, driverStationConstants);
    NomadOperatorConsole.setMap(map);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
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
   * Update the telemetry. This method in RobotContainer is mostly provided for quick testing. Most telemetry should be in subsystems. 
   */
  public void updateTelemetry() {
    if (init) {
      SmartDashboard.putNumber("driveFwdBack",
          NomadOperatorConsole.getRawAxis(driveConstants.getDriveControllerFwdBackAxis()));
      SmartDashboard.putString("Driver Map", NomadOperatorConsole.getSelectedMap().toString());
      drivebaseS.updateTelemetry();
    }
  }



}
