/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.wrappers.motorcontrollers;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Defines the base functionality, as an extension of SpeedController, for subclasses of NomadBaseMotor.
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

    /**
     * Attempts to set follower mode, following the given NomadBaseMotor.
     * @param leader The NomadBaseMotor to follow.
     * @return This object, modified.
     * @throws IllegalArgumentException if the given motor is an incompatible type.
     */

    public NomadBaseMotor setLeader(NomadBaseMotor leader) throws IllegalArgumentException;
    /**
     * Set whether the motor is inverted.
     * @param inverted Inverted is true.
     */
    public void setInverted(boolean inverted);
    /**
     * @return The actual percent output applied to the motor. 
     */
    public double getActualOutputPercent();




}
