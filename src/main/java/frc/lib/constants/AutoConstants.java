package frc.lib.constants;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.lib.constants.DriveConstants;

/**
 * The constants for the Autonomous
 * 
 * @author Sammcdo, EliSauder, JoeyFabel, Shueja, AriShashivkopanazak
 */
public abstract class AutoConstants {
      protected SimpleMotorFeedforward TRAJECTORY_FEED_FORWARD;
      protected DifferentialDriveVoltageConstraint AUTO_VOLTAGE_CONSTRAINT;
      protected CentripetalAccelerationConstraint CENTRIPETAL_ACCELERATION_CONSTRAINT;
      protected TrajectoryConfig TRAJECTORY_CONFIG;
      protected RamseteController RAMSETE_CONTROLLER;
      protected DriveConstants driveConstants;
      public AutoConstants(DriveConstants drivebaseConstants){
            driveConstants = drivebaseConstants;
      }
      public abstract double getkMaxAccelerationMetersPerSecondSquared();
      public abstract double getkMaxSpeedMetersPerSecond();
      public abstract double getkRamseteB();
      public abstract double getkRamseteZeta();

      /**
       * The SimpleMotorFeedForward object for trajectory generation on the talon.
       */
      public SimpleMotorFeedforward getTrajectoryFeedForward() {
            return TRAJECTORY_FEED_FORWARD;
      }
      
      public DifferentialDriveVoltageConstraint getAutoVoltageConstraint() {
            return AUTO_VOLTAGE_CONSTRAINT;
      }
      
      public CentripetalAccelerationConstraint getAutoCentripetalConstraint() {
            return CENTRIPETAL_ACCELERATION_CONSTRAINT;
      }
      
      public TrajectoryConfig getTrajectoryConfig() {
            return TRAJECTORY_CONFIG;
      }
      
      public RamseteController getRamseteController() {
            return RAMSETE_CONTROLLER;
      }
}
