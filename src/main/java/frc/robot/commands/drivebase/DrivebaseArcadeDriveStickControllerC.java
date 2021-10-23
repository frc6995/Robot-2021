/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivebase;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.constants.DriveConstants;
import frc.lib.subsystems.DifferentialDrivebaseS;

import frc.robot.subsystems.DrivebaseS;

public class DrivebaseArcadeDriveStickControllerC extends CommandBase {
	DrivebaseS drivebaseS;
	DriveConstants driveConstants;
	GenericHID controller;
	double driveSpeed;

	/**
	 * Creates a new DrivebaseArcadeDriveStick.
	 */
	public DrivebaseArcadeDriveStickControllerC(DrivebaseS drivebase, DriveConstants driveConstants,
			GenericHID controller) {
		drivebaseS = drivebase;
		addRequirements(drivebaseS);
		this.driveConstants = driveConstants;
		this.controller = controller;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		double rightTrigger = controller.getRawAxis(2);
		double leftTrigger = controller.getRawAxis(3);

		if (rightTrigger > -0.015 && rightTrigger < 0.015)
			rightTrigger = 0;
		if (leftTrigger > -0.015 && leftTrigger < 0.015)
			leftTrigger = 0;

		//double driveSpeed = rightTrigger - leftTrigger;
		if (controller.getRawButton(XboxController.Button.kY.value)) {
			driveSpeed = (rightTrigger - leftTrigger) * -1;
		} else {
			driveSpeed = (rightTrigger - leftTrigger) * driveConstants.getDriveControllerFwdBackAxisMultiplier();
		}

		double turnSpeed = controller.getRawAxis(0);

		if (turnSpeed > -0.015 && turnSpeed < 0.015)
			turnSpeed = 0;
		
		// A compounded function: processOutputs(calculateOutputs(getInputs())).
		// Defaults to the left and right Talons in DrivebaseS
		drivebaseS.curvatureDrive(driveSpeed,
				turnSpeed * driveConstants.getDriveControllerLeftRightAxisMultiplier(),
				controller.getRawButton(XboxController.Button.kB.value));
	}

	@Override
	public void end(boolean interrupted) {
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
