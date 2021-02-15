// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/** The constants for the {@link Shooter}. */
public interface ShooterConstants {
    /**
     * Get the port ID of the lead {@link NomadSparkMax}.
     * @return the port ID
     */
    int getLeadMotorID();
    /**
     * Get the port ID of the follower {@link NomadSparkMax}.
     * @return the port ID
     */
    int getFollowerMotorID();
    /**
     * Get the PID 'P' constant.
     * @return The P constants
     */
    double getKP();
    /**
     * Get the PID 'I' constant.
     * @return The I constant
     */
    double getKI();
    /**
     * Get the PID 'D' constant.
     * @return The D constant
     */
    double getKD();
    /**
     * Get the PID 'FF' constant.
     * @return The FF constant
     */
    double getKFF();    
    /**
     * Get the PID IZone constants.
     * @return The IZone constant
     */
    double getIZone();
    /**
     * Get the allowable RPM error: if the shooter RPM is within the error, it counts as being at the desired speed.
     * 
     * @return The allowable RPM error
     */
    double getAllowableRPMError();
    /**
     * Get the average voltage of the shooter's motor while it is running.
     * @return The average voltage
     */
    double getAverageVoltage();
    /**
     * Get the allowable error when comparing the Shooter's voltage to its average voltage
     */
    double getAllowableVoltageError();
}
