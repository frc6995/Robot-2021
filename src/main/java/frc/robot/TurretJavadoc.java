// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * <h1>Turret</h1>
 * 
 * <h2>Description</h2>
 *     <p>The turret to aim the shooter. It spins the shooter to point at the power port.</p>
 * <h2>Inputs</h2>
 *      <ul>
 *          <li>Throughborne encoder (8192 ticks/revolution)
 *          <li>Limelight
 *          <li>XBox Controller
 *      </ul>
 * <h2>Outputs</h2>
 *      <ul>
 *          <li>Current turret rotation
 *          <li>Current turret torque?
 *      </ul>
 * <h2>Actions</h2>
 *      <ul>
 *          <li>Rotate Turret Manually
 *          <li>Rotate Turret Automatically (w/ Limelight)
 *          <li>Home Turret
 *      </ul>
 * <h2>Hardware</h2>
 *      <ul>
 *          <li>1 Spark Max
 *          <li>1 Neo 550
 *          <li>1 Throughborne encoder (8192 ticks/revolution)
 *      </ul>
 * <h2>Constraints</h2>
 *      <ul>
 *          <li>Spark Max leader motor, Neo 550 follows Spark Max
 *          <li>Encoder plugged directly into Spark Max, not into Roborio
 *          <li>Turret can spin 360 degrees infinitely
 *          <li>Home on robot start
 *      </ul>
 * 
 * @author JoeyFabel
 */ 
public class TurretJavadoc {}
