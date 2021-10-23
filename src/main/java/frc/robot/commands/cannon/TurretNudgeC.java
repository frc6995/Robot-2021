// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.cannon.CannonS;
import frc.robot.subsystems.cannon.Turret;

public class TurretNudgeC extends InstantCommand {
  /** Creates a new TurretNudgeC. */
  CannonS cannon;
  double angleAdjust;
  public TurretNudgeC(double   angleAdjust, CannonS cannon) {
    this.cannon = cannon;
    addRequirements(cannon);
    // Use addRequirements() here to declare subsystem dependencies.
    this.angleAdjust = angleAdjust;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    cannon.turret.nudgeTurret(angleAdjust);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}
}
