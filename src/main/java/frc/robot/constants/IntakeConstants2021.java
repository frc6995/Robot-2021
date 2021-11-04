package frc.robot.constants;

import frc.robot.constants.interfaces.IntakeConstants;

public class IntakeConstants2021 implements IntakeConstants {

    @Override
    public int getIntakeMotorPort() {
        return 30;
    }

    @Override
	public int getIntakeBackMotorPort() {
		return 31;
	}

    @Override
    public int getSolenoidFwdPort() {
        return 6;
    }

    @Override
    public int getSolenoidRevPort() {
        return 7;
    }

    @Override
    public double getIntakeSpeed() {
        return -0.75;
    }

	@Override
	public double getIntakeBackRollerSpeed() {
		return -0.6;
	}

}