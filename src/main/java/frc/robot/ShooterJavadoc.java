// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/** 
 * <h1>Shooter</h1>
 * 
 * <h2>Description</h2>
 *     <p>The flywheel that shoots the power cells into the power port. A spinning wheel controlled by velocity PID and an adjustible hood</p>
 * <h2>Inputs</h2>
 *      <ul>
 *          <li>Neo encoder position
 *          <li>Linear Servo position
 *          <li>XBox controller
 *      </ul>
 * <h2>Outputs</h2>
 *      <ul>
 *          <li>Neo encoder position (average of two)
 *          <li>Linear Servo position (output of two)
 *          <li>Current shooter speed?
 *      </ul>
 * <h2>Actions</h2>
 *      <ul>
 *          <li>Spin to max speed
 *          <li>Spin down
 *          <li>Move hood to angle
 *      </ul>
 * <h2>Hardware</h2>
 *      <ul>
 *          <li>2 Spark Maxes
 *          <li>2 Neos
 *          <li>2 Linear Servos
 *      </ul>
 * <h2>Constraints</h2>
 *      <ul>
 *          <li>Spark Maxes follow Neos
 *          <li>Shooter should use PID to ramp up smoothly
 *          <li>Shooter should use PID to ramp down smoothly
 *          <li>Hood (Linear Servos) should home on robot start
 *      </ul>
 * 
 * @author JoeyFabel
 */
public class ShooterJavadoc {}
