package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel
 */
public class SpinUpShooterMidC extends CommandBase {
  private CannonS cannon;
  private double rpm = 2300;

  /** Creates a new SpinUpShooterC. */
  public SpinUpShooterMidC(CannonS cannon, boolean requireCannon) {
    SmartDashboard.putNumber("New Speed", 2450);
    this.cannon = cannon;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  @Override
  public void initialize() {
    rpm = SmartDashboard.getNumber("New Speed", 2450);
    cannon.pidShooterToTargetSpeed(rpm);
  }
  
  @Override
  public void execute() {
    rpm = SmartDashboard.getNumber("New Speed", 2450);
    cannon.pidShooterToTargetSpeed(rpm);
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
