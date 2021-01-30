package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.constants.IntakeConstants;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.IntakeConstantsKRen;

public class IntakeS extends SubsystemBase {
  private NomadSparkMax intakeMotor;
  private DoubleSolenoid intakeSolenoid;

  /**
   * Creates a new IntakeS.
   */
  public IntakeS() {
    IntakeConstants constants = new IntakeConstantsKRen();
    intakeMotor = new NomadSparkMax(constants.getIntakeMotorPort());
    intakeSolenoid = new DoubleSolenoid(1,
              constants.getSolenoidFwdPort(), 
              constants.getSolenoidRevPort());
  }

  public void setSpeed(double speed) {
    intakeMotor.set(speed);
  }

  @Override
  public void periodic() {
  }

  public void extend() {
    intakeSolenoid.set(Value.kForward);
  }

  public void retract() {
    intakeSolenoid.set(Value.kReverse);
  }

  public Value getSolenoidPosition() {
    return intakeSolenoid.get();
  }
}
