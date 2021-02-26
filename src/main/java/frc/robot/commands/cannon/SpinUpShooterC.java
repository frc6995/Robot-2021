package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel
 */
public class SpinUpShooterC extends CommandBase {
  private CannonS cannon;

  /** Creates a new SpinUpShooterC. */
  public SpinUpShooterC(CannonS cannon, boolean requireCannon) {
    this.cannon = cannon;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  @Override
  public void initialize() {
    cannon.pidShooterToTargetSpeed(0.8);
  }

  @Override
  public boolean isFinished() {
    return cannon.isShooterAtSpeed();
  }
}
