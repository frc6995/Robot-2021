package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.interfaces.IntakeConstants;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;

public class IntakeS extends SubsystemBase {
  private NomadSparkMax intakeMotor;
  private NomadSparkMax intakeBackMotor;
  private DoubleSolenoid intakeSolenoid;
  private IntakeConstants constants;

  /**
   * Creates a new IntakeS.
   */
  public IntakeS(IntakeConstants constants, NomadSparkMax intakeMotor, NomadSparkMax intakeBackMotor, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.intakeMotor = intakeMotor;
    this.intakeBackMotor = intakeBackMotor;
    //this.intakeBackMotor.follow(this.intakeMotor);
    this.intakeBackMotor.setInverted(true);
    this.intakeSolenoid = solenoid;
  }

  public IntakeConstants getConstants(){
    return constants;
  }

  public void setSpeed(double speed) {
    intakeMotor.set(speed);
    intakeBackMotor.set(speed);
  }

  public void runIntake() {
    intakeMotor.set(constants.getIntakeSpeed());
    intakeBackMotor.set(constants.getIntakeBackRollerSpeed());
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
