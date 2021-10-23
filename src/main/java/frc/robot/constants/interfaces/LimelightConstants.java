

package frc.robot.constants.interfaces;

/** The {@link LimelightS} constants.
 * @author JoeyFabel
 */
public interface LimelightConstants {
    /**Get the time constant for the Linear Filter */
    double getTimeConstant();
    /**Get the time period for the Linear Filter */
    double getTimePeriod();
    /**Get the distance between the limelight and the ground */
    double getLimelightHeight();
    /**Get the distance between the ground and the target, in inches */
    double getTargetHeight();
    /**Get the mounting angle of the limelight, in radians */
    double getMountingAngle();
}
