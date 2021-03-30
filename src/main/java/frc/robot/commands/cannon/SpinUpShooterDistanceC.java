package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel
 */
public class SpinUpShooterDistanceC extends CommandBase {
  private CannonS cannon;
  private LimelightS limelight;
  private double rpm = 2800;

  /** Creates a new SpinUpShooterC. */
  public SpinUpShooterDistanceC(CannonS cannon, LimelightS limelight, boolean requireCannon) {
    this.cannon = cannon;
    this.limelight = limelight;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  @Override
  public void initialize() {
    limelight.register();
    double speed = cannon.hood.getAngleBasedOnDistance(limelight.getDistanceToTarget());
    cannon.pidShooterToTargetSpeed(speed);
  }
  
  @Override
  public void execute() {
    //cannon.pidShooterToTargetSpeed(rpm);
    double speed = cannon.hood.getAngleBasedOnDistance(limelight.getDistanceToTarget());
    cannon.pidShooterToTargetSpeed(speed);
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
