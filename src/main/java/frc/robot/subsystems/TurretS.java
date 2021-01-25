// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.TurretConstants;

public class TurretS extends SubsystemBase {
  
  /**
   * An enum containing the different possible internal states of the Turret
   */
  public enum TurretInternalStates{
    /**
     * Turret is currently homed to its starting position
     */
    Homed,
    /**
     * Turret is currently homing to its starting position
     */
    Homing,
    /**
     * Turret is currently moving to its setpoint
     */
    MovingToSetpoint,
    /**
     * Turret is currently at its setpoint
     */
    AtSetpoint,
    /**
     * Turrent is currently stopped
     */
    Stopped;
  }
  
  /**
   * An enum containing the states that can be requested from the Turret
   */
  public enum TurretRequestedStates{
    /**
     * Make the Turret return to its home position
     */
    Home,
    /**
     * Make the Turret go to its specified setpoint
     */
    MoveToSetpoint,
    /**
     * Make the Turret stop moving where it is
     */
    Stop,
    /**
     * Do not give the Turret a command
     */
    None;
  }

  /**
   * The desired setpoint
   */
  double setpoint;
  /**
   * The number of periods that the Turret has been within the margin of error of its desired setpoint
   */
  int withinSetpointCounter;
  /**
   * The current internal state of the Turret
   */
  TurretInternalStates internalState;
  /**
   * The requested state of the Turret
   */
  TurretRequestedStates requestedState;
  /**
   * The motor controlling the Turret
   */
  NomadSparkMax sparkMax;
  /**
   * The magnetic limit switch on the Turret, located on its home position
   */
  DigitalInput limitSwitch;
  /**
   * The Throughbore encoder, plugged directly into the Spark Max
   */
  CANEncoder encoder;

  /* Test counter, not implemented yet, that could be used to more accurately check if the Turret is homed */
  Counter homedCounter;

  /** Creates a new TurretS. */
  public TurretS() {
    setpoint = 0;
    withinSetpointCounter = 0;
    internalState = TurretInternalStates.Homing;
    requestedState = TurretRequestedStates.Home;
    sparkMax = new NomadSparkMax(1); // TODO - make a RobotMap or something similar with port numbers
    homedCounter = new Counter(limitSwitch);
    encoder = sparkMax.getEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    stateMachineLoop();
  }

  public double convertEncoderTicksToAngle(){
    return encoder.getPosition() / TurretConstants.encoderTicksPerDegree;
  }

  public double convertEncoderTicksToAngle(double encoderTicks){
    return encoderTicks / TurretConstants.encoderTicksPerDegree;
  }

  public double convertAngleToEncoderTicks(double degrees){
    return degrees * TurretConstants.encoderTicksPerDegree;
  }

  public double getTurretEncoderPosition(){
    return encoder.getPosition();
  }

  public void runPID(){
    sparkMax.getPIDController().setP(TurretConstants.kP);
    sparkMax.getPIDController().setI(TurretConstants.kI);
    sparkMax.getPIDController().setD(TurretConstants.kD);
    sparkMax.getPIDController().setFF(TurretConstants.kFF);
    sparkMax.getPIDController().setReference(setpoint, ControlType.kPosition);    
  }

  public void runPIDWithMotionMagic(){
    sparkMax.getPIDController().setP(TurretConstants.kP);
    sparkMax.getPIDController().setI(TurretConstants.kI);
    sparkMax.getPIDController().setD(TurretConstants.kD);
    sparkMax.getPIDController().setFF(TurretConstants.kFF);

    // TODO - set velocity/acceleration limits
    sparkMax.getPIDController().setReference(setpoint, ControlType.kSmartMotion);    
  }

  private void stateMachineLoop(){

  }
}
