package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.constants.DriverStationConstants;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;
import frc.robot.commands.AgitatorSpinC;
import frc.robot.commands.IntakeToggleC;
import frc.robot.subsystems.AgitatorS;
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
  private DriverStationConstants driverStationConstants;
  // Subsystems
  private AgitatorS agitatorS;
  private IntakeS intakeS;
  // Commands
  private AgitatorSpinC agitatorSpinC;
  private IntakeToggleC intakeToggleC;

  // private NomadMappedGenericHID driverController;

  private boolean init = false;

  /**
   * The container for the robot. Contains constant files, controllers, subsystems, trajectories, commands,
   * and default command bindings, to be created in that order.
   */
  public RobotContainer() {
    createConstantsFiles();
    createControllers(driveConstants, driverStationConstants, NomadMappingEnum.DEFAULT_DRIVE);
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
  }

  /**
   * Creates the subsystems.
   */
  private void createSubsystems() {
    agitatorS = new AgitatorS();
    intakeS = new IntakeS();
  }

  /**
   * Creates the commands that will be started. By creating them once and reusing
   * them, we should save on garbage collection.
   */
  private void createCommands() {
    agitatorSpinC = new AgitatorSpinC(agitatorS);
    intakeToggleC = new IntakeToggleC(intakeS);
  }

  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
    //drivebaseS.setDefaultCommand(drivebaseArcadeDriveStickC);
    // TODO remove
    //agitatorS.setDefaultCommand(agitatorSpinC);
  }

  /**
   * Creates the operator console. In actual use, this method would have more constants files for other subsystems.
   * @param driveConstants Drivebase constants to use in the input map creation.
   * @param map The map from NomadInputMaps to select.
   */
  private void createControllers(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadMappingEnum map) {
    Robot2021NomadInputMaps.createMaps();
    NomadOperatorConsole.setMap(map);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    NomadOperatorConsole.getButton(001).whileHeld(agitatorSpinC);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    //RamseteCommand ramseteCommand = NomadAutoCommandGenerator.createRamseteCommand(Trajectories.exampleTrajectory,
    //    drivebaseS, driveConstants, autoConstants);
    // Reset odometry to starting pose of trajectory.
    //drivebaseS.resetOdometry(Trajectories.exampleTrajectory.getInitialPose());

    // Run path following command, then stop at the end.
    //return ramseteCommand.andThen(() -> drivebaseS.tankDriveVolts(0, 0));
    return new InstantCommand();
  }
  /**
   * Update the telemetry. This method in RobotContainer is mostly provided for quick testing. Most telemetry should be in subsystems. 
   */
  public void updateTelemetry() {
    if (init) {
      SmartDashboard.putNumber("driveFwdBack",
          NomadOperatorConsole.getRawAxis(driveConstants.getDriveControllerFwdBackAxis()));
      SmartDashboard.putString("Driver Map", NomadOperatorConsole.getSelectedMap().toString());
    }
  }



}