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

  @Override
  public void initialize() {
    agitator.setLeftMotor(agitator.getConstants().getLeftSpeed());
    agitator.setRightMotor(agitator.getConstants().getRightSpeed() * -1);
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    agitator.setLeftMotor(0);
    agitator.setRightMotor(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
