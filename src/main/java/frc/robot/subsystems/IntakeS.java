package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.interfaces.IntakeConstants;

public class IntakeS extends SubsystemBase {
  private NomadSparkMax intakeMotor;
  private DoubleSolenoid intakeSolenoid;
  private IntakeConstants constants;

  /**
   * Creates a new IntakeS.
   */
  public IntakeS(IntakeConstants constants, NomadSparkMax intakeMotor, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.intakeMotor = intakeMotor;
    this.intakeSolenoid = solenoid;
  }

  public IntakeConstants getConstants(){
    return constants;
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
