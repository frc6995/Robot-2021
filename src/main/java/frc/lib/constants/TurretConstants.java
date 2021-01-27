package frc.lib.constants;

/** Add your docs here. */
public interface TurretConstants {
    /**
     * The allowed margin of error when checking if the Turret is at its setpoint.
     * The Turret must be withing setpoint - marginOfError < TurretPosition < setpoint + marginOfError
     */
    public double getMarginOfError();
    /**
     * The PID Proportional constant
     */
    public double getKP();
    /**
     * The PID Integral constant
     */
    public double getKI();
    /**
     * The PID Derivative constant
     */
    public double getKD();
    /**
     * The PID Feed Forward constant
     */
    public double getKFF();
    /**
     * The number of encoder ticks per degree traveled on the Turret
     */
    public double getEncoderTicksPerDegree();
    /**
     * The encoder count when the Turret is in the homed postion
     */
    public double getHomePosition();
    /**
     * The possible range of motion of the Turret, in degrees
     */
    public double getSoftLimit();
    /**
     * The Spark Max's port ID
     */
    public int getSparkMaxPortID();
    /**
     * The magnetic limit switch's channel ID
     */
    public int getLimitSwitchChannelID();
}
