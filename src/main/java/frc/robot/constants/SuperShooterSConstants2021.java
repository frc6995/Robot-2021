// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** Add your docs here. */
public class SuperShooterSConstants2021 implements SuperShooterSConstants {
	private ShooterConstants shooterConstants;
	private HoodConstants hoodConstants;
	private TurretConstants turretConstants;

    public SuperShooterSConstants2021(ShooterConstants shooterConstants, HoodConstants hoodConstants, TurretConstants turretConstants){
		this.shooterConstants = shooterConstants;
		this.hoodConstants = hoodConstants;
		this.turretConstants = turretConstants;
	}

	@Override
	public ShooterConstants getShooterConstants() {		
		return shooterConstants;
	}

	@Override
	public HoodConstants getHoodConstants() {		
		return hoodConstants;
	}

	@Override
	public TurretConstants getTurretConstants() {
		return turretConstants;
	}
}
