package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.cannon.CannonS;

public class LaunchBallC extends CommandBase {
  private CannonS cannon;
  private int numBallsToLaunch;
  private int numBallsLaunched;

  /** Creates a new LaunchBallC that launches 5 balls. */
  public LaunchBallC(CannonS cannon, int numBalls) {
    this(cannon, true, numBalls);
  }

  /** Creates a new LaunchBallC that launches 5 balls. */
  public LaunchBallC(CannonS cannon, boolean requireCannon) {
    this(cannon, requireCannon, 5);
  }

  /** Creates a new LaunchBallC that launches 5 balls. */
  public LaunchBallC(CannonS cannon) {
    this(cannon, true, 5);
  }

  /**
   * Creates a new LaunchBallC that launches any number of balls.
   * 
   * @param column   The Column Subsystem
   * @param cannon   The Cannon Subsystem
   * @param numBalls The number of balls to launch
   */
  public LaunchBallC(CannonS cannon, boolean requireCannon, int numBalls) {
    this.cannon = cannon;
    numBallsToLaunch = numBalls;
    numBallsLaunched = 0;

    if (requireCannon) {
      addRequirements(cannon);
    }

  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // If this doesn't work, the command could be run for a set time or indefinitely
    // until the command is manually ended
    if (!cannon.isShooterVoltageNormal())
      numBallsLaunched++;
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;//return numBallsLaunched == numBallsToLaunch;
  }
}
