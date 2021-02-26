package frc.robot.subsystems;

import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.interfaces.ColumnConstants;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.robot.constants.interfaces.ColumnConstants;

public class ColumnS extends SubsystemBase {
  private NomadTalonSRX front;
  private DoubleSolenoid solenoid; 
  private ColumnConstants constants;
  private NomadSparkMax acceleratorWheels;
  private CANPIDController acceleratorPid;
  private SimpleMotorFeedforward accelFeedforward;

  /** Creates a new ColumnS. */
  public ColumnS(ColumnConstants constants, NomadTalonSRX front, NomadSparkMax acceleratorWheels, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.front = front;

    this.solenoid = solenoid;
    this.acceleratorWheels = acceleratorWheels; // id 43
    this.acceleratorPid = acceleratorWheels.getPIDController();
    double[] FFConstants = constants.getFeedForwardConstants();
    this.accelFeedforward = new SimpleMotorFeedforward(FFConstants[0], FFConstants[1], FFConstants[2]);
  }

  public ColumnConstants getConstants(){
    return constants;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Value getSolenoidPosition(){
    return solenoid.get();
  }

  public void setFrontSpeed(double speed){
    front.set(speed);
  }

  public void enableStopper(){
    solenoid.set(Value.kForward);
  }
  
  public void disableStopper(){
    solenoid.set(Value.kReverse);
  }

  public void spinAcceleratorUpwards(){
    acceleratorWheels.set(1); // TODO - Make sure that the wheels are not inverted
  }

  public void spinAcceleratorDownwards(){
    acceleratorWheels.set(-1);
  }

  public void stopAccelerator(){
    acceleratorWheels.set(0);
  }

  public void runVelocityPID(double rpm) {
    SmartDashboard.putNumber("Accel Target RPM", rpm);
    acceleratorPid.setReference(rpm, ControlType.kVelocity, 0, accelFeedforward.calculate(rpm));
  }
}
