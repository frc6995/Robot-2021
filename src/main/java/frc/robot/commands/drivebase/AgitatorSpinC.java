package frc.robot.commands.drivebase;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AgitatorS;
import frc.robot.wrappers.inputdevices.NomadDriverController;

/**
 * @author JoeyFabel
 */
public class AgitatorSpinC extends CommandBase {
  AgitatorS agitator;
  NomadDriverController controller;
  
  /**
   * Creates a new AgitatorSpinC.
   * @param agitatorSubsystem the {@link AgitatorS}
   * @param controller the {@link frc.robot.wrappers.InputDevices.NomadDriverController}
   */
  public AgitatorSpinC(AgitatorS agitatorSubystem, NomadDriverController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    agitator = agitatorSubystem;
    this.controller = controller;
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
    agitator.setLeftMotor(controller.getRawAxis(1));
    agitator.setRightMotor(controller.getRawAxis(5));
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
