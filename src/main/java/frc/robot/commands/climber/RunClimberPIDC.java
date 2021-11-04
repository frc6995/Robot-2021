package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberS;

public class RunClimberPIDC extends CommandBase {

	private ClimberS climber;

	/** Creates a new RunClimberPIDC. */
	public RunClimberPIDC(ClimberS climber) {
		this.climber = climber;
		addRequirements(climber);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		climber.runPID();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return climber.isAtSetpoint();
	}
}
