// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.super_shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.shooter.SuperShooterS;

/**
 * A command that aims the hood.
 * 
 * @author JoeyFabel
 */
public class AimHoodC extends CommandBase {
  SuperShooterS superShooter;
  LimelightS limelight;
  
  /** Creates a new AimHoodC. */
  public AimHoodC(SuperShooterS superShooter, LimelightS limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.superShooter = superShooter;
    this.limelight = limelight;

  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    addRequirements(superShooter);
    double distance = 5;
    // TODO - Calculate distance here
  
    superShooter.moveHoodToDesiredAngle(distance);
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
