

package frc.lib.wrappers.gyro;

import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.wpilibj.simulation.SimDeviceSim;

/** Class to control a simulated ADXRS450 gyroscope. */
@SuppressWarnings({"TypeName", "AbbreviationAsWordInName"})
public class GyroSim {
  private final SimDouble m_simAngle;
  private final SimDouble m_simRate;

  /**
   * Constructs from an Gyro object.
   *
   * @param gyro Gyro to simulate
   */
  public GyroSim(String name, int channel) {
    SimDeviceSim wrappedSimDevice = new SimDeviceSim("Gyro:SimulatedGyro" + "[" + channel + "]");
    m_simAngle = wrappedSimDevice.getDouble("angle_x");
    m_simRate = wrappedSimDevice.getDouble("rate_x");
  }

  /**
   * Sets the angle in degrees (clockwise positive).
   *
   * @param angleDegrees The angle.
   */
  public void setAngle(double angleDegrees) {
    m_simAngle.set(angleDegrees);
  }

  /**
   * Sets the angular rate in degrees per second (clockwise positive).
   *
   * @param rateDegreesPerSecond The angular rate.
   */
  public void setRate(double rateDegreesPerSecond) {
    m_simRate.set(rateDegreesPerSecond);
  }
}
