package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;
import frc.robot.constants.AgitatorConstantsKRen;

public class AgitatorS extends SubsystemBase {
  private NomadTalonSRX leftMotor;
  private NomadVictorSPX rightMotor;
  private AgitatorConstantsKRen constants;
  
  /** Creates a new AgitatorS.*/
  public AgitatorS() {
    constants = new AgitatorConstantsKRen();

    leftMotor = new NomadTalonSRX(constants.getLeftMotorID());
    rightMotor = new NomadVictorSPX(constants.getRightMotorID());
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
