// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.limelight.Limelight;
import frc.lib.wrappers.limelight.Limelight.CameraMode;
import frc.lib.wrappers.limelight.Limelight.LedState;
import frc.robot.constants.LimelightConstants;

/**
 * The Limelight Subsystem.
 * Contains methods for getting the x and y-offsets, filtered or unfiltered.
 * 
 * 
 * @author Joey Fabel
 */
public class LimelightS extends SubsystemBase {
  private Limelight limelight;

  private LinearFilter xOffsetFilter;
  private LinearFilter yOffsetFilter;

  private double filteredXOffset;
  private double filteredYOffset;

  private int numberOfConsumersRegistered;
  
  /** Creates a new LimelightS.
   * 
   * @param Limelight The {@link Limelight} wrapper
   */
  public LimelightS(Limelight limelight) {
    this.limelight = limelight;

    xOffsetFilter = LinearFilter.singlePoleIIR(LimelightConstants.timeConstant, LimelightConstants.timePeriod);
    yOffsetFilter = LinearFilter.singlePoleIIR(LimelightConstants.timeConstant, LimelightConstants.timePeriod);    

    numberOfConsumersRegistered = 0;
  }

  @Override
  public void periodic() {
    filteredXOffset = xOffsetFilter.calculate(getXOffset());
    filteredYOffset = yOffsetFilter.calculate(getYOffset());
  }

  /**
   * Get the X-offset of the Limelight without filtering.
   * @return The X-offset between the Limelight and the target
   */
  public double getXOffset(){
    return limelight.getXOffset();
  }

  /**
   * Get the Y-offset of the Limelight without filtering.
   * @return The Y-offset between the Limelight and the target
   */
  public double getYOffset(){
    return limelight.getYOffset();
  }

  /**
   * Get the X-offset of the Limelight after filtering.
   * @return The filtered X-offset between the Limelight and the target
   */
  public double getFilteredXOffset(){
    return filteredXOffset;
  }

  /**
   * Get the Y-offset of the Limelight after filtering.
   * @return The filtered Y-offset between the Limelight and the target
   */
  public double getFilteredYOffset(){
    return filteredYOffset;
  }

  /**
   * Registers a command as using the Limelight.
   */
  public void register(){
    numberOfConsumersRegistered++;
    
    setCameraMode(CameraMode.Vision);
    setLEDState(LedState.On);
  }

  /**
   * Removes a command from the Limelight's registry
   */
  public void deregister(){
    numberOfConsumersRegistered--;

    limelight.hashCode()

    if (numberOfConsumersRegistered == 0){
      setCameraMode(CameraMode.Driver);
      setLEDState(LedState.Off);
    }
  }

    /**
     * Set the camera mode to the specified state. This sets the network table entry
     * 'camMode'.
     * 
     * @param mode The desired {@link CameraMode}.
     */
  public void setCameraMode(Limelight.CameraMode mode){
    limelight.setCamMode(mode);
  }

    /**
     * Set the state of the LEDs. This changes the value of the 'ledMode' entry.
     * 
     * @param state The desired {@link LedState}
     */
  public void setLEDState(Limelight.LedState state){
    limelight.setLedMode(state);
  }
}
