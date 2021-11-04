package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimberS;

public class ClimberManualModeC extends CommandBase {
  private ClimberS climberS;
  private GenericHID controller;
  private double translateSpeed = 0;
  private double climbSpeed = 0;

  /** Creates a new ClimberManualModeC. */
  public ClimberManualModeC(ClimberS climberS, GenericHID controller) {
    this.climberS = climberS;
    this.controller = controller;
    addRequirements(climberS);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    climbSpeed = controller.getRawAxis(XboxController.Axis.kLeftY.value) * 0.5;
    translateSpeed = controller.getRawAxis(XboxController.Axis.kLeftX.value) * 0.5;

    climberS.setSpeed(climbSpeed);
    climberS.setTranslatorSpeed(translateSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
