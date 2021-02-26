package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.IntakeS;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeSpinRetractCG extends SequentialCommandGroup {
  /** Creates a new IntakeSpinRetractCG. */
  public IntakeSpinRetractCG(IntakeS intake) {
    addCommands(new IntakeRetractC(intake), new IntakeSpinC(intake));
  }
}
