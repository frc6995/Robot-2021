package frc.robot.constants;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * The DriveConstants class provides a convenient place for teams to hold
 * robot-wide numerical or boolean DriveConstants. This class should not be used
 * for any other purpose. All DriveConstants should be declared globally (i.e.
 * public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the DriveConstants are needed, to reduce verbosity.
 * 
 * @author Shueja
 */
public abstract class DriveConstants {
    protected DifferentialDriveKinematics differentialDriveKinematics;

    public abstract int getDriveControllerFwdBackAxis();
    public abstract int getDriveControllerLeftRightAxis();
    /** The CAN ID for the left master motor controller. */
    public abstract int getCanIDLeftDriveMaster();

    /** The CAN ID for the right master motor controller. */
    public abstract int getCanIDRightDriveMaster();

    /** The CAN ID for the left follower motor controller. */
    public abstract int getCanIDLeftDriveFollower();

    /** The CAN ID for the right follower motor controller. */
    public abstract int getCanIDRightDriveFollower();

    /** Whether or not the gyro is reversed */
    public abstract boolean getGyroReversed();

    /** The number of encoder counts per encoder revolution. */
    public abstract double getEncoderCountsPerEncoderRevolution();

    /**
     * The number of encoder counts per wheel revolution (7 encoder revolutions per
     * 3 wheel revolutions).
     */
    public abstract double getEncoderCountsPerWheelRevolution();

    // Drive characterization DriveConstants

    public abstract double getKsVolts();
    public abstract double getKvVoltSecondsPerMeter();
    public abstract double getKaVoltSecondsSquaredPerMeter();
    public abstract double getkWheelDiameter();

    public abstract double getkPDriveVel();
    public abstract double getkPDriveVelLeft();
    public abstract double getkPDriveVelRight();
    public abstract double getkTrackWidthMeters();
    public abstract DifferentialDriveKinematics getDifferentialDriveKinematics();
}
