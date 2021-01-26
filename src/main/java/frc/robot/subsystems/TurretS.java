// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.utility.math.NomadMathUtil;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.TurretConstantsKRen;

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

  TurretConstantsKRen constants;

  /** Creates a new TurretS. */
  public TurretS() {
    constants = new TurretConstantsKRen(); // Is this how I am supposed to use this?

    setpoint = 0;
    withinSetpointCounter = 0;
    internalState = TurretInternalStates.Homing;
    requestedState = TurretRequestedStates.Home;
    sparkMax = new NomadSparkMax(constants.sparkMaxPortID());
    limitSwitch = new DigitalInput(constants.limitSwitchChannelID());
    // Currently, limit switch is counted as pressed if counter is greater than 0. 
    // The Counter can catch it pushing if it is faster than the periodic check, so this can help there.
    homedCounter = new Counter(limitSwitch);
    homedCounter.reset();
    encoder = sparkMax.getEncoder();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
    stateMachineLoop();

    // Increase counter if at setpoint, or reset if it is not
    if (internalState == TurretInternalStates.AtSetpoint) withinSetpointCounter++;
    else withinSetpointCounter = 0;     
  }

  /**
   * Convert the current encoder reading from ticks to the angle of the turret.
   * 
   * @return The angle of the turret, in degrees
   */
  public double convertEncoderTicksToAngle(){
    return encoder.getPosition() / constants.encoderTicksPerDegree();
  }

  /**
   * Convert the specified number of encoder ticks into an angle.
   * 
   * @return The angle of the turret, in degrees
   */
  public double convertEncoderTicksToAngle(double encoderTicks){
    return encoderTicks / constants.encoderTicksPerDegree();
  }

  /**
   * Convert the specified angle of the turret from degrees into encoder ticks.
   * 
   * @return The number of ticks in the specified angle
   */
  public double convertAngleToEncoderTicks(double degrees){
    return degrees * constants.encoderTicksPerDegree();
  }

  /**
   * Get the current position of the encoder.
   * @return The encoder's position, in ticks
   */
  public double getTurretEncoderPosition(){
    return encoder.getPosition();
  }

  /**
   * Run PID on the Spark Max to the specified setpoint.
   */
  public void runPID(){
    sparkMax.getPIDController().setP(constants.kP());
    sparkMax.getPIDController().setI(constants.kI());
    sparkMax.getPIDController().setD(constants.kD());
    sparkMax.getPIDController().setFF(constants.kFF());
    sparkMax.getPIDController().setReference(setpoint, ControlType.kPosition);    
  }

  /**
   * Run PID to the setpoint, using motion magic. Currently Incomplete (commented out lines need to be added)
   */
  public void runPIDWithMotionMagic(){
    sparkMax.getPIDController().setP(constants.kP());
    sparkMax.getPIDController().setI(constants.kI());
    sparkMax.getPIDController().setD(constants.kD());
    sparkMax.getPIDController().setFF(constants.kFF());

    // TODO - set velocity/acceleration limits
    //sparkMax.getPIDController().setSmartMotionMaxAccel(maxAccel, slotID);
    //sparkMax.getPIDController().setSmartMotionMaxVelocity(maxVel, slotID);
    //sparkMax.getPIDController().setSmartMotionMinOutputVelocity(minVel, slotID);
    //sparkMax.getPIDController().setSmartMotionAccelStrategy(accelStrategy, slotID);
    //sparkMax.getPIDController().setSmartMotionAllowedClosedLoopError(allowedErr, slotID);
    //-------------------------------------------------------------------------------------

    sparkMax.getPIDController().setReference(setpoint, ControlType.kSmartMotion);    
  }

  /**
   * Is the Turret currently at its setpoint?
   *  
   * @return <b>true</b> if is at the setpoint, <b>false</b> otherwise
   */
  public boolean isAtSetpoint(){
    return withinSetpointCounter > 10;
  }

  /**
   * Request the Turret to go to into the specified state with a target setpoint.
   * If {@link TurretRequestedStates}.None is requested, the internal state is not changed.
   * 
   * @param desiredState The {@link TurretRequestedStates} that you want the turret to enter
   * @param setpoint The desired position for the turret
   */
  public void requestState(TurretRequestedStates desiredState, double setpoint){    
    if (setpoint < constants.softLimit() || setpoint > constants.softLimit()) return;
    
    requestedState = desiredState;    
    this.setpoint = setpoint;    

    if (requestedState == TurretRequestedStates.Home) internalState = TurretInternalStates.Homing;
    else if (requestedState == TurretRequestedStates.MoveToSetpoint) internalState = TurretInternalStates.MovingToSetpoint;
    else if (requestedState == TurretRequestedStates.Stop) internalState = TurretInternalStates.Stopped;
    // If requestedState is None, do not update the internal state
  }

  private void checkForSoftLimit(){
    if (convertEncoderTicksToAngle(getTurretEncoderPosition()) < -constants.softLimit()){
      requestState(TurretRequestedStates.MoveToSetpoint, setpoint + 10);
    }
    else if (convertEncoderTicksToAngle(getTurretEncoderPosition()) > constants.softLimit()){
      requestState(TurretRequestedStates.MoveToSetpoint, setpoint - 10);
    }
  }

  /**
   * Update method that is called in period. It updates the Turret based on its current internal state.
   */
  private void stateMachineLoop(){
    switch (internalState){
      case Homed:
        sparkMax.stopMotor();
        homedCounter.reset();
        break;
      case Homing:
        runPID();
        if ((getTurretEncoderPosition() > constants.homePosition() - constants.marginOfError() && getTurretEncoderPosition() < constants.homePosition + constants.marginOfError) || homedCounter.get() > 0) internalState = TurretInternalStates.Homed;
        break;
      case MovingToSetpoint:
        runPID();
        checkForSoftLimit();
        if (isAtSetpoint()) internalState = TurretInternalStates.AtSetpoint;
        break;
      case AtSetpoint:
        if (!isAtSetpoint()) internalState = TurretInternalStates.MovingToSetpoint;
        break;
      case Stopped:
        sparkMax.stopMotor();
        homedCounter.reset();
        break;
      }
  }
}
