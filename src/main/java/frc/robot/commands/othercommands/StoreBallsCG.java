package frc.robot.commands.othercommands;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.agitator.AgitatorSpinC;
import frc.robot.commands.column.ColumnLoadC;
import frc.robot.commands.intake.IntakeSpinWhileHeldC;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.ColumnS;
import frc.robot.subsystems.IntakeS;

public class StoreBallsCG extends ParallelCommandGroup {
  /** Creates a new StoreBallsC. */
  public StoreBallsCG(IntakeS intake, AgitatorS agitator, ColumnS column) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new IntakeSpinWhileHeldC(intake), new AgitatorSpinC(agitator), new ColumnLoadC(column));
  }
}
