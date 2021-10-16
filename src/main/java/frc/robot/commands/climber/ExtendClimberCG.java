package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ClimberS;

public class ExtendClimberCG extends SequentialCommandGroup {
	/** Creates a new ExtendClimberCG. */
	public ExtendClimberCG(ClimberS climber) {
		addCommands(new DeployClimberC(climber), new WaitCommand(1), new DisengageRatchetC(climber));
	}
}
