/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.DriveConstants;
import frc.robot.subsystems.DrivebaseS;
import frc.robot.subsystems.DrivebaseTalonVictorS;
import frc.robot.wrappers.inputdevices.NomadMappedGenericHID;

public class DrivebaseArcadeDriveStickC extends CommandBase {
  DrivebaseTalonVictorS drivebaseS;
  NomadMappedGenericHID driveStick;
  DriveConstants driveConstants;
  /**
   * Creates a new DrivebaseArcadeDriveStick.
   */
  public DrivebaseArcadeDriveStickC(DrivebaseTalonVictorS drivebase, NomadMappedGenericHID stick, DriveConstants driveConstants) {
    drivebaseS = drivebase;
    addRequirements(drivebaseS);
    driveStick = stick;
    this.driveConstants = driveConstants;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    //A compounded function: processOutputs(calculateOutputs(getInputs())). Defaults to the left and right Talons in DrivebaseS
    drivebaseS.drivePercentages(
      drivebaseS.arcadeDriveController(
        driveStick.getRawAxis(driveConstants.getDriveControllerFwdBackAxis()), 
        driveStick.getRawAxis(driveConstants.getDriveControllerLeftRightAxis())
      ).clamp()
    );
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
