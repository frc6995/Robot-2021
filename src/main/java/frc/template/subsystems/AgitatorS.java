package frc.template.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;

public class AgitatorS extends SubsystemBase {
  private NomadTalonSRX leftMotor;
  private NomadTalonSRX rightMotor;
  
  /** Creates a new AgitatorS.*/
  public AgitatorS() {
    leftMotor = new NomadTalonSRX(31);
    rightMotor = new NomadTalonSRX(32);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Sets the speed of the left motor to a given value
   */
  public void setLeftMotor(double motorSpeed){
    leftMotor.set(motorSpeed);
  }

  /**
   * Sets the speed of the right motor to a given value
   * @param motorSpeed
   */
  public void setRightMotor(double motorSpeed){
    rightMotor.set(motorSpeed);
  }

  /**
   * Stops the left motor
   */
  public void stopLeftMotor(){
    leftMotor.stopMotor();
  }

  /**
   * Stops the right motor
   */
  public void stopRightMotor(){
    rightMotor.stopMotor();
  }
}
