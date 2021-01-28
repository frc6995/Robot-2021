// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

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

  private ArrayList<String> registryKeys;
  
  /** Creates a new LimelightS.
   * 
   * @param Limelight The {@link Limelight} wrapper
   */
  public LimelightS(Limelight limelight) {
    this.limelight = limelight;

    xOffsetFilter = LinearFilter.singlePoleIIR(LimelightConstants.timeConstant, LimelightConstants.timePeriod);
    yOffsetFilter = LinearFilter.singlePoleIIR(LimelightConstants.timeConstant, LimelightConstants.timePeriod);    

    numberOfConsumersRegistered = 0;
    registryKeys = new ArrayList<String>();
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

    // If it went negative, reset the count to 0 and don't mess with the limelight
    if (numberOfConsumersRegistered < 0) numberOfConsumersRegistered = 0;
    else if (numberOfConsumersRegistered == 0){
      setCameraMode(CameraMode.Driver);
      setLEDState(LedState.Off);
    }
  }

  /**
   * Register to the Limelight and generate a registry key.
   * 
   * @return The unique registry key, make sure you store this if you want to be able to deregister
   */
  public String registerWithKey(){
    // Generate a new registry key
    byte[] array = new byte[7];
    new Random().nextBytes(array);
    String key = new String(array, Charset.forName("UTF-8"));

    // add it to the list
    registryKeys.add(key);

    setCameraMode(CameraMode.Vision);
    setLEDState(LedState.On);
    return key;    
  }

  /**
   * Deregister from the Limelight, if a valid registry key is provided.
   * @param key The registry key
   */
  public void deregister(String key){
    // Check if the provided registry key is valid
    if (registryKeys.contains(key)) {
      registryKeys.remove(key);     

      if (registryKeys.size() == 0){
        setCameraMode(CameraMode.Driver);
        setLEDState(LedState.Off);
      }
    }
    // Don't do anything with an invalid key
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
