package frc.robot.commands.intake;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.AgitatorS;
import frc.robot.subsystems.IntakeS;

public class IntakeToggleC extends InstantCommand {
  private IntakeS intakeS;
  private AgitatorS agitatorS;
  
  /** Creates a new IntakeToggleC. */
  public IntakeToggleC(IntakeS intakeS, AgitatorS agitatorS) {
    addRequirements(intakeS);
    this.intakeS = intakeS;
    this.agitatorS = agitatorS;
  }

  @Override
  public void initialize() {
    if (intakeS.getSolenoidPosition() == Value.kForward) {
      intakeS.setSpeed(0);
      agitatorS.setLeftMotor(0);
      agitatorS.setRightMotor(0);
      intakeS.retract();
    }
    else if (intakeS.getSolenoidPosition() == Value.kReverse){
      intakeS.extend();
      intakeS.runIntake();
      agitatorS.setLeftMotor(agitatorS.getConstants().getLeftSpeed());
      agitatorS.setRightMotor(agitatorS.getConstants().getRightSpeed());
    }
    else {
      intakeS.setSpeed(0);
      intakeS.retract();
    }
  }
}
