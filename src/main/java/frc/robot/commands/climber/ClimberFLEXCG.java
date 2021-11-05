package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ClimberS;

public class ClimberFLEXCG extends SequentialCommandGroup {
	/** Creates a new ClimberFLEXCG. */
	public ClimberFLEXCG(ClimberS climber) {
		addCommands(new EngageRatchetC(climber), new WaitCommand(0.5), new ClimberDownBangC(climber));
	}
}
