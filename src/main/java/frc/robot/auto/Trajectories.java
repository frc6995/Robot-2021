// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator.ControlVectorList;
import frc.lib.constants.AutoConstants;

import java.util.List;

import javax.xml.crypto.dsig.Transform;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;

/** Add your docs here. */
public class Trajectories {
    private static TrajectoryConfig config;
    //public static Trajectory exampleTrajectory;
    public static Trajectory straightTrajectory;
    public static Trajectory slalomTrajectory;
    public static Trajectory barrelRaceTrajectory;
    public static double fieldWidth = 4.572;
    public static void createTrajectories(TrajectoryConfig config) {
        
        /* exampleTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at (1, 2) facing the +X direction
                new Pose2d(1, 2, new Rotation2d(Math.PI/2)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(),//List.of(new Translation2d(2, 3), new Translation2d(3, 1)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(4, 2, new Rotation2d(Math.PI)), config
                // Pass config
                ); */

        straightTrajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(0, 5, new Rotation2d(0)),
                new Pose2d(2, 4, new Rotation2d(0)),
                new Pose2d(4, 5, new Rotation2d(0)))
                , config.setEndVelocity(0)
            );  
        slalomTrajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                 new Pose2d(0.763,-3.912 + fieldWidth, new Rotation2d(Math.atan2(0, 3.048)))
                ,new Pose2d(2.121,-3.658 + fieldWidth, new Rotation2d(Math.atan2(0.45, 0.813)))
                ,new Pose2d(3.124,-2.236 + fieldWidth, new Rotation2d(Math.atan2(0.617, 0.878)))
                ,new Pose2d(4.724,-1.941 + fieldWidth, new Rotation2d(Math.atan2(-0.065,1.082)))
                ,new Pose2d(6.443, -2.54 + fieldWidth, new Rotation2d(Math.atan2(-0.453, 0.325)))
                ,new Pose2d(7.934, -3.925 + fieldWidth, new Rotation2d(Math.atan2(0, 1.5)))
                ,new Pose2d(8.735, -2.973 + fieldWidth, new Rotation2d(Math.PI/2))
                ,new Pose2d(7.841, -2.028 + fieldWidth, new Rotation2d(Math.atan2(0, -1.5)))
                ,new Pose2d(6.443, -3.658 + fieldWidth, new Rotation2d(Math.atan2(-0.510, -1.061)))
                ,new Pose2d(4.724, -3.924 + fieldWidth, new Rotation2d(Math.atan2(-0.028, -1.041)))
                ,new Pose2d(2.962, -3.681 + fieldWidth, new Rotation2d(Math.atan2(0.383, -0.801)))
                ,new Pose2d(1.889, -2.132 + fieldWidth, new Rotation2d(Math.atan2(0.358, -0.821)))
                ,new Pose2d(0.987, -2.086 + fieldWidth, new Rotation2d(Math.atan2(0, -3)))
                
            ), config.setEndVelocity(0));

        barrelRaceTrajectory =
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(1.129, -2.097 + fieldWidth, new Rotation2d(Math.atan2(0, 3.048))),
                new Pose2d(4.006, -2.388 + fieldWidth, new Rotation2d(Math.atan2(-0.696, 0.824))),
                new Pose2d(3.832, -3.612 + fieldWidth, new Rotation2d(Math.atan2(0, -1.421))),
                new Pose2d(3.554, -2.614 + fieldWidth, new Rotation2d(Math.atan2(0.412, 0.621))),
                new Pose2d(4.987, -2.318 + fieldWidth, new Rotation2d(Math.atan2(0.058, 1.741))),
                new Pose2d(6.565, -1.831 + fieldWidth, new Rotation2d(Math.atan2(0.412, 0.69))),
                new Pose2d(6.791, -1.187 + fieldWidth, new Rotation2d(Math.atan2(0.487, -0.389))),
                new Pose2d(6.031, -0.995 + fieldWidth, new Rotation2d(Math.atan2(-0.203, -0.719))),
                new Pose2d(5.573, -1.628 + fieldWidth, new Rotation2d(Math.atan2(-0.847, 0.191))),
                new Pose2d(5.938, -2.538 + fieldWidth, new Rotation2d(Math.atan2(-0.992, 0.528))),
                new Pose2d(7.371, -3.496 + fieldWidth, new Rotation2d(Math.atan2(-0.023, 0.644))),
                new Pose2d(8.305, -2.892 + fieldWidth, new Rotation2d(Math.atan2(1.201, -0.133))),
                new Pose2d(7.342, -2.266 + fieldWidth, new Rotation2d(Math.atan2(-0.058, -0.656))),
                new Pose2d(5.225, -2.115 + fieldWidth, new Rotation2d(Math.atan2(0.238, -1.52))),
                new Pose2d(3.507, -1.9 + fieldWidth, new Rotation2d(Math.atan2(0.1, -1.314))),
                new Pose2d(1.279, -1.871 + fieldWidth, new Rotation2d(Math.atan2(-0.046, -1.033)))
            ), config.setEndVelocity(0));
    }
}
