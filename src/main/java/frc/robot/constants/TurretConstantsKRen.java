package frc.robot.constants;

/** {@link TurretConstants} that have been set specifically for KRen
 * 
 * @author JoeyFabel
 */
public class TurretConstantsKRen implements TurretConstants {

    @Override
    public double getMarginOfError() {
        return 50;
    }

    @Override
    public double getKP() {
        return 1;
    }

    @Override
    public double getKI() {
        return 1;
    }

    @Override
    public double getKD() {
        return 1;
    }

    @Override
    public double getKFF() {
        return 1;
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
        return 270;
    }

    @Override
    public int getSparkMaxPortID() {
        return 1;
    }

    @Override
    public int getLimitSwitchChannelID() {
        return 2;
    }

    @Override
    public int getMinCountsAtSetpoint(){
        return 10;
    }
}
