package frc.lib.constants;

import javax.sound.sampled.Line;

import org.ejml.LinearSolverToSparse;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.system.LinearSystem;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpiutil.math.VecBuilder;
import edu.wpi.first.wpiutil.math.Vector;
import edu.wpi.first.wpiutil.math.numbers.N2;
import edu.wpi.first.wpiutil.math.numbers.N7;

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
public interface DriveConstants {
    /**
     * The DifferentialDriveKinematics for the drivebase, based on the track width.
     */
    /*protected DifferentialDriveKinematics kDifferentialDriveKinematics = new DifferentialDriveKinematics(getkTrackWidthMeters());*/
    /**
     * The drivetrain model, based on the characterization constants.
     */
    /*protected LinearSystem<N2, N2, N2> kDrivetrainPlant = 
        LinearSystemId.identifyDrivetrainSystem(
        getKvVoltSecondsPerMeter(),
        getKaVoltSecondsSquaredPerMeter(),
        getKvVoltSecondsPerRadian(),
        getKaVoltSecondsSquaredPerRadian());*/
    /**
     * The custom axis ID that will be used for the driving forward/back speed.
     * @return the axis id, defaults to 33
     */
    int getDriveControllerFwdBackAxis();
    /**
     * The custom axis ID that will be used for the driving turning.
     * @return the axis id, defaults to 34
     */
    int getDriveControllerLeftRightAxis() ;
    /** The CAN ID for the left master motor controller. */
    int getCanIDLeftDriveMaster();
    boolean getLeftEncoderReversed();
    int[] getLeftEncoderPorts();
    /** The CAN ID for the right master motor controller. */
    int getCanIDRightDriveMaster();
    boolean getRightEncoderReversed();
    int[] getRightEncoderPorts();
    /** The CAN ID for the left follower motor controller. */
    int getCanIDLeftDriveFollower();

    /** The CAN ID for the right follower motor controller. */
    int getCanIDRightDriveFollower();

    /** Whether or not the gyro is reversed */
    boolean getGyroReversed();

    /** The number of encoder counts per encoder revolution. */
    double getEncoderCountsPerEncoderRevolution();

    /**
     * The number of encoder counts per wheel revolution (7 encoder revolutions per
     * 3 wheel revolutions).
     */
    double getEncoderCountsPerWheelRevolution();/* {
        return getEncoderCountsPerEncoderRevolution() *
        getEncoderRevolutionsPerWheelRevolution();}*/

    double getEncoderRevolutionsPerWheelRevolution();
    double getEncoderDistancePerPulse(); /* {
        return (getkWheelDiameter() * Math.PI) / (double) getEncoderCountsPerEncoderRevolution();
    }*/
    // Drive characterization DriveConstants

    double getKsVolts();
    double getKaVoltSecondsSquaredPerMeter();
    double getKvVoltSecondsPerRadian();
    double getKaVoltSecondsSquaredPerRadian();
    double getkWheelDiameter();
    double getKvVoltSecondsPerMeter();
    double getkPDriveVel();
    double getkPDriveVelLeft();
    double getkPDriveVelRight();
    double getkTrackWidthMeters();
    DifferentialDriveKinematics getDifferentialDriveKinematics(); /* {
        return kDifferentialDriveKinematics;
    }*/
    LinearSystem<N2, N2, N2> getDrivetrainPlant();/*{
        return kDrivetrainPlant;
    }*/
    DCMotor getDriveGearbox();

    double getDriveGearingRatio();

    Vector<N7> getSimEncoderStdDev();

       /**
     * 
     * @return The value to multiply the drive controller forward back axis by 
     */
    int getDriveControllerFwdBackAxisMultiplier();
    /**
     * 
     * @return The value to multiply the drive controller turning axis by
     */
    int getDriveControllerLeftRightAxisMultiplier();

    /**
     * 
     */
    boolean getDrivebaseRightSideInverted();
    
}   
