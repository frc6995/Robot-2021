package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.cannon.TurretHomeC;
import frc.robot.subsystems.ClimberS;
import frc.robot.subsystems.cannon.CannonS;

public class ClimberUpCG extends SequentialCommandGroup {
  /** Creates a new ClimberUpCG. */
  public ClimberUpCG(ClimberS climber, CannonS cannon) {
    addCommands(// new WaitCommand(0),
    new DisengageRatchetC(climber), 
    new WaitCommand(0.5), 
    new ClimberUpBangC(climber), 
    new WaitCommand(0.5), 
    new EngageRatchetC(climber));
  }
}
