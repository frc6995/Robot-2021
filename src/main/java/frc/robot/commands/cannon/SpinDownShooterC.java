// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.cannon.CannonS;

/** A command that stops the Shooter using PID.
 * 
 * @author JoeyFabel
 */
public class SpinDownShooterC extends InstantCommand {
  private CannonS cannon;
  
  /** Creates a new SpinDownShooterC. */
  public SpinDownShooterC(CannonS cannon, boolean requireCannon) {
    this.cannon = cannon;

    if(requireCannon){
      addRequirements(cannon);
    }

    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cannon.spinDownShooter();
  }
}
