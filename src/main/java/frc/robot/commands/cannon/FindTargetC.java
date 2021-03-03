package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;

/** A command that uses the Limelight to find the target.
 * 
 * @author JoeyFabel
 */
public class FindTargetC extends CommandBase {
  private LimelightS limelight;
  
  /** Creates a new FindTargetC. */
  public FindTargetC(LimelightS limelight) {

    this.limelight = limelight;
  }

  @Override
  public void initialize() {
    limelight.register();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    limelight.deregister();
  }

  @Override
  public boolean isFinished() {
    return limelight.isTargetFound();
  }
}
