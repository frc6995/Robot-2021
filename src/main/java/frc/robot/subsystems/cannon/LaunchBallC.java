// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColumnS;

public class LaunchBallC extends CommandBase {
  private CannonS cannon;
  private ColumnS column;
  private int numBallsToLaunch;
  private int numBallsLaunched;
  
  /** Creates a new LaunchBallC that launches 5 balls. */
  public LaunchBallC(ColumnS column, CannonS cannon) {
    this(column, cannon, 5);
  }
  
  /**
   * Creates a new LaunchBallC that launches any number of balls.
   * @param column The Column Subsystem
   * @param cannon The Cannon Subsystem
   * @param numBalls The number of balls to launch
   */
  public LaunchBallC(ColumnS column, CannonS cannon, int numBalls){
    this.cannon = cannon;
    this.column = column;
    numBallsToLaunch = numBalls;
    numBallsLaunched = 0;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cannon);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    column.spinAcceleratorUpwards();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // If this doesn't work, the command could be run for a set time or indefinitely until the command is manually ended
    if (!cannon.isShooterVoltageNormal()) numBallsLaunched++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    column.stopAccelerator();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return numBallsLaunched == numBallsToLaunch;
  }
}
