package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadVictorSPX;
import frc.robot.constants.interfaces.AgitatorConstants;

public class AgitatorS extends SubsystemBase {
  private NomadTalonSRX leftMotor;
  private NomadTalonSRX rightMotor;
  private AgitatorConstants constants;
  
  /** Creates a new AgitatorS. Spins the powers cells into the column from the intake*/
  public AgitatorS(AgitatorConstants constants, NomadTalonSRX leftMotor, NomadTalonSRX rightMotor) {
    this.constants = constants;

    this.leftMotor = leftMotor;
    this.rightMotor = rightMotor;
  }

  /**
   * Gets the agitator constants
   * @return agitator constants
   */
  public AgitatorConstants getConstants() {
    return constants;
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
