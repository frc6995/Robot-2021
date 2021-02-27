package frc.robot.subsystems;

import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.robot.constants.interfaces.ColumnConstants;

public class ColumnS extends SubsystemBase {
  private NomadTalonSRX columnBelts;
  private DoubleSolenoid solenoid; 
  private ColumnConstants constants;
  private NomadSparkMax acceleratorWheels;

  /** Creates a new ColumnS. Moves the power cells up from the agitator to the shooter*/
  public ColumnS(ColumnConstants constants, NomadTalonSRX columnBelts, NomadSparkMax acceleratorWheels, DoubleSolenoid solenoid) {
    this.constants = constants;
    this.columnBelts = columnBelts;

    this.solenoid = solenoid;
    this.acceleratorWheels = acceleratorWheels; // id 43
    this.acceleratorWheels.setIdleMode(IdleMode.kCoast);
  }

  /**
   * Gets the constants for the column
   * @return column constants
   */
  public ColumnConstants getConstants(){
    return constants;
  }

  /**
   * Get whether the solenoid is extended or retracted
   * @return solenoid position
   */
  public Value getSolenoidPosition(){
    return solenoid.get();
  }

  /**
   * Set the speed of the column belts as a percent
   * @param speed of the column belts
   */
  public void setColumnBeltsSpeed(double speed){
    columnBelts.set(speed);
  }

  /**
   * Enable the stopper at the top of the column
   */
  public void enableStopper(){
    solenoid.set(Value.kForward);
  }
  
  /**
   * Disable the stopper at the top of the column
   */
  public void disableStopper(){
    solenoid.set(Value.kReverse);
  }

  /**
   * Spins accelerator wheels to a specificate rpm
   * @param rpm target speed
   */
  public void spinToRPM(double rpm){
    acceleratorWheels.getPIDController().setReference(rpm, ControlType.kVelocity, 0, constants.getAcceleratorArbitraryFF().calculate(rpm));
  }

  public void spinAccel(double speed) {
    acceleratorWheels.set(speed);
  }

  /**
   * Stops the accelerator wheels
   */
  public void stopAccelerator(){
    acceleratorWheels.set(0);
  }
 
  @Override
  public void periodic() {
    SmartDashboard.putNumber("AcceleratorSpeed", acceleratorWheels.getEncoder().getVelocity());
    SmartDashboard.putNumber("AcceleratorPos", acceleratorWheels.getEncoder().getPosition());
  }
}
