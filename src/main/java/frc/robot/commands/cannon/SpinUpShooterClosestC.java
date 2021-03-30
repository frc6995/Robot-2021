package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel
 */
public class SpinUpShooterClosestC extends CommandBase {
  private CannonS cannon;
  private double rpm = 2300;

  /** Creates a new SpinUpShooterC. */
  public SpinUpShooterClosestC(CannonS cannon, boolean requireCannon) {
    this.cannon = cannon;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  @Override
  public void initialize() {
    cannon.pidShooterToTargetSpeed(rpm);
  }
  
  @Override
  public void execute() {
    //cannon.pidShooterToTargetSpeed(rpm);
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
  }

  @Override
  public boolean isFinished() {
    return cannon.isShooterAtSpeed();
  }
}
