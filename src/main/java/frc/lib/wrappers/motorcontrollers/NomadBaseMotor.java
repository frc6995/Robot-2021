/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.wrappers.motorcontrollers;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Add your docs here.
 */
public interface NomadBaseMotor extends SpeedController{



    /**
     * Check if the motor controller is lazy
     * 
     * @return Whether the motor controller is lazy
     */
    public boolean isLazy();
    /**
     * Set the lazy mode
     * 
     * @param isLazy A boolean for the lazy mode, where true is lazy on
     */
    public void setLazy(boolean isLazy);

    public NomadBaseMotor setLeader(NomadBaseMotor leader) throws IllegalArgumentException;

    public void setInverted(boolean inverted);

    public double getActualOutputPercent();




}
