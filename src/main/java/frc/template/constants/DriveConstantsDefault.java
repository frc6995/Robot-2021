/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.template.constants;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

/**
 * Add your docs here.
 */
public class DriveConstantsDefault extends DriveConstants {

    @Override
    public int getCanIDLeftDriveMaster() {
        
        return 0;
    }

    @Override
    public int getCanIDRightDriveMaster() {
        
        return 0;
    }

    @Override
    public int getCanIDLeftDriveFollower() {
        
        return 0;
    }

    @Override
    public int getCanIDRightDriveFollower() {
        
        return 0;
    }

    @Override
    public boolean getGyroReversed() {
        
        return false;
    }

    @Override
    public double getEncoderCountsPerEncoderRevolution() {
        
        return 0;
    }

    @Override
    public double getEncoderCountsPerWheelRevolution() {
        
        return 0;
    }

    @Override
    public double getKsVolts() {
        
        return 0;
    }

    @Override
    public double getKvVoltSecondsPerMeter() {
        
        return 0;
    }

    @Override
    public double getKaVoltSecondsSquaredPerMeter() {
        
        return 0;
    }

    @Override
    public double getkWheelDiameter() {
        
        return 0;
    }

    @Override
    public double getkPDriveVel() {
        
        return 0;
    }

    @Override
    public double getkPDriveVelLeft() {
        
        return 0;
    }

    @Override
    public double getkPDriveVelRight() {
        
        return 0;
    }

    @Override
    public double getkTrackWidthMeters() {
        
        return 0;
    }

    @Override
    public DifferentialDriveKinematics getDifferentialDriveKinematics() {
        
        return new DifferentialDriveKinematics(getkTrackWidthMeters());
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
