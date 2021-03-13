package frc.lib.wrappers.motorcontrollers;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is an encapsulation of WPI_SparkMAX that add a couple constructors
 * for forcing common settings.
 */
public class NomadSparkMax extends CANSparkMax implements NomadBaseMotor {
    /** This decides if the talon should operate in lazy mode. */
    protected boolean lazy = false;
    protected NomadBaseMotor leader = NomadNoneMotor.noneMotor;
    protected double lastPower = 0.0;
    protected ControlType lastMode = null;

    @Override
    public double get() {
        
        return (RobotBase.isReal() ? super.get() : lastPower);
    }
    /**
     * Constructs a brushless {@link CANSparkMax} with the given port.
     * 
     * @param port The CAN ID of this SparkMax
     */
    public NomadSparkMax(int port){
        this(port, MotorType.kBrushless);
    }

    /**
     * Constructs a {@link CANSparkMax}, reverts it to factory default, and sets brake mode.
     * 
     * @param port The CAN ID of this SparkMAX
     */
    public NomadSparkMax(int port, MotorType type) {
        super(port, type);
        restoreFactoryDefaults();
        setIdleMode(IdleMode.kBrake);
    }

    /**
     * Constructs a {@link CANSparkMax}, reverts it to factory default, and sets brake mode and
     * inversion status.
     * 
     * @param port     The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     */
    public NomadSparkMax(int port, MotorType type, boolean inverted) {
        this(port, type);
        setInverted(inverted);
    }

    /**
     * Constructs a {@link CANSparkMax}, reverts it to factory default, sets brake mode and
     * inversion status, and slaves it to a specified NomadSparkMAX.
     * 
     * @param port     The CAN ID of this SparkMAX.
     * @param inverted True for inverted, false if not.
     * @param leader   The NomadSparkMAX to follow.
     */
    public NomadSparkMax(int port, MotorType type, boolean inverted, NomadBaseMotor leader) {
        this(port, type, inverted);
        setLeader(leader, inverted);
    }
    
    /**
     * Check if the motor controller is lazy
     * 
     * @return Whether the motor controller is lazy
     */
    public boolean isLazy() {
        return lazy;
    }

    /**
     * Set the lazy mode
     * 
     * @param isLazy A boolean for the lazy mode, where true is lazy on
     */
    public void setLazy(boolean isLazy) {
        lazy = isLazy;
    }
    
    public NomadBaseMotor setLeader( NomadBaseMotor leader){
        setLeader(leader, false);
        return this;
    }

    public NomadBaseMotor setLeader(NomadBaseMotor leader, boolean inverted){
        this.leader = leader;
        if (leader instanceof NomadSparkMax) {
            follow((NomadSparkMax) leader, inverted);
        } else {
            throw new IllegalArgumentException(getClass().toString() + getDeviceId() + " tried to follow an incompatible controller");
        }
        return this;
    }

    @Override
    public void set(double speed) {
        // TODO Auto-generated method stub
        set(speed, ControlType.kDutyCycle);
    }

    public void set(double setpoint, ControlType type) {
        SmartDashboard.putNumber("SPARK MAX " + getDeviceId(), setpoint);
        if(!RobotBase.isReal()){
            lastPower = setpoint;
        } else
        if (lazy) {
            if (setpoint != lastPower || type != lastMode) {
                lastPower = setpoint;
                lastMode = type;
                super.getPIDController().setReference(setpoint, type);
            }
        } else {
            lastPower = setpoint;
            super.getPIDController().setReference(setpoint, type);
        }
    }
    

    @Override
    public double getActualOutputPercent() {
        
        if(RobotBase.isReal()) {
            return getAppliedOutput();
        } else {
            return lastPower;
        }
    }

    public void setOutputVoltage(double outputVolts) {
        // TODO Auto-generated method stub
        if (RobotBase.isReal()) {
            super.setVoltage(outputVolts);
        } else{
            set(outputVolts / RobotController.getBatteryVoltage());
        }
    }
}