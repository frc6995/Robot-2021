

package frc.robot.constants;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import frc.robot.constants.interfaces.ShooterConstants;

/** The {@link ShooterConstants} for our 2021 robot. */
public class ShooterConstants2021 implements ShooterConstants {
    public int getLeadMotorID(){
        return 49;
    }

    public int getFollowerMotorID(){
        return 57;
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
        return 100;
    }

    public double getAverageCurrent(){
        return 20;
    }

    public double getAllowableVoltageError(){
        return 0.5;
    }

    @Override
    public boolean getLeadMotorInverted() {
        return true;
    }

    @Override
    public boolean getFollowerMotorInverted() {
        // always true - why a constant?
        return true;
    }

    public SimpleMotorFeedforward getArbitraryFeedforward(){
        return new SimpleMotorFeedforward(0.393, 0.216/103, 0.032/103);
    }
}
