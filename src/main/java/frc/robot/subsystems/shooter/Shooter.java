package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.ShooterConstants;
import frc.robot.constants.ShooterConstants2021;

/** The flywheel that shoots the power cells into the power port. 
 * @author JoeyFabel
*/
public class Shooter {
    public enum ShooterStates {
        OFF,
        RAMPING_UP,
        AT_TARGET_SPEED,
        SLOWING_DOWN
    }
    
    /**
     * The Spark Max's encoder
     */
    private CANEncoder encoder;
    /**
     * The Shooter's lead Spark Max
     */
    private NomadSparkMax leadMotor;
    /**
     * The constants for the Shooter
     */
    private ShooterConstants constants;
    /**The curret state of the Shooter */
    private ShooterStates shooterState;
    /**The target speed */
    private double targetSpeed;
    /**
     * Create a new Shooter with 2021 constants.
     */
    public Shooter(NomadSparkMax leadMotor){
        this(new ShooterConstants2021(), leadMotor);
    }
    
    /**
     * Create a new Shooter with the specified constants file.
     * @param shooterConstants The {@link ShooterConstants} for this to use
     */
    public Shooter(ShooterConstants shooterConstants, NomadSparkMax leadMotor){
        constants = shooterConstants;
        this.leadMotor = leadMotor;
        followerMotor.follow(leadMotor);
        encoder = leadMotor.getEncoder();
        shooterState = ShooterStates.OFF;
    }

    public double getEncoderSpeed(){
        return encoder.getPosition();
    }

    public void pidToTargetSpeed(double speed){
        if (speed > 0.8) speed = 0.8;
        else if (speed < -0.8) speed = -0.8;
        targetSpeed = speed;

        leadMotor.getPIDController().setP(constants.getKP());
        leadMotor.getPIDController().setI(constants.getKI());
        leadMotor.getPIDController().setD(constants.getKD());
        leadMotor.getPIDController().setFF(constants.getKFF());
        leadMotor.getPIDController().setReference(speed, ControlType.kPosition);

        shooterState = ShooterStates.RAMPING_UP;
    }

    public void spinDown(){
        pidToTargetSpeed(0.0);
        targetSpeed = 0;
        shooterState = ShooterStates.SLOWING_DOWN;
    }    

    public ShooterStates getShooterState(){
        return shooterState;
    }

    protected void periodic(){
        updateState();
    }
    
    private void updateState(){        
        if (shooterState == ShooterStates.RAMPING_UP && getEncoderSpeed() == targetSpeed) shooterState = ShooterStates.AT_TARGET_SPEED;
        else if (shooterState == ShooterStates.SLOWING_DOWN && getEncoderSpeed() < 0.001) shooterState = ShooterStates.OFF;        
    }
}
