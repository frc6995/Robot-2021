/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.wrappers.motorcontrollers.NomadSparkMax;

public class AgitatorS extends SubsystemBase {
  private NomadSparkMax leftMotor;
  private NomadSparkMax rightMotor;
  
  /**
   * Creates a new Agitator.
   */
  public AgitatorS() {
    leftMotor = new NomadSparkMax(33); // 31
    rightMotor = new NomadSparkMax(30); // 32
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Sets the speed of the left motor to the given value
   * @param motorSpeed the speed of the motor
   */
  public void setLeftMotor(double motorSpeed){
    leftMotor.set(motorSpeed);
  }

  /**
   * Sets the speed of the right motor to the given value
   * @param motorSpeed the speed of the motor
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
