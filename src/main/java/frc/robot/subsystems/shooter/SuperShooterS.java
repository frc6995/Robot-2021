package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.SuperShooterSConstants;
import frc.robot.subsystems.shooter.Turret.TurretRequestedStates;

/** The Super Shooter Subsytem, it includes the {@link Shooter}, the {@link Hood}, and the {@link Turret}.
 * 
 * @author JoeyFabel
 */
public class SuperShooterS extends SubsystemBase {  
  private Hood hood;
  private Shooter shooter;
  private Turret turret;

  private SuperShooterSConstants constants;

  /** Creates a new ShooterS. */
  public SuperShooterS(SuperShooterSConstants constants, Servo hoodLeftServo, Servo hoodRightServo, NomadSparkMax shooterLeadMotor, NomadSparkMax turretMotor, DigitalInput turretLimitSwitch) {
    this.constants = constants;

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
   * Move the {@link Hood} to the specified position.
   * 
   * @param position The desired position
   */
  public void moveHoodToPosition(double position){
    hood.moveHoodToPosition(position);
  }

  /**
   * Have the {@link Shooter} PID to the target speed.
   * @param speed The desired turret speed, between -0.8 and 0.8
   */
  public void pidShooterToTargetSpeed(double speed){
    shooter.pidToTargetSpeed(speed);
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
}
