package frc.robot.commands.cannon;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel, sammcdo, benjay
 */
public class SpinUpShooterDistanceC extends CommandBase {
  private CannonS cannon;
  private LimelightS limelight;
  private double rpm = 2800;
  private double distance = 0;
  private int index = 0;
  private double offset = 0;

  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  private NetworkTableEntry speed = tab.add("Calculated Speed", 1).withWidget(BuiltInWidgets.kGraph).getEntry();

  private static double[] speeds =    {5500, 3250, 2450, 3250, 3800, 4000};
  private static double[] distances = {-15.0, 0.0, 8.0, 12.0, 14.0, 18};

  /** Creates a new SpinUpShooterC. */
  public SpinUpShooterDistanceC(CannonS cannon, LimelightS limelight, boolean requireCannon) {
    this.cannon = cannon;
    this.limelight = limelight;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  @Override
  public void initialize() {
    limelight.register();
    distance = limelight.getFilteredYOffset();
    index = getUnderId(distance, distances);
    rpm = calcSpeed(index, index+1, distance, distances, speeds);
    cannon.pidShooterToTargetSpeed(rpm);
    speed.setDouble(rpm);

    offset = cannon.turret.getTurretEncoderPosition() - (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1:0));
    cannon.turret.setSetpoint(offset);
    cannon.turret.runPID();
  }

  @Override
  public void execute() {
    distance = limelight.getFilteredYOffset();
    // if the distance is not within the closest two values anymore, recalculate
    if (!(distance > distances[index] && distance < distances[index+1])) {
      index = getUnderId(distance, distances);
    }

    // find target speed and pid to that speed
    rpm = calcSpeed(index, index+1, distance, distances, speeds);
    cannon.pidShooterToTargetSpeed(rpm);
    speed.setDouble(rpm);

    offset = cannon.turret.getTurretEncoderPosition() - (limelight.getFilteredXOffset() * (limelight.isTargetFound() ? 1:0));
    cannon.turret.setSetpoint(offset);
    cannon.turret.runPID();
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
    cannon.stopShooter();
    limelight.deregister();
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  /**
   * Calculates the RPM that the shooter should spin corresponding to the distance from   target
   *
   * @param smallerIndex The index of the number directly before the distance from               target
   * @param biggerIndex The index of the number directly after the distance from                 target
   * @param distance The distance away from the target
   * @param DISTANCES_FEET The DISTANCES_FEET array
   * @param RPMS The RPMS array
   *
   * @return returns the exact RPM depending on the distance from the target
   */ 
  public static double calcSpeed(int smallerIndex, int biggerIndex, double distance, double[] DISTANCES_FEET, double[] RPMS) {
    double smallerRPM = RPMS[smallerIndex];
    double biggerRPM = RPMS[biggerIndex];
    double smallerDistance = DISTANCES_FEET[smallerIndex];
    double biggerDistance = DISTANCES_FEET[biggerIndex];
    
    double newRPM = (biggerRPM - smallerRPM) / (biggerDistance - smallerDistance) * (distance - smallerDistance) + smallerRPM;

    return newRPM;
  }

  public static int getUnderId(double distance, double[] DISTANCES_FEET) {
    int index = 0;
    for (int i = 0; i < DISTANCES_FEET.length; i++) {
      if (distance > DISTANCES_FEET[i]) {
        index = i;
      }
    }
    return index;
  }
}
