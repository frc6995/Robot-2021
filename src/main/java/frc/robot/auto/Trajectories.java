package frc.robot.auto;

import java.util.List;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

/** Add your docs here. */
public class Trajectories {
    private static TrajectoryConfig config;
    //public static Trajectory exampleTrajectory;
    public static Trajectory straightTrajectory;
    public static Trajectory slalomTrajectory;
    public static Trajectory barrelRaceTrajectory;
    public static Trajectory searchTrajectoryA;
    public static Trajectory bounce1Trajectory;
    public static Trajectory bounce2Trajectory;
    public static Trajectory bounce3Trajectory;
    public static Trajectory bounce4Trajectory;
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
                new Pose2d(4.145, -2.312 + fieldWidth, new Rotation2d(Math.atan2(-0.696, 0.824))),
                new Pose2d(3.879, -3.757 + fieldWidth, new Rotation2d(Math.atan2(0, -1.421))),
                new Pose2d(3.403, -2.596 + fieldWidth, new Rotation2d(Math.atan2(0.412, 0.621))),
                new Pose2d(4.987, -2.318 + fieldWidth, new Rotation2d(Math.atan2(0.058, 1.741))),
                new Pose2d(6.663, -1.958 + fieldWidth, new Rotation2d(Math.atan2(0.412, 0.69))),
                new Pose2d(6.895, -1.134 + fieldWidth, new Rotation2d(Math.atan2(0.487, -0.389))),
                new Pose2d(6.048, -0.815 + fieldWidth, new Rotation2d(Math.atan2(-0.203, -0.719))),
                new Pose2d(5.428, -1.546 + fieldWidth, new Rotation2d(Math.atan2(-0.528, 0.133))),
                new Pose2d(5.938, -2.538 + fieldWidth, new Rotation2d(Math.atan2(-0.992, 0.528))),
                new Pose2d(7.296, -3.658 + fieldWidth, new Rotation2d(Math.atan2(-0.023, 0.644))),
                new Pose2d(8.363, -3.037 + fieldWidth, new Rotation2d(Math.atan2(1.201, -0.133))),
                new Pose2d(7.418, -2.132 + fieldWidth, new Rotation2d(Math.atan2(-0.847, -0.07))),
                new Pose2d(5.259, -2.179 + fieldWidth, new Rotation2d(Math.atan2(0.099, -1.276))),
                new Pose2d(3.559, -2.103 + fieldWidth, new Rotation2d(Math.atan2(0.033, -1.303))),
                new Pose2d(1.349, -2.103 + fieldWidth, new Rotation2d(Math.atan2(-0.046, -1.033)))
            ), config.setEndVelocity(0));
        
        searchTrajectoryA = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(0.647, -0.821 + fieldWidth, new Rotation2d(Math.atan2(0, 3.048))),
                new Pose2d(2.242, -1.285 + fieldWidth, new Rotation2d(Math.atan2(-0.627, -0.041))),
                new Pose2d(2.318, -2.312 + fieldWidth, new Rotation2d(Math.atan2(-0.731, 0.331))),
                new Pose2d(3.797, -3.055 + fieldWidth, new Rotation2d(Math.atan2(-0.594, 0.468))),
                new Pose2d(4.116, -3.623 + fieldWidth, new Rotation2d(Math.atan2(-0.266, 0.294))),
                new Pose2d(4.655, -3.875 + fieldWidth, new Rotation2d(Math.atan2(0.005, 0.388))),
                new Pose2d(5.033, -3.536 + fieldWidth, new Rotation2d(Math.atan2(0.348, -0.075))),
                new Pose2d(4.482, -2.817 + fieldWidth, new Rotation2d(Math.atan2(0.389, -0.110))),
                new Pose2d(4.145, -1.511 + fieldWidth, new Rotation2d(Math.atan2(0.214, 0.0))),
                new Pose2d(4.331, -0.862 + fieldWidth, new Rotation2d(Math.atan2(0.220, 0.145))),
                new Pose2d(4.975, -0.717 + fieldWidth, new Rotation2d(Math.atan2(-0.551, 0.406))),
                new Pose2d(5.143, -1.163 + fieldWidth, new Rotation2d(Math.atan2(-0.180, 0.0))),
                new Pose2d(5.455, -1.556 + fieldWidth, new Rotation2d(Math.atan2(-0.052, 0.197))),
                new Pose2d(6.170, -1.575 + fieldWidth, new Rotation2d(Math.atan2(-0.182, 0.473))),
                new Pose2d(6.791, -2.254 + fieldWidth, new Rotation2d(Math.atan2(-0.498, 0.519))),
                new Pose2d(7.760, -3.032 + fieldWidth, new Rotation2d(Math.atan2(-0.277, 0.639))),
                new Pose2d(8.636, -3.229 + fieldWidth, new Rotation2d(Math.atan2(-0.012, 0.249)))
             ), config.setEndVelocity(0));
        
    bounce1Trajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(1.221, -2.219 + fieldWidth, new Rotation2d(Math.atan2(0.44, -0.005))),
                new Pose2d(1.894, -2.097 + fieldWidth, new Rotation2d(Math.atan2(0.249, 0.168))),
                new Pose2d(2.155, -1.470 + fieldWidth, new Rotation2d(Math.atan2(0.075, 0.249))),
                new Pose2d(2.312, -0.722 + fieldWidth, new Rotation2d(Math.atan2(0.011, 0.290)))
            ), config.setEndVelocity(0));

        bounce2Trajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(2.300, -0.844 + fieldWidth, new Rotation2d(Math.atan2(0.0, -1.0))),
                new Pose2d(2.567, -2.271 + fieldWidth, new Rotation2d(Math.atan2(0.358, 1.005))),
                new Pose2d(3.489, -3.814 + fieldWidth, new Rotation2d(Math.atan2(0.795, 0.076))),
                new Pose2d(4.191, -3.716 + fieldWidth, new Rotation2d(Math.atan2(0.476, 0.265))),
                new Pose2d(4.539, -2.619 + fieldWidth, new Rotation2d(Math.atan2(0.023, 0.400))),
                new Pose2d(4.539, -1.755 + fieldWidth, new Rotation2d(Math.atan2(0.003, 0.597))),
                new Pose2d(4.551, -0.826 + fieldWidth, new Rotation2d(Math.atan2(0.0, 1.0)))
        ), config.setEndVelocity(0).setReversed(true));

        bounce3Trajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(4.615, -0.751 + fieldWidth, new Rotation2d(Math.atan2(0.0, -1.0))),
                new Pose2d(4.748, -2.172 + fieldWidth, new Rotation2d(Math.atan2(0.174, 0.954))),
                new Pose2d(5.149, -3.611 + fieldWidth, new Rotation2d(Math.atan2(0.621, 0.373))),
                new Pose2d(6.28,  -3.675 + fieldWidth, new Rotation2d(Math.atan2(0.342, 0.069))),
                new Pose2d(6.686, -2.637 + fieldWidth, new Rotation2d(Math.atan2(0.116, 0.667))),
                new Pose2d(6.849, -0.832 + fieldWidth, new Rotation2d(Math.atan2(0.005, 0.435)))
        ), config.setEndVelocity(0));

        bounce4Trajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(6.889, -0.676 + fieldWidth, new Rotation2d(Math.atan2(0.0, -1.0))),
                new Pose2d(7.052, -1.911 + fieldWidth, new Rotation2d(Math.atan2(0.415, 0.471))),
                new Pose2d(7.777, -2.312 + fieldWidth, new Rotation2d(Math.PI / 2))
        ), config.setEndVelocity(0).setReversed(true));
    }}
