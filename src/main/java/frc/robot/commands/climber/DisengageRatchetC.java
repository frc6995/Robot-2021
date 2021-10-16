package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ClimberS;

public class DisengageRatchetC extends InstantCommand {

	private ClimberS climber;

	public DisengageRatchetC(ClimberS climber) {
		this.climber = climber;
		addRequirements(climber);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		climber.disengageRatchet();
	}
}
