package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeS;

public class IntakeSpinC extends CommandBase {
  private IntakeS intake;

  /** Creates a new IntakeSpin. */
  public IntakeSpinC(IntakeS intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {
    intake.runIntake();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {
    intake.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
