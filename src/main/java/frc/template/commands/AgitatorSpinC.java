package frc.template.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.template.subsystems.AgitatorS;

public class AgitatorSpinC extends CommandBase {
  /** Creates a new AgitatorSpinC. */
  AgitatorS agitator;
  
  public AgitatorSpinC(AgitatorS agitatorSubystem) {
    agitator = agitatorSubystem;
    addRequirements(agitatorSubystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    agitator.stopLeftMotor();
    agitator.stopRightMotor();    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    agitator.setLeftMotor(0.8);
    agitator.setRightMotor(0.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    agitator.stopLeftMotor();
    agitator.stopRightMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}