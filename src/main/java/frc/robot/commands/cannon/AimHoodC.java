package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that aims the hood.
 * 
 * @author JoeyFabel
 */
public class AimHoodC extends CommandBase {
  private CannonS cannon;
  private LimelightS limelight;

  /** Creates a new AimHoodC. */
  public AimHoodC(LimelightS limelight, CannonS cannon, boolean requireCannon) {

    this.cannon = cannon;
    this.limelight = limelight;
    if (requireCannon) {
      addRequirements(cannon);
    }
  }

  public AimHoodC(LimelightS limelight, CannonS cannon) {
    this(limelight, cannon, true);
  }

  @Override
  public void initialize() {
    // should the cannon do this in execute, or is once enough?
    cannon.moveHoodToDesiredAngle(limelight.getDistanceToTarget());
    limelight.register();
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    limelight.deregister();
  }

  @Override
  public boolean isFinished() {
    return cannon.isHoodAtSetpoint();
  }
}
