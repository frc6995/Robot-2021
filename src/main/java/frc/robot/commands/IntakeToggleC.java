package frc.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeS;

public class IntakeToggleC extends CommandBase {
  IntakeS intakeS;
  /** Creates a new IntakeToggleC. */
  public IntakeToggleC(IntakeS intakeS) {
    addRequirements(intakeS);
    this.intakeS = intakeS;
  }

  @Override
  public void initialize() {
    if (intakeS.getSolenoidPosition() == Value.kForward) {
      intakeS.setSpeed(0);
      intakeS.retract();
    }
    else if (intakeS.getSolenoidPosition() == Value.kReverse){
      intakeS.extend();
      intakeS.setSpeed(0.75);
    }
    else {
      intakeS.setSpeed(0);
      intakeS.retract();
    }
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}
