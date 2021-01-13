/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.wrappers.inputdevices;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.utility.inputs.NomadInputMaps;
import frc.robot.utility.inputs.NomadXboxInputMap;
import frc.robot.utility.inputs.NomadInputMaps.NomadMappingEnum;

/**
 * A controller wrapper specifically for the driver controller.
 * Provides convenience methods that get the correct axis/button value for the role (fwdBackAxis, etc), based on the passed in DrivercontrollerProfile.
 */
public class NomadMappedGenericHID extends GenericHID {
    private NomadMappingEnum selectedMap;

    public NomadMappedGenericHID(int port) {
        super(port);
    }

    public NomadMappedGenericHID setMap(NomadMappingEnum mapType) {
        selectedMap = mapType;
        return this;
    }

    public NomadMappingEnum getSelectedMap() {
        return selectedMap;
    }

    @Override
    public double getX(Hand hand) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY(Hand hand) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRawAxis(int id){
        return selectedMap.getMap().getRawAxis(id);
    }

    @Override
    public boolean getRawButton(int id){
        return selectedMap.getMap().getRawButton(id);
    }


    
}
