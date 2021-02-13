// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.super_shooter;

import java.util.logging.Level;
import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.shooter.SuperShooterS;
import frc.robot.subsystems.shooter.Turret.TurretRequestedStates;

/** A command that homes the SuperShooter.
 * 
 * @author JoeyFabel
 */
public class HomeSuperShooterC extends CommandBase {
  private SuperShooterS superShooter;

  /** Creates a new HomeSuperShooterC.   */
  public HomeSuperShooterC(SuperShooterS superShooter, double timeout) {
    this.withTimeout(2.0);  
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(superShooter);

    this.superShooter = superShooter;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    superShooter.requestTurretState(TurretRequestedStates.HOME, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (!superShooter.isTurretHomed()) Logger.getAnonymousLogger().log(Level.WARNING, "Turret Home timed-out");
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return superShooter.isTurretHomed();
  }
}
