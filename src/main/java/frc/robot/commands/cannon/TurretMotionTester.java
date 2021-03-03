// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.cannon.CannonS;
import frc.robot.subsystems.cannon.Turret.TurretRequestedStates;

public class TurretMotionTester extends CommandBase {
  private CannonS cannonS;
  /** Creates a new TurretMotionTester. */
  public TurretMotionTester(CannonS cannon) {
    this.cannonS = cannon;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    cannonS.requestTurretState(TurretRequestedStates.MOVE_TO_SETPOINT, 45);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //cannonS.runTurretPIDWithMotionMagic();
    cannonS.runTurretPID();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return cannonS.isTurretAtSetpoint();
  }
}
