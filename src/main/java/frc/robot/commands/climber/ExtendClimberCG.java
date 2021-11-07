package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.ClimberS;
import frc.robot.subsystems.cannon.CannonS;
import frc.robot.commands.cannon.*;

public class ExtendClimberCG extends SequentialCommandGroup {

	/** Creates a new ExtendClimberCG. */
	public ExtendClimberCG(ClimberS climber, CannonS cannon) {
		addCommands(new TurretHomeC(cannon).withTimeout(0.5), new DeployClimberC(climber));
	}
}
