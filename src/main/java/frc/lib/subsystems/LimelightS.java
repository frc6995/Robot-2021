// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.subsystems;

import edu.wpi.first.wpilibj.LinearFilter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.wrappers.limelight.Limelight;
import frc.lib.wrappers.limelight.Limelight.CameraMode;

public class LimelightS extends SubsystemBase {
  private Limelight limelight;

  private LinearFilter xOffsetFilter;
  private LinearFilter yOffsetFilter;

  private double filteredXOffset;
  private double filteredYOffset;

  private int numberOfRegisters;
  
  /**
   * Linear filter with time constant of 0.1 seconds and period of 0.02 seconds - the standard FRC main Loop period
   */
  private static LinearFilter standardMainLoopPeriodFilter = LinearFilter.singlePoleIIR(0.1, 0.02);

  /** Creates a new LimelightS.
   * 
   * @param Limelight The Limelight wrapper
   * @author Joey Fabel
   */
  public LimelightS(Limelight limelight) {
    this.limelight = limelight;

    xOffsetFilter = standardMainLoopPeriodFilter;
    yOffsetFilter = standardMainLoopPeriodFilter;

    numberOfRegisters = 0;
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
  public void Register(){
    numberOfRegisters++;
    
    SetCameraMode(CameraMode.Vision);
  }

  /**
   * Removes a command from the Limelight's registry
   */
  public void Deregister(){
    numberOfRegisters--;

    if (numberOfRegisters == 0) SetCameraMode(CameraMode.Driver);
  }

    /**
     * Set the camera mode to the specified state. This sets the network table entry
     * 'camMode'.
     * 
     * @param state The desired {@link CameraMode}.
     */
  public void SetCameraMode(Limelight.CameraMode mode){
    limelight.setCamMode(mode);
  }

    /**
     * Set the state of the LEDs. This changes the value of the 'ledMode' entry.
     * 
     * @param state The desired {@link LedState}
     */
  public void SetLEDState(Limelight.LedState state){
    limelight.setLedMode(state);
  }
}
