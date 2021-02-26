// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;

/** A command that uses the Limelight to find the target.
 * 
 * @author JoeyFabel
 */
public class FindTargetC extends CommandBase {
  private LimelightS limelight;
  
  /** Creates a new FindTargetC. */
  public FindTargetC(LimelightS limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.limelight = limelight;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.register();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    limelight.deregister();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return limelight.isTargetFound();
  }
}
