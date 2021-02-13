// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.super_shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.shooter.SuperShooterS;
import frc.robot.subsystems.shooter.Turret.TurretRequestedStates;

/** A command that aims the turret.
 * 
 * @author JoeyFabel
 */
public class AimTurretC extends CommandBase {
  private LimelightS limelight;
  private SuperShooterS superShooter;
  /** Creates a new AimTurretC. */
  public AimTurretC(LimelightS limelight, SuperShooterS superShooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.limelight = limelight;
    this.superShooter = superShooter;

    addRequirements(limelight);
    addRequirements(superShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {                                // if offset > 0, turn left. if offset < 0, turn right
    superShooter.requestTurretState(TurretRequestedStates.MOVE_TO_SETPOINT, 0 - limelight.getFilteredXOffset());
    superShooter.runTurretPID();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted) superShooter.requestTurretState(TurretRequestedStates.STOP, 0);
    else superShooter.requestTurretState(TurretRequestedStates.HOME, 0);
    limelight.deregister();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return superShooter.isTurretAtSetpoint();
  }
}
