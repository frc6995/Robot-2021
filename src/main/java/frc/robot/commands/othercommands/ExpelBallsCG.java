package frc.robot.commands.othercommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.agitator.AgitatorSpinC;
import frc.robot.commands.column.ColumnFeedC;
import frc.robot.commands.intake.IntakeSpinRetractCG;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.IntakeS;

public class ExpelBallsCG extends ParallelCommandGroup {
  /** Creates a new ExpellBallsCG. */
  public ExpelBallsCG(IntakeS intake, AgitatorS agitator, ColumnS column) {
    addCommands(new IntakeSpinRetractCG(intake), new AgitatorSpinC(agitator), new ColumnFeedC(column));
  }
}
