

package frc.robot.constants;

import frc.robot.constants.interfaces.AgitatorConstants;

/** Add your docs here. */
public class AgitatorConstants2021 implements AgitatorConstants{
    public int getLeftMotorID(){
        return 31;
    }

    public int getRightMotorID(){
        return 32;
    }

    @Override
    public double getRightSpeed() {
        return 0.8;
    }

    @Override
    public double getLeftSpeed() {
        return 0.5;
    }
}
