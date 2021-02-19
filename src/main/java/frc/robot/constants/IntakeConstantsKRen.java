package frc.robot.constants;

import frc.robot.constants.interfaces.IntakeConstants;

public class IntakeConstantsKRen implements IntakeConstants {

    @Override
    public int getIntakeMotorPort() {
        return 30;
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
        return -1;
    }

}