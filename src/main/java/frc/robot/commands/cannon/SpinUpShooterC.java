package frc.robot.commands.cannon;

import java.util.function.DoubleSupplier;

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
  private double rpm = 2550;

  /** Creates a new SpinUpShooterC. */
  public SpinUpShooterC(CannonS cannon, boolean requireCannon) {
    this.cannon = cannon;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  public SpinUpShooterC(CannonS cannon, boolean requireCannon, double rpm){
      this(cannon, requireCannon);
      this.rpm = rpm;
  }

  public SpinUpShooterC(CannonS cannon, boolean requireCannon, DoubleSupplier rpm){
    this(cannon, requireCannon);
    this.rpm = rpm.getAsDouble();
}

  @Override
  public void initialize() {
    System.out.println("Spin up at " + rpm);
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
    return true; //cannon.isShooterAtSpeed();
  }
}
