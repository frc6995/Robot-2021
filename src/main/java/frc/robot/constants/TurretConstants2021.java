package frc.robot.constants;

import frc.robot.constants.interfaces.TurretConstants;

/** {@link TurretConstants} that have been set specifically for KRen
 * 
 * @author JoeyFabel
 */
public class TurretConstants2021 implements TurretConstants {

    @Override
    public double getMarginOfError() {
        return 0.1;
    }

    @Override
    public double getKP() {
        return .005;
    }

    @Override
    public double getKI() {
        return 0;
    }

    @Override
    public double getKD() {
        return 0;
    }

    @Override
    public double getKFF() {
        return 0;
    }

    public double getIZone() {
        return 1;
    }

    @Override
    public double getEncoderTicksPerDegree() {
        return 42.0 * 5 * 150 / 18 / 360;
        /* Encoder ticks per rev * Gear ratio (5:1 and 150:18) / Degrees per revolution */
    }

    @Override
    public double getHomePosition() {
        return 0;
    }

    @Override
    public double getSoftLimit() {
        return 80;
    }

    @Override
    public int getSparkMaxPortID() {
        return 53;
    }

    @Override
    public int getLimitSwitchChannelID() {
        return 9;
    }

    @Override
    public int getMinCountsAtSetpoint(){
        return 10;
    }

    @Override
    public boolean getLeadMotorInverted() {
        return false;
    }
}
