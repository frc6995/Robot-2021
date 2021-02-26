// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.cannon.CannonS;

/**A command that preps the shooter for launching power cells.
 * 
 * @author JoeyFabel
 */
public class SpinUpShooterC extends InstantCommand {
  private CannonS cannon;
  
  /** Creates a new SpinUpShooterC. */
  public SpinUpShooterC(CannonS cannon, boolean requireCannon) {
    this.cannon = cannon;

    if(requireCannon){
      addRequirements(cannon);
    }

    // Use addRequirements() here to declare subsystem dependencies.
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cannon.pidShooterToTargetSpeed(0.8);
  }
}
