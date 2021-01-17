package frc.template.constants;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * Drive Constants for our 2020 bot KRen
 */
public final class DriveConstantsKRen extends DriveConstants {
    @Override
    /** The CAN ID for the left master motor controller. */
    public int getCanIDLeftDriveMaster() {
        return 10;}

    @Override
    /** The CAN ID for the right master motor controller. */
    public int getCanIDRightDriveMaster() {return 11;}

    @Override
    public int getCanIDLeftDriveFollower() {
        return 13;
    }

    @Override
    public int getCanIDRightDriveFollower() {
        return 12;
    }

    @Override
    public boolean getGyroReversed() {
        return true;
    }

    @Override
    public double getEncoderCountsPerEncoderRevolution() {
        
        return 1024;
    }

    @Override
    public double getEncoderCountsPerWheelRevolution() {
        
        return getEncoderCountsPerEncoderRevolution() * 7.0 / 3.0;
    }

    @Override
    public double getKsVolts() {
        
        return 1.26;
    }

    @Override
    public double getKvVoltSecondsPerMeter() {
        return 2.19;
    }

    @Override
    public double getKaVoltSecondsSquaredPerMeter() {
        return 0.683;
    }

    @Override
    public double getkWheelDiameter() {
        return 0.1524;
    }

    @Override
    public double getkPDriveVel() {
        return 0.0405;
    }

    @Override
    public double getkPDriveVelLeft() {
        return 0.0405;
    }

    @Override
    public double getkPDriveVelRight() {
        return 0.0405;
    }

    @Override
    public double getkTrackWidthMeters() {
        return 0.6032;
    }

    public final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
            getkTrackWidthMeters());

    @Override
    public DifferentialDriveKinematics getDifferentialDriveKinematics() {
        return kDriveKinematics;
    }

    @Override
    public int getDriveControllerFwdBackAxis() {
        
        return 33;
    }

    @Override
    public int getDriveControllerLeftRightAxis() {
        
        return 34;
    }
}
