package frc.robot.subsystems.cannon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.interfaces.CannonConstants;
import frc.robot.subsystems.cannon.Shooter.ShooterStates;
import frc.robot.subsystems.cannon.Turret.TurretRequestedStates;

/** The Super Shooter Subsytem, it includes the {@link Shooter}, the {@link Hood}, and the {@link Turret}.
 * 
 * @author JoeyFabel
 */
public class CannonS extends SubsystemBase {  
  private Hood hood;
  private Shooter shooter;
  private Turret turret;

  /** Creates a new ShooterS. */
  public CannonS(CannonConstants constants, Servo hoodLeftServo, Servo hoodRightServo, NomadSparkMax shooterLeadMotor, NomadSparkMax turretMotor, DigitalInput turretLimitSwitch) {
    hood = new Hood(constants.getHoodConstants(), hoodLeftServo, hoodRightServo);
    shooter = new Shooter(constants.getShooterConstants(), shooterLeadMotor);
    turret = new Turret(constants.getTurretConstants(), turretMotor, turretLimitSwitch);
  }

  @Override
  public void periodic() {
    turret.periodic();
    shooter.periodic();
  }

  /**
   * Have the {@link Shooter} PID to the target speed.
   * @param speed The desired turret speed, between -0.8 and 0.8
   */
  public void pidShooterToTargetSpeed(double speed){
    shooter.pidToTargetSpeed(speed);
  }

  /**Have the {@link Shooter} PID to a stop. */
  public void spinDownShooter(){
    shooter.spinDown();
  }

  /**Is the {@link Shooter} ready to fire a ball? */
  public boolean isShooterAtSpeed(){
    return shooter.getShooterState() == ShooterStates.READY;
  }

  /**Is the {@link Shooter} currently stopped? */
  public boolean isShooterOff(){
    return shooter.getShooterState() == ShooterStates.OFF;
  }

  /**Is the {@link Shooter}'s voltage normal? */
  public boolean isShooterVoltageNormal(){
    return shooter.isVoltageNormal();
  }

  /**
   * Run PID on the Spark Max to the specified setpoint.
   */  
  public void runTurretPID(){
    turret.runPID();
  }

  /**
   * Run PID to the setpoint, using motion magic. Currently Incomplete (commented out lines need to be added)
   */
  public void runTurretPIDWithMotionMagic(){
    turret.runPIDWithMotionMagic();
  }

  /**
   * Request the Turret to go to into the specified state with a target setpoint.
   * If {@link TurretRequestedStates}.None is requested, the internal state is not changed.
   * 
   * @param state The {@link TurretRequestedStates} that you want the turret to enter
   * @param setpoint The desired position for the turret
   */
  public void requestTurretState(TurretRequestedStates state, double setpoint){
    turret.requestState(state, setpoint);
  }

  /**
   * Is the Turret homed?
   * @return <b>true</b> if the Turret is at its home position, <b>false</b> otherwise
   */
  public boolean isTurretHomed(){
    return turret.isHomed();
  }

  /**Is the Turret at the setpoint? */
  public boolean isTurretAtSetpoint(){
    return turret.isAtSetpoint();
  }

  /**
   * Move the hood to the appropriate angle, based on the given distance from the target.
   * @param distance The distance from the target
   * @return The desired angle, based on the given distance
   */
  public void moveHoodToDesiredAngle(double distance){
    hood.moveHoodToPosition(hood.getAngleBasedOnDistance(distance));
  }
  /**
   * Is the Hood at its setpoint?
   */
  public boolean isHoodAtSetpoint(){
    return hood.isAtSetpoint(); 
  }
}
