// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.lib.constants.AutoConstants;

import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

/** Add your docs here. */
public class Trajectories {
    private static TrajectoryConfig config;
    public static Trajectory exampleTrajectory;
    public static void createTrajectories(TrajectoryConfig config) {
        
        exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at (1, 2) facing the +X direction
                new Pose2d(1, 2, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(2, 2), new Translation2d(3, 2)),
                // End 3 meters straight ahead of where we started, facing forward /*
                new Pose2d(4, 2, new Rotation2d(0)), config
                // Pass config
                );
    }
}
