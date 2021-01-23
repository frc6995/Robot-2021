// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.template.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.motorcontrollers.NomadSparkMax;

  public class IntakeS extends SubsystemBase {
    private NomadSparkMax intakeMotor;
    private DoubleSolenoid intakeSolenoid;
    /**
     * Creates a new IntakeS.
     */
    public IntakeS() {
      intakeMotor = new NomadSparkMax(21);
      intakeSolenoid = new DoubleSolenoid(1, 2, 3);
    }
  
    public void setSpeed(double speed) {
      intakeMotor.set(speed);
    }
  
    @Override
    public void periodic() {
    }

    public void intakeToggle() {
    if (intakeSolenoid.get() == Value.kReverse) {
      intakeSolenoid.set(Value.kForward);
    }
    else {
      intakeSolenoid.set(Value.kReverse);
    }
  }
  
}