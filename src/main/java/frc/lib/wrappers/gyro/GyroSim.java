

package frc.lib.wrappers.gyro;

import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.wpilibj.simulation.SimDeviceSim;

public class GyroSim {
  private final SimDouble SIM_ANGLE;
  private final SimDouble SIM_RATE;

  /**
   * Constructs from an Gyro object.
   *
   * @param gyro Gyro to simulate
   */
  public GyroSim(String name, int channel) {
    SimDeviceSim wrappedSimDevice = new SimDeviceSim("Gyro:SimulatedGyro" + "[" + channel + "]");
    SIM_ANGLE = wrappedSimDevice.getDouble("angle_x");
    SIM_RATE = wrappedSimDevice.getDouble("rate_x");
  }

  /**
   * Sets the angle in degrees (clockwise positive).
   *
   * @param angleDegrees The angle.
   */
  public void setAngle(double angleDegrees) {
    SIM_ANGLE.set(angleDegrees);
  }

  /**
   * Sets the angular rate in degrees per second (clockwise positive).
   *
   * @param rateDegreesPerSecond The angular rate.
   */
  public void setRate(double rateDegreesPerSecond) {
    SIM_RATE.set(rateDegreesPerSecond);
  }
}
