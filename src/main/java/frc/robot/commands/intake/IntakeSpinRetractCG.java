package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.IntakeS;

public class IntakeSpinRetractCG extends ParallelCommandGroup {

  /** Creates a new IntakeSpinRetractCG. */
  public IntakeSpinRetractCG(IntakeS intake) {
    addCommands(new IntakeRetractC(intake), new IntakeSpinC(intake));
  }
}
