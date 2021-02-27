

package frc.robot.constants;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import frc.robot.constants.interfaces.ShooterConstants;

/** The {@link ShooterConstants} for our 2021 robot. */
public class ShooterConstants2021 implements ShooterConstants {
    public int getLeadMotorID(){
        return 3;
    }

    public int getFollowerMotorID(){
        return 4;
    }

    public double getKP() {
        return 0;
    }

    public double getKI() {
        return 0;
    }

    public double getKD() {
        return 0;
    }

    public double getKFF() {
        return 0;
    }

    public double getIZone(){
        return 0;
    }

    public double getAllowableRPMError(){
        return 10;
    }

    public double getAverageVoltage(){
        return 10.0;
    }

    public double getAllowableVoltageError(){
        return 0.25;
    }

    @Override
    public boolean getLeadMotorInverted() {
        return false;
    }

    @Override
    public boolean getFollowerMotorInverted() {
        return false;
    }

    public SimpleMotorFeedforward getArbitraryFeedforward(){
        return new SimpleMotorFeedforward(0.161, 0.189/90, 0.0434/90);
    }
}
