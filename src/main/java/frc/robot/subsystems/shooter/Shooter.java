package frc.robot.subsystems.shooter;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.Encoder;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.constants.ShooterConstants;
import frc.robot.constants.ShooterConstants2021;

/** The flywheel that shoots the power cells into the power port. */
public class Shooter {
    /**
     * The Spark Max's encoder
     */
    private CANEncoder encoder;
    /**
     * The Shooter's lead Spark Max
     */
    private NomadSparkMax leadMotor;
    /**
     * The Shooter's follower Spark Max
     */
    private NomadSparkMax followerMotor;
    /**
     * The constants for the Shooter
     */
    private ShooterConstants constants;
    /**
     * Create a new Shooter with 2021 constants.
     */
    public Shooter(NomadSparkMax leadMotor, NomadSparkMax followerMotor){
        this(new ShooterConstants2021(), leadMotor, followerMotor);
    }
    
    /**
     * Create a new Shooter with the specified constants file.
     * @param shooterConstants The {@link ShooterConstants} for this to use
     */
    public Shooter(ShooterConstants shooterConstants, NomadSparkMax leadMotor, NomadSparkMax followerMotor){
        constants = shooterConstants;
        this.leadMotor = leadMotor;
        this.followerMotor = followerMotor;
        encoder = leadMotor.getEncoder();
    }

    public double getEncoderSpeed(){
        return encoder.getPosition();
    }

    public void pidToTargetSpeed(double speed){
        leadMotor.getPIDController().setP(constants.getKP());
        leadMotor.getPIDController().setI(constants.getKI());
        leadMotor.getPIDController().setD(constants.getKD());
        leadMotor.getPIDController().setFF(constants.getKFF());
        leadMotor.getPIDController().setReference(speed, ControlType.kPosition);
    }

    public void spinDown(){
        pidToTargetSpeed(0.0);
    }    
}
