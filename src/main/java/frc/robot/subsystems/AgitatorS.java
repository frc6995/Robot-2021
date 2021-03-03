package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.constants.AgitatorConstants;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;

public class AgitatorS extends SubsystemBase {
  private NomadTalonSRX leftMotor;
  private NomadTalonSRX rightMotor;
  private AgitatorConstants constants;
  
  /** Creates a new AgitatorS.*/
  public AgitatorS(AgitatorConstants constants, NomadTalonSRX leftMotor, NomadTalonSRX rightMotor) {
    this.constants = constants;

    this.leftMotor = leftMotor;
    this.rightMotor = rightMotor;
  }

  public AgitatorConstants getConstants() {
    return constants;
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
