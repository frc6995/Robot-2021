package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.ClimberS;

public class ClimberFLEXCG extends SequentialCommandGroup {
	/** Creates a new ClimberFLEXCG. */
	public ClimberFLEXCG(ClimberS climber) {
		addCommands(new EngageRatchetC(climber), new RunClimberPIDC(climber));
	}
}
