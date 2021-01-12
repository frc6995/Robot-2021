package frc.robot.wrappers.motorcontrollers;

import com.revrobotics.CANSparkMax;

/**
 * This class is an encapsulation of WPI_SparkMAX that add a couple constructors
 * for forcing common settings.
 */
public class NomadSparkMaxBrushed<LeaderType extends NomadBaseMotor> extends CANSparkMax implements NomadBaseMotor {

    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode.
     * 
     * @param port The CAN ID of this SparkMAX
     */
    public NomadSparkMaxBrushed(int port) {
        super(port, MotorType.kBrushed);
        restoreFactoryDefaults();
        setIdleMode(IdleMode.kBrake);
    }

    /**
     * Constructs a SparkMAX, reverts it to factory default, and sets brake mode and
     * inversion status.
     * 
     * @param port     The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     */
    public NomadSparkMaxBrushed(int port, boolean inverted) {
        this(port);
        setInverted(inverted);
    }

    /**
     * Constructs a SparkMAX, reverts it to factory default, sets brake mode and
     * inversion status, and slaves it to a specified NomadSparkMAX.
     * 
     * @param port     The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     * @param master   The NomadSparkMAX to follow.
     */
    public NomadSparkMaxBrushed(int port, boolean inverted, NomadSparkMaxBrushed<NomadNoneMotor> master) {
        this(port, inverted);
        follow(master);
    }
    @SuppressWarnings("unchecked")
    public void setLeader( NomadBaseMotor leader){
        if (leader instanceof NomadSparkMaxBrushed) {
            follow((NomadSparkMaxBrushed<NomadNoneMotor>) leader);
        }
        else if (leader instanceof NomadNoneMotor) {
            System.out.println("NomadSparkMaxBrushed tried to follow NomadNoneMotor, skipping...");
        }
        else throw new IllegalArgumentException("NomadSparkMaxBrushed can only follow a NomadSparkMaxBrushed!");
    }

    @Override
    public boolean isLazy() {
        
        return false;
    }

    @Override
    public void setLazy(boolean isLazy) {
        

    }
}
