package frc.robot.commands.agitator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AgitatorS;

public class AgitatorUnjamC extends CommandBase {
  public AgitatorS agitator;

  /** Creates a new AgitatorUnjamC. */
  public AgitatorUnjamC(AgitatorS agitator) {
    this.agitator = agitator;
    addRequirements(agitator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    agitator.setLeftMotor(agitator.getConstants().getLeftSpeed());
    agitator.setRightMotor(agitator.getConstants().getRightSpeed() * -1);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    agitator.setLeftMotor(0);
    agitator.setRightMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
