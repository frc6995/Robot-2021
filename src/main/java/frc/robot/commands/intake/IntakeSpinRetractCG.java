package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeS;

public class IntakeSpinRetractCG extends SequentialCommandGroup {
  /** Creates a new IntakeSpinRetractCG. */
  public IntakeSpinRetractCG(IntakeS intake) {

    addCommands(new IntakeRetractC(intake), new IntakeSpinC(intake));
  }
}
