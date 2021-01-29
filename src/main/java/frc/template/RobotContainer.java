/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.template;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;
import frc.template.commands.AgitatorSpinC;
import frc.template.commands.drivebase.DrivebaseArcadeDriveStickC;
import frc.template.constants.AutoConstantsKRen;
import frc.template.constants.DriveConstantsKRen;
import frc.robot.subsystems.AgitatorS;
import frc.template.subsystems.DifferentialDrivebaseTalonVictorS;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Constants Files
  private AutoConstants autoConstants;
  private DriveConstants driveConstants;
  //Subsystems
  private DifferentialDrivebaseTalonVictorS drivebaseS;
  private AgitatorS agitatorS;
  //Commands
  private DrivebaseArcadeDriveStickC drivebaseArcadeDriveStickC;
  private AgitatorSpinC agitatorSpinC;

  //private NomadMappedGenericHID driverController;

  private boolean init = false;
  /**
   * The container for the robot.  Contains constant files, subsystems, commands, controller profiles, and controllers, to be created in that order.
   */
  public RobotContainer() {
    createConstantsFiles();
    createControllers();
    createSubsystems();
    createCommands();
    configureButtonBindings();
    configureDefaultCommands();
    init = true;
  }

  /**
   * Creates the constants files for each subsystem.
   */
  private void createConstantsFiles() {
    driveConstants = new DriveConstantsKRen();
    autoConstants = new AutoConstantsKRen(driveConstants);
  }
  /**
   * Creates the subsystem.
   */
  private void createSubsystems() {
    //drivebaseS = new DifferentialDrivebaseTalonVictorS(driveConstants, autoConstants);
    agitatorS = new AgitatorS();
  }
  /**
   * Creates the commands that will be started. By creating them once and reusing them, we should save on garbage collection.
   */
  private void createCommands() {
    //drivebaseArcadeDriveStickC = new DrivebaseArcadeDriveStickC(drivebaseS, driveConstants);
    agitatorSpinC = new AgitatorSpinC(agitatorS);
  }
  /**
   * Configures the default Commands for the subsystems.
   */
  private void configureDefaultCommands() {
    //drivebaseS.setDefaultCommand(drivebaseArcadeDriveStickC);
    //agitatorS.setDefaultCommand(agitatorSpinC);
  }
  /**
   * Creates the user controllers.
   */
  private void createControllers() {
    NomadOperatorConsole.init();
    NomadInputMaps.createMaps(driveConstants);
    NomadOperatorConsole.setMap(NomadMappingEnum.DEFAULT_DRIVE);
    /*driverController = new NomadMappedGenericHID(DriverStationConstants.DRIVER_CONTROLLER_USB_PORT);
    NomadInputMaps.createMaps(driverController, driveConstants);
    selectedMap = DriverStationConstants.DRIVER_CONTROLLER_MAP;*/
    //driverController.setMap(DriverStationConstants.DRIVER_CONTROLLER_MAP);
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
    // An ExampleCommand will run in autonomous
    return drivebaseArcadeDriveStickC;
  }

  public void updateTelemetry(){
    if(init) {
      SmartDashboard.putNumber("driveFwdBack", NomadOperatorConsole.getRawAxis(driveConstants.getDriveControllerFwdBackAxis()));
      SmartDashboard.putString("Driver Map", NomadOperatorConsole.getSelectedMap().toString());
      SmartDashboard.putData(agitatorSpinC);
    }
  }

}
