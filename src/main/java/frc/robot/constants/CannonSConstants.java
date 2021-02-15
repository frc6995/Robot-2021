// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** The Consants for the complete {@link CannonS} subsystem. Includes a {@link ShooterConstants}, a {@link HoodConstants}, and a {@link TurretConstants} */
public interface CannonSConstants {
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
