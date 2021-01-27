package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.TurretConstantsKRen;

/**
 * The Turret on the robot. It controls the horizontal rotation of the {@link ShooterS}.
 * 
 * @author JoeyFabel
 */
public class TurretS extends SubsystemBase {
  
  /**
   * An enum containing the different possible internal states of the Turret
   */
  public enum TurretInternalStates{
    /**
     * Turret is currently homed to its starting position
     */
    HOMED,
    /**
     * Turret is currently homing to its starting position
     */
    HOMING,
    /**
     * Turret is currently moving to its setpoint
     */
    MOVING_TO_SETPOINT,
    /**
     * Turret is currently at its setpoint
     */
    AT_SETPOINT,
    /**
     * Turrent is currently stopped
     */
    STOPPED;
  }
  
  /**
   * An enum containing the states that can be requested from the Turret
   */
  public enum TurretRequestedStates{
    /**
     * Make the Turret return to its home position
     */
    HOME,
    /**
     * Make the Turret go to its specified setpoint
     */
    MOVE_TO_SETPOINT,
    /**
     * Make the Turret stop moving where it is
     */
    STOP,
    /**
     * Do not give the Turret a command
     */
    NONE;
  }

  /**
   * The desired setpoint
   */
  private double setpoint;
  /**
   * The number of periods that the Turret has been within the margin of error of its desired setpoint
   */
  private int withinSetpointCounter;
  /**
   * The current internal state of the Turret
   */
  private TurretInternalStates internalState;
  /**
   * The requested state of the Turret
   */
  private TurretRequestedStates requestedState;
  /**
   * The motor controlling the Turret
   */
  private NomadSparkMax sparkMax;
  /**
   * The magnetic limit switch on the Turret, located on its home position
   */
  private DigitalInput limitSwitch;
  /**
   * The Throughbore encoder, plugged directly into the Spark Max
   */
  private CANEncoder encoder;

  /** Counter that checks if the Turret is homed */
  Counter homedCounter;

  /**
   * Instance of the {@link TurretConstantsKRen}, allowing access to the constants
   */
  TurretConstantsKRen constants;

  /** Creates a new TurretS. */
  public TurretS() {
    constants = new TurretConstantsKRen();

    setpoint = 0;
    withinSetpointCounter = 0;
    internalState = TurretInternalStates.HOMING;
    requestedState = TurretRequestedStates.HOME;
    sparkMax = new NomadSparkMax(constants.getSparkMaxPortID());
    limitSwitch = new DigitalInput(constants.getLimitSwitchChannelID());
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
    if (internalState == TurretInternalStates.AT_SETPOINT) withinSetpointCounter++;
    else withinSetpointCounter = 0;     
  }

  /**
   * Convert the current encoder reading from ticks to the angle of the turret.
   * 
   * @return The angle of the turret, in degrees
   */
  public double convertEncoderTicksToAngle(){
    return encoder.getPosition() / constants.getEncoderTicksPerDegree();
  }

  /**
   * Convert the specified number of encoder ticks into an angle.
   * 
   * @return The angle of the turret, in degrees
   */
  public double convertEncoderTicksToAngle(double encoderTicks){
    return encoderTicks / constants.getEncoderTicksPerDegree();
  }

  /**
   * Convert the specified angle of the turret from degrees into encoder ticks.
   * 
   * @return The number of ticks in the specified angle
   */
  public double convertAngleToEncoderTicks(double degrees){
    return degrees * constants.getEncoderTicksPerDegree();
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
    sparkMax.getPIDController().setP(constants.getKP());
    sparkMax.getPIDController().setI(constants.getKI());
    sparkMax.getPIDController().setD(constants.getKD());
    sparkMax.getPIDController().setFF(constants.getKFF());
    sparkMax.getPIDController().setReference(setpoint, ControlType.kPosition);    
  }

  /**
   * Run PID to the setpoint, using motion magic. Currently Incomplete (commented out lines need to be added)
   */
  public void runPIDWithMotionMagic(){
    sparkMax.getPIDController().setP(constants.getKP());
    sparkMax.getPIDController().setI(constants.getKI());
    sparkMax.getPIDController().setD(constants.getKD());
    sparkMax.getPIDController().setFF(constants.getKFF());

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
    return withinSetpointCounter > constants.getMinCountsAtSetpoint();
  }

  /**
   * Request the Turret to go to into the specified state with a target setpoint.
   * If {@link TurretRequestedStates}.None is requested, the internal state is not changed.
   * 
   * @param desiredState The {@link TurretRequestedStates} that you want the turret to enter
   * @param setpoint The desired position for the turret
   */
  public void requestState(TurretRequestedStates desiredState, double setpoint){    
    if (setpoint < constants.getSoftLimit() || setpoint > constants.getSoftLimit()) return;
    
    requestedState = desiredState;    
    this.setpoint = setpoint;    

    if (requestedState == TurretRequestedStates.HOME) internalState = TurretInternalStates.HOMING;
    else if (requestedState == TurretRequestedStates.MOVE_TO_SETPOINT) internalState = TurretInternalStates.MOVING_TO_SETPOINT;
    else if (requestedState == TurretRequestedStates.STOP) internalState = TurretInternalStates.STOPPED;
    // If requestedState is None, do not update the internal state
  }

  private void checkForSoftLimit(){
    if (convertEncoderTicksToAngle(getTurretEncoderPosition()) < -constants.getSoftLimit()){
      requestState(TurretRequestedStates.MOVE_TO_SETPOINT, setpoint + 10);
    }
    else if (convertEncoderTicksToAngle(getTurretEncoderPosition()) > constants.getSoftLimit()){
      requestState(TurretRequestedStates.MOVE_TO_SETPOINT, setpoint - 10);
    }
  }

  /**
   * Update method that is called in period. It updates the Turret based on its current internal state.
   */
  private void stateMachineLoop(){
    switch (internalState){
      case HOMED:
        sparkMax.stopMotor();
        homedCounter.reset();
        break;
      case HOMING:
        runPID();
        if ((getTurretEncoderPosition() > constants.getHomePosition() - constants.getMarginOfError() && getTurretEncoderPosition() < constants.getHomePosition() + (constants.getMarginOfError())) || homedCounter.get() > 0) internalState = TurretInternalStates.HOMED;
        break;
      case MOVING_TO_SETPOINT:
        runPID();
        checkForSoftLimit();
        if (isAtSetpoint()) internalState = TurretInternalStates.AT_SETPOINT;
        break;
      case AT_SETPOINT:
        if (!isAtSetpoint()) internalState = TurretInternalStates.MOVING_TO_SETPOINT;
        break;
      case STOPPED:
        sparkMax.stopMotor();
        homedCounter.reset();
        break;
      }
  }
}
