package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.constants.DriveConstants;
import frc.lib.subsystems.DifferentialDrivebaseS;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;

public class DrivebaseArcadeDriveStickC extends CommandBase {
  DifferentialDrivebaseS drivebaseS;
  DriveConstants driveConstants;

  /**
   * Creates a new DrivebaseArcadeDriveStick.
   */
  public DrivebaseArcadeDriveStickC(DifferentialDrivebaseS drivebase, DriveConstants driveConstants) {
    drivebaseS = drivebase;
    addRequirements(drivebaseS);
    this.driveConstants = driveConstants;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //A compounded function: processOutputs(calculateOutputs(getInputs())). Defaults to the left and right Talons in DrivebaseS
    drivebaseS.drivePercentages(
      drivebaseS.arcadeDriveController(
        NomadOperatorConsole.getRawAxis(driveConstants.getDriveControllerFwdBackAxis()), 
        NomadOperatorConsole.getRawAxis(driveConstants.getDriveControllerLeftRightAxis())
      ).clamp()
    );
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
