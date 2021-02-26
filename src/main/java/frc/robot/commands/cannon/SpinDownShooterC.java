package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that stops the Shooter using PID.
 * 
 * @author JoeyFabel
 */
public class SpinDownShooterC extends InstantCommand {
  private CannonS cannon;

  /** Creates a new SpinDownShooterC. */
  public SpinDownShooterC(CannonS cannon, boolean requireCannon) {
    this.cannon = cannon;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  @Override
  public void initialize() {
    cannon.spinDownShooter();
  }
}
