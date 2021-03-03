package frc.robot.constants;

import frc.robot.constants.interfaces.TurretConstants;

/** {@link TurretConstants} that have been set specifically for KRen
 * 
 * @author JoeyFabel
 */
public class TurretConstantsKRen {

    
    public double getMarginOfError() {
        return 50;
    }

    
    public double getKP() {
        return 1;
    }

    
    public double getKI() {
        return 1;
    }

    
    public double getKD() {
        return 1;
    }

    
    public double getKFF() {
        return 1;
    }

    
    public double getEncoderTicksPerDegree() {
        return 42.0 * 5 * 150 / 18 / 360;
        /* Encoder ticks per rev * Gear ratio (5:1 and 150:18) / Degrees per revolution */
    }

    
    public double getHomePosition() {
        return 0;
    }

    
    public double getSoftLimit() {
        return 270;
    }

    
    public int getSparkMaxPortID() {
        return 1;
    }

    
    public int getLimitSwitchChannelID() {
        return 2;
    }

    
    public int getMinCountsAtSetpoint(){
        return 10;
    }
}
