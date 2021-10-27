package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.interfaces.IntakeConstants;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;

public class IntakeS extends SubsystemBase {
  private NomadSparkMax intakeMotor;
  private DoubleSolenoid intakeSolenoid;
  private IntakeConstants constants;

  /**
   * Creates a new IntakeS.
   * Collects the balls by spinning motors
   */
  public IntakeS(IntakeConstants constants, NomadSparkMax intakeMotor, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.intakeMotor = intakeMotor;
    this.intakeSolenoid = solenoid;
  }

  /**
   * Gets the constants
   * @return constants
   */
  public IntakeConstants getConstants(){
    return constants;
  }

  /**
   * Sets the speed of the intake motor
   * @param speed of intake motor
   */
  public void setSpeed(double speed) {
    intakeMotor.set(speed);
  }

  /**
   * subsystem periodic method
   */
  @Override
  public void periodic() {
  }

  /**
   * Extends intake forward
   */
  public void extend() {
    intakeSolenoid.set(Value.kForward);
  }

  /**
   * Retracts the intake
   */
  public void retract() {
    intakeSolenoid.set(Value.kReverse);
  }

  /**
   * Gets the position of the intake
   * @return the position of the intake
   */
  public Value getSolenoidPosition() {
    return intakeSolenoid.get();
  }
}
