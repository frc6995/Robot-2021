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
public interface AutoConstants {

      /**
       * The maximum acceleration, in m/s^2
       * @return The max acceleration
       */
      public double getkMaxAccelerationMetersPerSecondSquared();
      public double getkMaxSpeedMetersPerSecond();
      public double getkRamseteB();
      public double getkRamseteZeta();

      /**
       * The SimpleMotorFeedForward object for trajectory generation on the talon.
       */
      public SimpleMotorFeedforward getTrajectoryFeedForward();
      public DifferentialDriveVoltageConstraint getAutoVoltageConstraint();
      
      public CentripetalAccelerationConstraint getAutoCentripetalConstraint();
      
      public TrajectoryConfig getTrajectoryConfig();
      
      public RamseteController getRamseteController();
}
