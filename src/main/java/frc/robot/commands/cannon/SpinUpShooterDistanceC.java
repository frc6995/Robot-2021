package frc.robot.commands.cannon;

import java.util.Map;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.WidgetType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel
 */
public class SpinUpShooterDistanceC extends CommandBase {
  private CannonS cannon;
  private LimelightS limelight;
  private double rpm = 2800;
  private ShuffleboardTab tab = Shuffleboard.getTab("Shooter");
  private NetworkTableEntry speed = tab.add("Calculated Speed", 1).withWidget(BuiltInWidgets.kGraph).getEntry();
  private NetworkTableEntry limelightDistance = tab.add("Limelight Distance", 1)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 30))
        .getEntry();

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
    //double speed = cannon.hood.getAngleBasedOnDistance(limelight.getDistanceToTarget());
    double speed = cannon.hood.getAngleBasedOnDistance(limelightDistance.getDouble(1));
    cannon.pidShooterToTargetSpeed(speed);
  }

  @Override
  public void execute() {
    // cannon.pidShooterToTargetSpeed(rpm);
    //double speed = cannon.hood.getAngleBasedOnDistance(limelight.getDistanceToTarget());
    double speed = cannon.hood.getAngleBasedOnDistance(limelightDistance.getDouble(1));
    cannon.pidShooterToTargetSpeed(speed);
  }

  @Override
  public void end(boolean interrupted) {
    super.end(interrupted);
  }

  @Override
  public boolean isFinished() {
    return cannon.isShooterAtSpeed();
  }
}
