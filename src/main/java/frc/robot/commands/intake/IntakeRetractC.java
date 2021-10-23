package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.IntakeS;

public class IntakeRetractC extends InstantCommand {

  /** Creates a new IntakeRetractC. */
  private IntakeS intake;
  public IntakeRetractC(IntakeS intake) {
    this.intake = intake;
    addRequirements(intake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    intake.retract();
  }

  @Override
  public void end(boolean interrupted) {}

  
}
