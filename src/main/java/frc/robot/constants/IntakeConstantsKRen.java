package frc.robot.constants;

import frc.lib.constants.IntakeConstants;

public class IntakeConstantsKRen implements IntakeConstants {

    @Override
    public int getIntakeMotorPort() {
        return 30;
    }

    @Override
    public int getSolenoidFwdPort() {
        return 2;
    }

    @Override
    public int getSolenoidRevPort() {
        return 3;
    }

}