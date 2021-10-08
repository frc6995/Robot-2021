// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

/** Add your docs here. */
public class AutoConstants2021 implements AutoConstants {
          /**
       * The SimpleMotorFeedForward for the drivebase gearboxes.
       */
      protected SimpleMotorFeedforward TRAJECTORY_FEED_FORWARD;
      /**
       * The voltage constraint for the drive motors.
       */
      protected DifferentialDriveVoltageConstraint AUTO_VOLTAGE_CONSTRAINT;
      /**
       * The centripetal acceleration constraint for the drive motors.
       */
      protected CentripetalAccelerationConstraint CENTRIPETAL_ACCELERATION_CONSTRAINT;
      /**
       * The trajectory following config for the drivebase
       */
      protected TrajectoryConfig TRAJECTORY_CONFIG;
      /**
       * The Ramsete controller for trajectory following.
       */
      protected RamseteController RAMSETE_CONTROLLER;
      /**
       * The DriveConstants to provide info about the drivebase.
       */
      protected DriveConstants driveConstants;
    public AutoConstants2021(DriveConstants drivebaseConstants) {
        driveConstants = drivebaseConstants;
        CENTRIPETAL_ACCELERATION_CONSTRAINT = new CentripetalAccelerationConstraint(5);

        TRAJECTORY_FEED_FORWARD = driveConstants.getArbitraryFeedforward();/*new SimpleMotorFeedforward(
            driveConstants.getKsVolts(),
            driveConstants.getKvVoltSecondsPerMeter(),
            driveConstants.getKaVoltSecondsSquaredPerMeter());*/

        AUTO_VOLTAGE_CONSTRAINT =
        new DifferentialDriveVoltageConstraint(
            TRAJECTORY_FEED_FORWARD,
            driveConstants.getDifferentialDriveKinematics(),
            10.5);

    // Create config for trajectory
        TRAJECTORY_CONFIG =
        new TrajectoryConfig(
                getkMaxSpeedMetersPerSecond(),
                getkMaxAccelerationMetersPerSecondSquared())
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(driveConstants.getDifferentialDriveKinematics())
            // Apply the voltage constraint
            .addConstraint(AUTO_VOLTAGE_CONSTRAINT)
            .addConstraint(CENTRIPETAL_ACCELERATION_CONSTRAINT);

        RAMSETE_CONTROLLER = new RamseteController(getkRamseteB(), getkRamseteZeta());

    // An example trajectory to follow.  All units in meters.
    
    }

    @Override
    public double getkMaxAccelerationMetersPerSecondSquared() {
        
        return 1;
    }

    @Override
    public double getkMaxSpeedMetersPerSecond() {
        
        return 2;
    }

    @Override
    public double getkRamseteB() {
        
        return 2;
    }

    @Override
    public double getkRamseteZeta() {
        
        return 0.7;
    }
    @Override
    public SimpleMotorFeedforward getTrajectoryFeedForward() {
        // TODO Auto-generated method stub
        return TRAJECTORY_FEED_FORWARD;
    }

    @Override
    public DifferentialDriveVoltageConstraint getAutoVoltageConstraint() {
        // TODO Auto-generated method stub
        return AUTO_VOLTAGE_CONSTRAINT;
    }

    @Override
    public CentripetalAccelerationConstraint getAutoCentripetalConstraint() {
        // TODO Auto-generated method stub
        return CENTRIPETAL_ACCELERATION_CONSTRAINT;
    }

    @Override
    public TrajectoryConfig getTrajectoryConfig() {
        // TODO Auto-generated method stub
        return TRAJECTORY_CONFIG;
    }

    @Override
    public RamseteController getRamseteController() {
        // TODO Auto-generated method stub
        return RAMSETE_CONTROLLER;
    }

}
