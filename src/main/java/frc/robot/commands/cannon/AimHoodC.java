// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;

/**
 * A command that aims the hood.
 * 
 * @author JoeyFabel
 */
public class AimHoodC extends CommandBase {
  private CannonS cannon;
  private LimelightS limelight;
  
  /** Creates a new AimHoodC. */
  public AimHoodC(CannonS cannon, LimelightS limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.cannon = cannon;
    this.limelight = limelight;

    addRequirements(cannon);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // should the cannon do this in execute, or is once enough?
    cannon.moveHoodToDesiredAngle(limelight.getDistanceToTarget());
    limelight.register();
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    limelight.deregister();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {    
    return cannon.isHoodAtSetpoint();
  }
}
