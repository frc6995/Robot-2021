/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.wrappers.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class LimelightSimulatedNetworkTable{
    private NetworkTable table = null;
    public NetworkTableEntry hasTarget = null;
    public NetworkTableEntry tx = null;
    public NetworkTableEntry ty = null;
    public NetworkTableEntry ts = null;
    public NetworkTableEntry pipeline = null;
    public NetworkTableEntry camMode = null;
    public NetworkTableEntry ledMode = null;

    public LimelightSimulatedNetworkTable(String name){
        table = NetworkTableInstance.getDefault().getTable(name);
        hasTarget = table.getEntry("tv");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ts = table.getEntry("ts");
        pipeline = table.getEntry("pipeline");
        camMode = table.getEntry("camMode");
        ledMode = table.getEntry("ledMode");
    }
}
