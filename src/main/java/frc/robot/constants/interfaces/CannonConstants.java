

package frc.robot.constants.interfaces;

import frc.robot.constants.interfaces.HoodConstants;
import frc.robot.constants.interfaces.ShooterConstants;

/**
 * The Consants for the complete {@link CannonS} subsystem. Includes a
 * {@link ShooterConstants}, a {@link HoodConstants}, and a
 * {@link TurretConstants}
 */
public interface CannonConstants {
    /**
     * Get the {@link ShooterConstants}.
     * @return The {@link ShooterConstants}
     */
    ShooterConstants getShooterConstants();
    /**
     * Get the {@link HoodConstants}.
     * @return The {@link HoodConstants}
     */
    HoodConstants getHoodConstants();
    /**
     *  Get the {@link TurretConstants}. 
     * 
     * @return The {@link TurretConstants}
     */
    TurretConstants getTurretConstants();
}
