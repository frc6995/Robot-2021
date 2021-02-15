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

  private LimelightConstants constants;
  
  /** Creates a new LimelightS.
   * 
   * @param Limelight The {@link Limelight} wrapper
   */
  public LimelightS(Limelight limelight, LimelightConstants constants) {
    this.limelight = limelight;
    this.constants = constants;

    xOffsetFilter = LinearFilter.singlePoleIIR(constants.getTimeConstant(), constants.getTimePeriod());
    yOffsetFilter = LinearFilter.singlePoleIIR(constants.getTimeConstant(), constants.getTimePeriod());    

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

  /**
   * Has the Limelight found a target?
   * @return <b>true</b> if a target is found, otherwise <b>false</b>
   */
  public boolean isTargetFound(){
    return limelight.hasTarget();
  }

  /**
   * Get the distance between the Limelight and the target.
   * @return The distance between limelight and the target
   */
  public double getDistanceToTarget(){
    //TODO - Find the actual constant values
    double px = limelight.get3dPosition().x; // pixel x-coordinate
    double py = limelight.get3dPosition().y; // pixel y-coordinate
    double nx = (1/60) * (px - 159.5); // normalized pixel x-coordinate
    double ny = (1/120) * (119.5 - py); // normalized pixel y-coordinate
    double horizontalFov = Math.toRadians(54); // 54 degrees
    double verticalFov = Math.toRadians(41); // 41 degrees
    double vpw = 2.0 * Math.tan(horizontalFov / 2); // View plane width - Note: View plane at distance of 1.0
    double vph = 2.0 * Math.tan(verticalFov / 2); // View plane height
    double x = vpw / 2 * nx; // Convert from normalized pixel coordinate and view plane coordinate
    double y = vph / 2 * ny; 

    double a2 = Math.atan2(1, x); // Angle = arc tangent of 1 (view point distance) and x
    
    // (h2 - h1) / tan(a1 + a2)
    return (constants.getTargetHeight() - constants.getLimelightHeight()) / Math.tan(constants.getMountingAngle() + a2);
  }
}
