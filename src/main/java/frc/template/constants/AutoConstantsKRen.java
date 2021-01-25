package frc.template.constants;

import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.CentripetalAccelerationConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;

/**
 * The constants for the Autonomous
 * 
 * @author Sammcdo, EliSauder, JoeyFabel, Shueja, AriShashivkopanazak
 */
public class AutoConstantsKRen extends AutoConstants{
      public AutoConstantsKRen(final DriveConstants drivebaseConstants) {
            super(drivebaseConstants);
            //Create the objects to be returned.
            TRAJECTORY_FEED_FORWARD = new SimpleMotorFeedforward(
                  driveConstants.getKsVolts(), driveConstants.getKvVoltSecondsPerMeter(),
                  driveConstants.getKaVoltSecondsSquaredPerMeter());
            AUTO_VOLTAGE_CONSTRAINT = new DifferentialDriveVoltageConstraint(
                  getTrajectoryFeedForward(),
                  driveConstants.getDifferentialDriveKinematics(), 10);
            CENTRIPETAL_ACCELERATION_CONSTRAINT = new CentripetalAccelerationConstraint(2);
            TRAJECTORY_CONFIG = new TrajectoryConfig(
                  getkMaxSpeedMetersPerSecond(), getkMaxAccelerationMetersPerSecondSquared())
                              // Add kinematics to ensure max speed is actually obeyed
                              .setKinematics(driveConstants.getDifferentialDriveKinematics())
                              // Apply the voltage constraint
                              .addConstraint(getAutoVoltageConstraint()).addConstraint(getAutoCentripetalConstraint());
            RAMSETE_CONTROLLER = new RamseteController(getkRamseteB(),
            getkRamseteZeta());
            
      }
      @Override
      public double getkMaxAccelerationMetersPerSecondSquared() {
            return 0.5;
      }

      @Override
      public double getkMaxSpeedMetersPerSecond() {
            return 1;
      }

      @Override
      public double getkRamseteB() {
            return 2.0;
      }

      @Override
      public double getkRamseteZeta() {
            return 0.7;
      }
}
