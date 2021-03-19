// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.CANPIDController.AccelStrategy;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;
import frc.robot.subsystems.LimelightS;

public class TurretAimTester extends CommandBase {
  private NomadSparkMax motor;
  private double setpoint;
  private CANEncoder encoder;
  private CANPIDController controller;
  private LimelightS limelight;

  /** Creates a new TurretAimTester. */
  public TurretAimTester(NomadSparkMax cannon, LimelightS limelight) {
    motor = cannon;
    this.limelight = limelight;
    encoder = motor.getEncoder();
    controller = motor.getPIDController();
    encoder.setPositionConversionFactor(360 / 49.03125);
    encoder.setVelocityConversionFactor(360 / 49.03125);
    encoder.setPosition(0);
    motor.enableVoltageCompensation(12);

    //motor.enableSoftLimit(SoftLimitDirection.kForward, true);
    motor.setSoftLimit(SoftLimitDirection.kForward, 270f);
    //motor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    motor.setSoftLimit(SoftLimitDirection.kReverse, -270f);

    controller.setSmartMotionMaxAccel(35000, 0);
    controller.setSmartMotionMaxVelocity(36000, 0); //3600
    controller.setSmartMotionMinOutputVelocity(/*500*/0, 0);
    controller.setSmartMotionAccelStrategy(AccelStrategy.kTrapezoidal, 0);
    controller.setSmartMotionAllowedClosedLoopError(0.1,0);
    
    controller.setOutputRange(-1, 1);

    controller.setP(.000032, 0); 
    controller.setI(.000002, 0);
    controller.setIZone(100, 0);
    controller.setD(0.00001, 0);
    controller.setFF(0, 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    setpoint = encoder.getPosition() - limelight.getFilteredXOffset();
    controller.setReference(setpoint, ControlType.kSmartMotion, 0);
    SmartDashboard.putNumber("Turret Setpoint", setpoint);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    SmartDashboard.putNumber("Turret Position", encoder.getPosition());
    SmartDashboard.putNumber("Turret Velocity", encoder.getVelocity());
    SmartDashboard.putNumber("Limelight Offset", limelight.getFilteredXOffset());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motor.stopMotor();
    //encoder.setPosition(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(motor.getEncoder().getPosition() - setpoint) < 0.1;
  }
}
