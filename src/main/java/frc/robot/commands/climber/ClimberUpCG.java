package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberS;

public class ClimberUpCG extends SequentialCommandGroup {
  /** Creates a new ClimberUpCG. */
  public ClimberUpCG(ClimberS climber) {
    addCommands(new DisengageRatchetC(climber), new ClimberUpBangC(climber));
  }
}
