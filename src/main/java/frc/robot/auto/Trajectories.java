package frc.robot.auto;

import java.io.IOException;
import java.util.List;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.auto.WaypointUtil.FIELD_WIDTH;

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
    public static Trajectory leftTurnTrajectory;

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
                ,new Pose2d(2.121,-3.658 + fieldWidth, new Rotation2d(Math.atan2(0.627, 0.336)))
                ,new Pose2d(3.124,-2.236 + fieldWidth, new Rotation2d(Math.atan2(0.617, 0.878)))
                ,new Pose2d(4.546,-2.086 + fieldWidth, new Rotation2d(Math.atan2(0.0, 1.0)))
                ,new Pose2d(6.588, -2.283 + fieldWidth, new Rotation2d(Math.atan2(-0.4, 0.209)))
                ,new Pose2d(7.934, -3.925 + fieldWidth, new Rotation2d(Math.atan2(0, 1.5)))
                ,new Pose2d(8.735, -2.973 + fieldWidth, new Rotation2d(Math.PI/2))
                ,new Pose2d(7.452, -2.103 + fieldWidth, new Rotation2d(Math.atan2(-0.273, -0.801)))
                ,new Pose2d(6.704, -2.857 + fieldWidth, new Rotation2d(Math.atan2(-0.603, -0.133)))
                ,new Pose2d(6.402, -3.687 + fieldWidth, new Rotation2d(Math.atan2(-0.603, -0.545)))
                ,new Pose2d(4.724, -3.924 + fieldWidth, new Rotation2d(Math.atan2(-0.028, -1.041)))
                ,new Pose2d(2.48, -3.722 + fieldWidth, new Rotation2d(Math.atan2(0.58, -0.598)))
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
                new Pose2d(1, -2.219 + fieldWidth, new Rotation2d(Math.atan2(-0.005, 0.44))),
                new Pose2d(1.894, -2.097 + fieldWidth, new Rotation2d(Math.atan2(0.081, 0.209))),
                new Pose2d(2.289, -1.616 + fieldWidth, new Rotation2d(Math.atan2(0.249, 0.075))),
                new Pose2d(2.335, -1.000 + fieldWidth, new Rotation2d(Math.atan2(1.0, 0.0)))
            ), config.setEndVelocity(0));

        bounce2Trajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(2.335, -1.000 + fieldWidth, new Rotation2d(Math.atan2(1.0, 0.0))),
                new Pose2d(2.881, -2.376 + fieldWidth, new Rotation2d(Math.atan2(-0.966, 0.406)).plus(new Rotation2d(Math.PI))),
                new Pose2d(3.055, -2.910 + fieldWidth, new Rotation2d(Math.atan2(-0.458, 0.0)).plus(new Rotation2d(Math.PI))),
                new Pose2d(3.559, -3.896 + fieldWidth, new Rotation2d(Math.atan2(-0.041, 0.621)).plus(new Rotation2d(Math.PI))),
                new Pose2d(4.285, -3.722 + fieldWidth, new Rotation2d(Math.atan2(0.321, 0.444)).plus(new Rotation2d(Math.PI))),
                new Pose2d(4.539, -2.619 + fieldWidth, new Rotation2d(Math.atan2(0.400, 0.023)).plus(new Rotation2d(Math.PI))),
                new Pose2d(4.539, -1.755 + fieldWidth, new Rotation2d(Math.atan2(0.597, 0.003)).plus(new Rotation2d(Math.PI))),
                new Pose2d(4.551, -1.1 + fieldWidth, new Rotation2d(Math.atan2(-1.0, 0.0)))
        ), config.setEndVelocity(0).setReversed(true));

        bounce3Trajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(4.551, -1.1 + fieldWidth, new Rotation2d(Math.atan2(-1.0, 0.0))),
                new Pose2d(4.586, -2.179 + fieldWidth, new Rotation2d(Math.atan2(-1.0, 0.0))),
                new Pose2d(4.708, -3.716 + fieldWidth, new Rotation2d(Math.atan2(-0.737, 0.534))),
                new Pose2d(6.721,  -3.78 + fieldWidth, new Rotation2d(Math.atan2(0.278, 0.186))),
                new Pose2d(6.803, -2.667 + fieldWidth, new Rotation2d(Math.atan2(1, 0.0))),
                new Pose2d(6.889, -1.000 + fieldWidth, new Rotation2d(Math.atan2(1, 0.0)))
        ), config.setEndVelocity(0).setReversed(false));

        bounce4Trajectory = 
            TrajectoryGenerator.generateTrajectory(List.of(
                new Pose2d(6.889, -1 + fieldWidth, new Rotation2d(Math.atan2(1.0, 0.0))),
                new Pose2d(7.133, -2.039 + fieldWidth, new Rotation2d(Math.atan2(-0.522, 0.249)).plus(new Rotation2d(Math.PI))),
                new Pose2d(7.725, -2.512 + fieldWidth, new Rotation2d(Math.PI))
        ), config.setEndVelocity(0).setReversed(true));

        try {
            leftTurnTrajectory = 
            WaypointUtil.importPathToQuinticTrajectory(Filesystem.getDeployDirectory() + "/PathWeaver/Paths/Unnamed.path", config.setEndVelocity(0).setReversed(false), FIELD_WIDTH.METERS.value);
            System.out.println(leftTurnTrajectory);
        } catch(IOException e) {
            System.out.println("TRAJECTORY NOT FOUND: " + e.getMessage());
            System.exit(8);
        }
       
    }}
