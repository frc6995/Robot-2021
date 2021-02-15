// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.cannon.CannonS;

/** A command that stops the shooter using PID.
 * 
 * @author JoeyFabel
 */
public class SpinDownShooterC extends CommandBase {
  private CannonS cannon;
  
  /** Creates a new SpinDownShooterC. */
  public SpinDownShooterC(CannonS cannon) {
    this.cannon = cannon;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(cannon);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cannon.spinDownShooter();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cannon.isShooterOff();
  }
}
