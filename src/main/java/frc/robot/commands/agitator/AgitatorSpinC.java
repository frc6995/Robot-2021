package frc.robot.commands.agitator;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AgitatorS;

public class AgitatorSpinC extends CommandBase {
  private AgitatorS agitator;

  /** Creates a new AgitatorSpinC. */
  public AgitatorSpinC(AgitatorS agitatorSubystem) {
    agitator = agitatorSubystem;
    addRequirements(agitatorSubystem);
  }

  @Override
  public void initialize() {
    agitator.stopLeftMotor();
    agitator.stopRightMotor();    
  }

  @Override
  public void execute() {
    agitator.setLeftMotor(agitator.getConstants().getLeftSpeed());
    agitator.setRightMotor(agitator.getConstants().getRightSpeed());
  }

  @Override
  public void end(boolean interrupted) {
    agitator.stopLeftMotor();
    agitator.stopRightMotor();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}