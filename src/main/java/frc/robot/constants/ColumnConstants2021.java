

package frc.robot.constants;

import edu.wpi.first.wpilibj.system.plant.DCMotor;
import frc.robot.constants.interfaces.ColumnConstants;

/** Add your docs here. */
public class ColumnConstants2021 implements ColumnConstants {
    @Override
    public int getTalonID() {
        return 41;
    }
    
    @Override
    public int getAcceleratorID() {
        return 43;
    }
    
    @Override
    public int getFwdPort() {
        return 4;
    }

    @Override
    public int getRevPort() {
        return 5;
    }

    @Override
    public double getColumnLoadSpeed() {
        return 0.25;
    }

    @Override
    public double getColumnFeedSpeed() {
        // TODO Auto-generated method stub
        return 0.75;
    }

    @Override
    public double getAcceleratorRPM() {
        // TODO Auto-generated method stub
        return 5000; //free speed for BAG is 13000 RPM
    }

    @Override
    public double getAcceleratorCurrentLimit() {
        // TODO Auto-generated method stub
        return 5;
    }

}
