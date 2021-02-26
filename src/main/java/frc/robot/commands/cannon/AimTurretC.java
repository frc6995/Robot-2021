// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.LimelightS;
import frc.robot.subsystems.cannon.CannonS;
import frc.robot.subsystems.cannon.Turret.TurretRequestedStates;

/** A command that aims the turret.
 * 
 * @author JoeyFabel
 */
public class AimTurretC extends CommandBase {
  private LimelightS limelight;
  private CannonS cannon;
  
  /** Creates a new AimTurretC. */
  public AimTurretC(LimelightS limelight, CannonS cannon, boolean requireCannon) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.limelight = limelight;
    this.cannon = cannon;
    if(requireCannon) {
      addRequirements(cannon);
    }
  }

  public AimTurretC(LimelightS limelight, CannonS cannon) {
    this(limelight, cannon, true);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    limelight.register();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {                                // if offset > 0, turn left. if offset < 0, turn right
    cannon.requestTurretState(TurretRequestedStates.MOVE_TO_SETPOINT, 0 - limelight.getFilteredXOffset());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    if (interrupted) cannon.requestTurretState(TurretRequestedStates.STOP, 0);
    else cannon.requestTurretState(TurretRequestedStates.HOME, 0);
    limelight.deregister();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // cannon can tell when it is at setpoint, so it has an exit condition and runs in execute.
    return cannon.isTurretAtSetpoint();
  }
}
