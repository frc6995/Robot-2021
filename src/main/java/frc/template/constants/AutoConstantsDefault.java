/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.template.constants;

import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;

/**
 * Add your docs here.
 */
public class AutoConstantsDefault extends AutoConstants{
    
    public AutoConstantsDefault(DriveConstants drivebaseConstants) {
        super(drivebaseConstants);
        // TODO Auto-generated constructor stub
    }

    @Override
    public double getkMaxAccelerationMetersPerSecondSquared() {
        
        return 0;
    }

    @Override
    public double getkMaxSpeedMetersPerSecond() {
        
        return 0;
    }

    @Override
    public double getkRamseteB() {
        
        return 0;
    }

    @Override
    public double getkRamseteZeta() {
        
        return 0;
    }
}
