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
   * Creates a new IntakeS. System for picking up power cells from the ground
   */
  public IntakeS(IntakeConstants constants, NomadSparkMax intakeMotor, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.intakeMotor = intakeMotor;
    this.intakeSolenoid = solenoid;
  }

  /**
   * Get the intake constants
   * 
   * @return intake constants
   */
  public IntakeConstants getConstants() {
    return constants;
  }

  /**
   * set the speed of the intake motor as a percent
   * 
   * @param speed of the intake motor
   */
  public void setSpeed(double speed) {
    intakeMotor.set(speed);
  }

  /**
   * Extends the intake
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
   * Get whether the solenoid is extended or retracted
   * 
   * @return solenoid position
   */
  public Value getSolenoidPosition() {
    return intakeSolenoid.get();
  }

  @Override
  public void periodic() {
  }
}
