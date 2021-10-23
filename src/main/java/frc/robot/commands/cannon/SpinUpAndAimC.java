package frc.robot.commands.cannon;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.lib.wrappers.limelight.Limelight;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel, sammcdo
 */
public class SpinUpAndAimC extends CommandBase {
  private CannonS cannon;
  private LimelightS limelight;
  private double rpm = 2250;
  private double offset;

  /** Creates a new SpinUpShooterC. */
  public SpinUpAndAimC(CannonS cannon, LimelightS limelight, boolean requireCannon) {
    this.cannon = cannon;
    this.limelight = limelight;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  public SpinUpAndAimC(CannonS cannon, LimelightS limelight, boolean requireCannon, double rpm) {
    this(cannon, limelight, requireCannon);
    this.rpm = rpm;
  }

  public SpinUpAndAimC(CannonS cannon, LimelightS limelight, boolean requireCannon, DoubleSupplier rpm){
    this(cannon, limelight, requireCannon);
    this.rpm = rpm.getAsDouble();
}

  @Override
  public void initialize() {
    cannon.pidShooterToTargetSpeed(rpm);
    limelight.register();
    offset = cannon.turret.getTurretEncoderPosition() - (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1:0));
    cannon.turret.setSetpoint(offset);
    cannon.turret.runPID();
  }

  @Override
  public void execute() {
    offset = cannon.turret.getTurretEncoderPosition() - (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1:0));
    cannon.turret.setSetpoint(offset);
    cannon.turret.runPID();
  }

  @Override
  public void end(boolean interrupted) {
    cannon.stopShooter();
    limelight.deregister();
    super.end(interrupted);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}