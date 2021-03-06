// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.cannon;

import com.revrobotics.CANEncoder;
import com.revrobotics.ControlType;
import com.revrobotics.EncoderType;
import com.revrobotics.CANPIDController.AccelStrategy;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;

public class TurretMotionTester extends CommandBase {
  private NomadSparkMax motor;
  private double setpoint;
  private CANEncoder encoder;
  /** Creates a new TurretMotionTester. */
  public TurretMotionTester(NomadSparkMax cannon, double setpoint) {
    motor = cannon;
    this.setpoint = setpoint;
    encoder = motor.getEncoder();
    encoder.setPositionConversionFactor(360 / 49.03125);
    encoder.setVelocityConversionFactor(360 / 49.03125);
    encoder.setPosition(0);

    //motor.enableSoftLimit(SoftLimitDirection.kForward, true);
    motor.setSoftLimit(SoftLimitDirection.kForward, 270f);
    //motor.enableSoftLimit(SoftLimitDirection.kReverse, true);
    motor.setSoftLimit(SoftLimitDirection.kReverse, -270f);

    motor.getPIDController().setSmartMotionMaxAccel(3600, 0);
    motor.getPIDController().setSmartMotionMaxVelocity(3600, 0);
    motor.getPIDController().setSmartMotionMinOutputVelocity(/*500*/0, 0);
    motor.getPIDController().setSmartMotionAccelStrategy(AccelStrategy.kTrapezoidal, 0);
    motor.getPIDController().setSmartMotionAllowedClosedLoopError(2,0);
    
    motor.getPIDController().setOutputRange(-.75, .75);

    motor.getPIDController().setP(.00003, 0); 
    motor.getPIDController().setI(.000004, 0);
    motor.getPIDController().setIZone(100, 0);
    motor.getPIDController().setD(0, 0);
    motor.getPIDController().setFF(0, 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //motor.getPIDController().setReference(setpoint, ControlType.kSmartMotion, 0);
    motor.set(0.2);
    //cannonS.runTurretPID();
  }
  
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //cannonS.runTurretPIDWithMotionMagic();
    //cannonS.runTurretPID();
    SmartDashboard.putNumber("Turret Position", encoder.getPosition());
    SmartDashboard.putNumber("Turret Velocity", encoder.getVelocity());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    motor.stopMotor();
    encoder.setPosition(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(motor.getEncoder().getPosition() - setpoint) < 2;
  }
}
