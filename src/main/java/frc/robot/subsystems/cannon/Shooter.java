package frc.robot.subsystems.cannon;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.utility.math.NomadMathUtil;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.interfaces.ShooterConstants;
import frc.robot.constants.ShooterConstants2021;

/** The flywheel that shoots the power cells into the power port. 
 * @author JoeyFabel
*/
public class Shooter{
    /** The possible states of the Shooter */
    public enum ShooterStates {
        /** The shooter is currently off */
        OFF,
        /** The shooter is currently ramping up */
        RAMPING_UP,
        /** The shooter is at full speed */
        READY,
        /** The shooter is currently slowing down */
        SLOWING_DOWN
    }
    
    /**
     * The Spark Max's encoder
     */
    private CANEncoder encoder;
    /**
     * The Shooter's lead Spark Max
     */
    private NomadSparkMax motor;
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
        this.motor = leadMotor;
        encoder = leadMotor.getEncoder();
        shooterState = ShooterStates.OFF;
    }

    /**
     * Get the current speed of the Shooter, from the encoder
     * @return The shooter's speed
     */
    public double getEncoderSpeed(){
        return encoder.getPosition();
    }
    
    /**
     * Make the Shooter PID to the given speed.
     * @param speed The desired motor speed
     */
    public void pidToTargetSpeed(double speed){
        speed = NomadMathUtil.clamp(-0.8, 0.8, speed);
        targetSpeed = speed;
        
        motor.getPIDController().setP(constants.getKP());
        motor.getPIDController().setI(constants.getKI());
        motor.getPIDController().setD(constants.getKD());
        motor.getPIDController().setFF(constants.getKFF());
        motor.getPIDController().setIZone(constants.getIZone());
        motor.getPIDController().setReference(speed, ControlType.kVelocity);

        shooterState = ShooterStates.RAMPING_UP;
    }

    /**
     * Have the Shooter spin down and come to a stop.
     */
    public void spinDown(){
        pidToTargetSpeed(0.0);
        targetSpeed = 0;
        shooterState = ShooterStates.SLOWING_DOWN;
    }    

    /**
     * Get the Shooter's current state.
     * @return The {@link ShooterStates}
     */
    public ShooterStates getShooterState(){
        return shooterState;
    }

    /**
     * Method that is run every update loop.
     */
    protected void periodic(){
        updateState();
    }
    
    public boolean isVoltageNormal(){
        return Math.abs(motor.getBusVoltage() - constants.getAverageVoltage()) <= constants.getAllowableVoltageError();
    }
    
    /**
     * Update the state-machine's current state
     */
    private void updateState(){        
        if (shooterState == ShooterStates.RAMPING_UP && Math.abs(getEncoderSpeed() - targetSpeed) < constants.getAllowableRPMError()) shooterState = ShooterStates.READY;
        else if (shooterState == ShooterStates.SLOWING_DOWN && getEncoderSpeed() < constants.getAllowableRPMError()) shooterState = ShooterStates.OFF;        
    }
}