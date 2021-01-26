package frc.lib.constants;

/** Add your docs here. */
public interface TurretConstants {
    /**
     * The allowed margin of error when checking if the Turret is at its setpoint.
     * The Turret must be withing setpoint - marginOfError < TurretPosition < setpoint + marginOfError
     */
    public double marginOfError();
    /**
     * The PID Proportional constant
     */
    public double kP();
    /**
     * The PID Integral constant
     */
    public double kI();
    /**
     * The PID Derivative constant
     */
    public double kD();
    /**
     * The PID Feed Forward constant
     */
    public double kFF();
    /**
     * The number of encoder ticks per degree traveled on the Turret
     */
    public double encoderTicksPerDegree();
    /**
     * The encoder count when the Turret is in the homed postion
     */
    public double homePosition();
    /**
     * The possible range of motion of the Turret, in degrees
     */
    public double softLimit();
    /**
     * The Spark Max's port ID
     */
    public int sparkMaxPortID();
    /**
     * The magnetic limit switch's channel ID
     */
    public int limitSwitchChannelID();
}
