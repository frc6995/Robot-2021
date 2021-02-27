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
    public static Trajectory exampleTrajectory;

    public static void createTrajectories(TrajectoryConfig config) {

        exampleTrajectory = TrajectoryGenerator.generateTrajectory(
                // Start at (1, 2) facing the +X direction
                new Pose2d(1, 2, new Rotation2d(Math.PI / 2)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(), /* List.of(new Translation2d(2, 3), new Translation2d(3, 1)), */
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(4, 2, new Rotation2d(0)), config
        // Pass config
        );
    }
}
