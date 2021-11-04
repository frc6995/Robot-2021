
package frc.robot.constants;

import frc.robot.constants.interfaces.CannonConstants;
import frc.robot.constants.interfaces.HoodConstants;
import frc.robot.constants.interfaces.ShooterConstants;
import frc.robot.constants.interfaces.TurretConstants;

/** Add your docs here. */
public class CannonConstants2021 implements CannonConstants {
	private ShooterConstants shooterConstants;
	private HoodConstants hoodConstants;
	private TurretConstants turretConstants;

	public CannonConstants2021(ShooterConstants shooterConstants, HoodConstants hoodConstants,
			TurretConstants turretConstants) {
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
