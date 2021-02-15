package frc.robot.constants;

/** An interface containing constants for the Turret
 * 
 * @author JoeyFabel
 */
public interface TurretConstants {
    /**
     * The allowed margin of error when checking if the Turret is at its setpoint.
     * The Turret must be withing setpoint - marginOfError < TurretPosition < setpoint + marginOfError
     */
    double getMarginOfError();
    /**
     * The PID Proportional constant
     */
    double getKP();
    /**
     * The PID Integral constant
     */
    double getKI();
    /**
     * The PID Derivative constant
     */
    double getKD();
    /**
     * The PID Feed Forward constant
     */
    double getKFF();
    /**
     * The PID IZone constant
     */
    double getIZone();
    /**
     * The number of encoder ticks per degree traveled on the Turret
     */
    double getEncoderTicksPerDegree();
    /**
     * The encoder count when the Turret is in the homed postion
     */
    double getHomePosition();
    /**
     * The possible range of motion of the Turret, in degrees
     */
    double getSoftLimit();
    /**
     * The Spark Max's port ID
     */
    int getSparkMaxPortID();
    /**
     * The magnetic limit switch's channel ID
     */
    int getLimitSwitchChannelID();
    /**
     * The minimum number of counts that the Turret must be within the margin of error of the setpoint to count as at the setpoint.
     */
    int getMinCountsAtSetpoint();
}
