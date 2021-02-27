

package frc.lib.wrappers.motorcontrollers;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Spark;

/** Add your docs here. */
public class NomadPWMMotor extends Spark implements NomadBaseMotor {
    private boolean laziness = false;

    public NomadPWMMotor(int channel) {
        super(channel);
    }

    @Override
    public boolean isLazy() {
        return laziness;
    }

    @Override
    public void setLazy(boolean isLazy) {
        laziness = isLazy;
    }

    @Override
    public NomadBaseMotor setLeader(NomadBaseMotor leader) throws IllegalArgumentException {
        return this;
    } 

    @Override
    public double getActualOutputPercent() {
        return get();
    }

    @Override
    public void setOutputVoltage(double voltage) {
        set(voltage/RobotController.getBatteryVoltage());
    }
}
