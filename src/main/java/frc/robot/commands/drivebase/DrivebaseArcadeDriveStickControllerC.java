package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.constants.DriveConstants;
import frc.lib.subsystems.DifferentialDrivebaseS;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;

public class DrivebaseArcadeDriveStickControllerC extends CommandBase {
  DifferentialDrivebaseS drivebaseS;
  DriveConstants driveConstants;
  GenericHID controller;
  
  /**
   * Creates a new DrivebaseArcadeDriveStick.
   */
  public DrivebaseArcadeDriveStickControllerC(DifferentialDrivebaseS drivebase, DriveConstants driveConstants, GenericHID controller) {
    drivebaseS = drivebase;
    addRequirements(drivebaseS);
    this.driveConstants = driveConstants;
    this.controller = controller;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    double rightTrigger = controller.getRawAxis(2);
    double leftTrigger = controller.getRawAxis(3);

    if (rightTrigger > -0.02 && rightTrigger < 0.02) rightTrigger = 0;
    if (leftTrigger > -0.02 && leftTrigger < 0.02) leftTrigger = 0;

    double driveSpeed = rightTrigger-leftTrigger;
    double turnSpeed = controller.getRawAxis(0);

    if (turnSpeed > -0.02 && turnSpeed < 0.02) turnSpeed = 0;

    //A compounded function: processOutputs(calculateOutputs(getInputs())). Defaults to the left and right Talons in DrivebaseS
    drivebaseS.drivePercentages(
      drivebaseS.arcadeDriveController(driveSpeed*0.4, turnSpeed*-0.25).clamp()
    );
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
