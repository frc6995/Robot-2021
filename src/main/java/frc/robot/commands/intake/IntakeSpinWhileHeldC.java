package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeS;

public class IntakeSpinWhileHeldC extends CommandBase {
  private IntakeS intake;

  /** Creates a new IntakeSpinWhileHeld. */
  public IntakeSpinWhileHeldC(IntakeS intake) {
    this.intake = intake;
    addRequirements(intake);

  }

  @Override
  public void initialize() {
    intake.extend();
    intake.setSpeed(intake.getConstants().getIntakeSpeed());
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    intake.retract();
    intake.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
