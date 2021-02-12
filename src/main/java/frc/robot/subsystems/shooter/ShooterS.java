package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.SuperShooterSConstants;

/** The Super Shooter Subsytem, it includes the {@link Shooter}, the {@link Hood}, and the {@link Turret}.
 * 
 * @author JoeyFabel
 */
public class ShooterS extends SubsystemBase {  
  private Hood hood;
  private Shooter shooter;
  private Turret turret;

  private SuperShooterSConstants constants;

  /** Creates a new ShooterS. */
  public ShooterS(SuperShooterSConstants constants, Servo hoodLeftServo, Servo hoodRightServo, NomadSparkMax shooterLeadMotor, NomadSparkMax shooterFollowerMotor, NomadSparkMax turretMotor, DigitalInput turretLimitSwitch) {
    this.constants = constants;

    hood = new Hood(constants.getHoodConstants(), hoodLeftServo, hoodRightServo);
    shooter = new Shooter(constants.getShooterConstants(), shooterLeadMotor, shooterFollowerMotor);
    turret = new Turret(constants.getTurretConstants(), turretMotor, turretLimitSwitch);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
