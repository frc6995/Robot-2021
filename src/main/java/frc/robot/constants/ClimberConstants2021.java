// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** Add your docs here. */
public class ClimberConstants2021 {
	private boolean useSD = true;

	public ClimberConstants2021() {
		SmartDashboard.putNumber("Servo Engage", 5);
		SmartDashboard.putNumber("Servo Disengaged", 16);
	}

	public int getRatchetMotorId() {
		return 60;
	}

	public int getTranslatorId() {
		return 61;
	}

	public int getDeploySolenoidPort() {
		return 3;
	}

	public int getRetractSolenoidPort() {
		return 2;
	}

	public int getServoPort() {
		return 0;
	}

	public double getKP() {
		return 0.0;
	}

	public double getKI() {
		return 0.0;
	}

	public double getKD() {
		return 0.0;
	}

	public double getKF() {
		return 0.0;
	}

	public double getIZone() {
		return 1000;
	}

	public double getPullupSetpoint() {
		return 1000;
	}

	public double getExtendSetPoint() {
		return 5000;
	}

	public double getAllowableError() {
		return 50;
	}

	public double servoEngagedAngle() {
		return (useSD ? SmartDashboard.getNumber("Servo Engage", 5) : 5);
	}

	public double servoDisengagedAngle() {
		return (useSD ? SmartDashboard.getNumber("Servo Disengaged", 16) : 16);
	}
}
