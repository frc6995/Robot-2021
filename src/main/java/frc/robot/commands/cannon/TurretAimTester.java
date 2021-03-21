// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.subsystems.LimelightS;

public class TurretAimTester extends CommandBase {
  private NomadSparkMax motor;
  private double setpoint;
  private CANEncoder encoder;
  private LimelightS limelight;

  /** Creates a new TurretAimTester. */
  public TurretAimTester(NomadSparkMax cannon, LimelightS limelight) {
    motor = cannon;
    this.limelight = limelight;
    encoder = motor.getEncoder();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setpoint = encoder.getPosition() - limelight.getFilteredXOffset();
    motor.getPIDController().setReference(setpoint, ControlType.kSmartMotion, 0);
    SmartDashboard.putNumber("Turret Setpoint", setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motor.stopMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(motor.getEncoder().getPosition() - setpoint) < 0.1;
  }
}
