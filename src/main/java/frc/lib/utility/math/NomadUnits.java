/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.utility.math;

import frc.lib.constants.DriveConstants;

/**
 * Add your docs here.
 */
public class NomadUnits {

    public static double DBTicksToMeters (double ticks, DriveConstants constants) {
        return Math.PI * constants.getkWheelDiameter() * ticks / constants.getEncoderCountsPerWheelRevolution();
    }

    public static double DBMetersToTicks (double meters, DriveConstants constants) {
        return (meters / (constants.getkWheelDiameter() * Math.PI) * constants.getEncoderCountsPerWheelRevolution()); 
    }
}
