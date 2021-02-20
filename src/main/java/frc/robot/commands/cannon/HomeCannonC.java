// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.cannon.CannonS;
import frc.robot.subsystems.cannon.Turret.TurretRequestedStates;

/** A command that homes the SuperShooter.
 * 
 * @author JoeyFabel
 */
public class HomeCannonC extends CommandBase {
  private CannonS cannon;

  /** Creates a new HomeSuperShooterC.   */
  public HomeCannonC(CannonS cannon, double timeout, boolean requireCannon) {
    this.withTimeout(timeout);  
    
    this.cannon = cannon;

    if(requireCannon) {
      addRequirements(cannon);
    }
  }

  public HomeCannonC(CannonS cannon, double timeout) {
    this(cannon, timeout, true);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cannon.requestTurretState(TurretRequestedStates.HOME, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (!cannon.isTurretHomed()) Logger.getAnonymousLogger().log(Level.WARNING, "Turret Home timed-out");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cannon.isTurretHomed();
  }
}
