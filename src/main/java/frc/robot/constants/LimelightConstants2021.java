

package frc.robot.constants;

import frc.robot.constants.interfaces.LimelightConstants;

/**
 * The Limelight constants for the 2021 robot.
 * 
 * @author JoeyFabel
 */
public class LimelightConstants2021 implements LimelightConstants{

    @Override
    public double getTimeConstant() {
        return 0.1;
    }

    @Override
    public double getTimePeriod() {
        return 0.02;
    }

    @Override
    public double getLimelightHeight() {
        return 10;
    }

    @Override
    public double getTargetHeight() {
        return 98.25; // 8' 2.25"
    }

    @Override
    public double getMountingAngle() {
        return Math.toRadians(45);
    }

}
