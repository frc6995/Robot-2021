// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Transform2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.spline.Spline.ControlVector;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator.ControlVectorList;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public final class WaypointUtil {
  private WaypointUtil() {
    throw new UnsupportedOperationException("This is a utility class!");
  }

  /** The width of a standard FRC field in various units. */
  public enum FIELD_WIDTH {
      METERS(8.211),
      FEET(27.0);
      public double value;
      private FIELD_WIDTH(double value) {
        this.value = value;
      }
  }

  /**
   * Imports Path object and creates a trajectory with that info.
   *
   * @param filepath Full path to the file
   * @param config TrajectoryConfig that sets the contraints for the Trajectory to be generated
   * @param fieldWidth The y-axis width of the field in your PathWeaver length units of choice. (On a standard FRC field, this is 8.211 meters, the short dimension of the field)
   * @return Trajectory created from the waypoints in the file.
   * @throws IOException if file does not exist
   */
  public static Trajectory importPathToCubicTrajectory(String filepath, TrajectoryConfig config, double fieldWidth)
      throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
      // Read first line
      String line = reader.readLine();
      if (line == null) {
        throw new RuntimeException("PathWeaver CSV file is malformed");
      }
      if (!"X,Y,Tangent X,Tangent Y,Fixed Theta,Reversed,Name".equals(line)) {
        throw new RuntimeException("File given isn't a PathWeaver CSV file");
      }

      // Read start pose
      line = reader.readLine();
      if (line == null) {
        throw new RuntimeException("PathWeaver CSV file is missing start pose");
      }
      final Pose2d start = createPoseWaypoint(line);

      var interiorWaypoints = new ArrayList<Translation2d>();
      line = reader.readLine();
      if (line == null) {
        throw new RuntimeException(
            "PathWeaver CSV file is missing interior translations or end pose");
      }
      String nextLine = reader.readLine();
      while (nextLine != null) {
        interiorWaypoints.add(createTranslationWaypoint(line));
        line = nextLine;
        nextLine = reader.readLine();
      }

      final Pose2d end = createPoseWaypoint(line);

      return TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, end, config).transformBy(new Transform2d( new Translation2d(0.0, fieldWidth), new Rotation2d()));
    }
  }

  /**
   * Imports Path object and creates a trajectory with that info.
   *
   * @param filepath Full path to the file
   * @param config TrajectoryConfig that sets the contraints for the Trajectory to be generated
   * @param fieldWidth The y-axis width of the field in your PathWeaver length units of choice. (On a standard FRC field, this is 8.211 meters, the short dimension of the field)
   * @return Trajectory created from the waypoints in the file.
   * @throws IOException if file does not exist
   */
  public static Trajectory importPathToQuinticTrajectory(String filepath, TrajectoryConfig config, double fieldWidth)
      throws IOException {
    try (BufferedReader reader = Files.newBufferedReader(Paths.get(filepath))) {
      // Read first line
      String line = reader.readLine();
      if (line == null) {
        throw new RuntimeException("PathWeaver CSV file is malformed");
      }
      if (!"X,Y,Tangent X,Tangent Y,Fixed Theta,Reversed,Name".equals(line)) {
        throw new RuntimeException("File given isn't a PathWeaver CSV file");
      }

      var controlVectors = new ControlVectorList();
      line = reader.readLine();
      if (line == null) {
        throw new RuntimeException("PathWeaver CSV file is missing waypoints");
      }
      while (line != null) {
        controlVectors.add(createControlVector(line));
        line = reader.readLine();
      }

      return TrajectoryGenerator.generateTrajectory(controlVectors, config);
    }
  }

  private static Pose2d createPoseWaypoint(String input) {
    String[] arrOfStr = input.split(",", 0);
    return new Pose2d(
        new Translation2d(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[1])),
        new Rotation2d(Double.parseDouble(arrOfStr[2]) + FIELD_WIDTH.METERS.value, Double.parseDouble(arrOfStr[3])));
  }

  private static Translation2d createTranslationWaypoint(String input) {
    String[] arrOfStr = input.split(",", 0);
    return new Translation2d(Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[1])  + FIELD_WIDTH.METERS.value);
  }

  private static ControlVector createControlVector(String input) {
    String[] arrOfStr = input.split(",", 0);
    var x = new double[] {Double.parseDouble(arrOfStr[0]), Double.parseDouble(arrOfStr[2]), 0};
    var y = new double[] {Double.parseDouble(arrOfStr[1])  + FIELD_WIDTH.METERS.value, Double.parseDouble(arrOfStr[3]), 0};
    return new ControlVector(x, y);
  }
}