// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.system.LinearSystem;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpiutil.math.Vector;
import edu.wpi.first.wpiutil.math.numbers.N2;
import edu.wpi.first.wpiutil.math.numbers.N7;
import frc.lib.constants.DriveConstants;

/** Add your docs here. */
public class DriveConstants2021 implements DriveConstants {

    protected DifferentialDriveKinematics kDifferentialDriveKinematics
    = new DifferentialDriveKinematics(getkTrackWidthMeters());
    protected LinearSystem<N2, N2, N2> kDrivetrainPlant = 
        LinearSystemId.identifyDrivetrainSystem(
        getKvVoltSecondsPerMeter(),
        getKaVoltSecondsSquaredPerMeter(),
        getKvVoltSecondsPerRadian(),
        getKaVoltSecondsSquaredPerRadian());
    @Override
    public int getDriveControllerFwdBackAxis() {
        return 33;
    }

    @Override
    public boolean getLeftDriveLeaderInverted() {
        return false;
    }

    @Override
    public int getDriveControllerLeftRightAxis() {
        return 34;
    }
    @Override
    public int getCanIDLeftDriveMaster() {
        return 12;
    }

    @Override
    public boolean getLeftEncoderReversed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int[] getLeftEncoderPorts() {
        int[] ports = { 0, 1 };
        return ports;
    }
    @Override
    public int getCanIDRightDriveMaster() {
        return 10;
    }

    @Override
    public boolean getRightEncoderReversed() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int[] getRightEncoderPorts() {
        // TODO Auto-generated method stub
        int[] ports = { 2, 3 };
        return ports;
    }
    @Override
    public boolean getRightDriveLeaderInverted() {
        return true;
    }

    @Override
    public int getCanIDLeftDriveFollower() {
        return 13;
    }

    @Override
    public boolean getLeftDriveFollowerInverted() {
        return false;
    }

    @Override
    public int getCanIDRightDriveFollower() {
        return 14;
    }

    @Override
    public boolean getRightDriveFollowerInverted() {
        return false;
    }

    @Override
    public boolean getGyroReversed() {
        return false;
    }

    @Override
    public double getEncoderCountsPerEncoderRevolution() {
        return 42 * 4; //Greyhill
    }

    @Override
    public double getEncoderCountsPerWheelRevolution() {
        return getEncoderCountsPerEncoderRevolution() *
        getEncoderRevolutionsPerWheelRevolution();
    }

    @Override
    public double getEncoderRevolutionsPerWheelRevolution() {
        return 7.66667;
    }

    @Override
    public double getEncoderDistancePerPulse() {
        return 1/672.5;
        //return (getkWheelDiameter() * Math.PI) / (double) getEncoderCountsPerEncoderRevolution();
    }

    @Override
    public double getKsVolts() {
        return 0.22;
    }

    @Override
    public double getKaVoltSecondsSquaredPerMeter() {
        return 0.2;
    }

    @Override
    public double getKvVoltSecondsPerRadian() {
        return 1.5;
    }

    @Override
    public double getKaVoltSecondsSquaredPerRadian() {
        return 0.3;
    }

    @Override
    public double getkWheelDiameter() {
        return Units.inchesToMeters(6);
    }

    @Override
    public double getKvVoltSecondsPerMeter() {
        return 1.98;
    }

    @Override
    public double getkPDriveVel() {
        return 0.000294;
    }

    @Override
    public double getkPDriveVelLeft() {
        return getkPDriveVel();
    }

    @Override
    public double getkPDriveVelRight() {
        return getkPDriveVel();
    }

    @Override
    public double getkTrackWidthMeters() {
        return 0.69;
    }

    @Override
    public DifferentialDriveKinematics getDifferentialDriveKinematics() {
        return kDifferentialDriveKinematics;
    }

    @Override
    public LinearSystem<N2, N2, N2> getDrivetrainPlant() {
        return kDrivetrainPlant;
    }

    @Override
    public DCMotor getDriveGearbox() {
        return DCMotor.getNEO(2);
    }

    @Override
    public Vector<N7> getSimEncoderStdDev() {
        return VecBuilder.fill(0, 0, 0, 0, 0, 0, 0);//VecBuilder.fill(0.0001, 0.0001, 0.0001, 0.01, 0.01, 0.0005, 0.0005);
    }

    @Override
    public double getDriveControllerFwdBackAxisMultiplier() {
        return 0.75;
    }

    @Override
    public double getDriveControllerLeftRightAxisMultiplier() {
        return -0.5;
    }

    @Override
    public boolean getDrivebaseRightSideInverted() {
        return false;
    }

    public SimpleMotorFeedforward getArbitraryFeedforward(){
        return new SimpleMotorFeedforward(0.303, 2.02, 0.0468);
    }
}
