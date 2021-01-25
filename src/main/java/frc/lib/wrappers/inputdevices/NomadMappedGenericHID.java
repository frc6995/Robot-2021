/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.wrappers.inputdevices;

import edu.wpi.first.wpilibj.GenericHID;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;


/**
 * A controller wrapper specifically for the driver controller.
 * Provides convenience methods that get the correct axis/button value for the role (fwdBackAxis, etc), based on the passed in DrivercontrollerProfile.
 */
public class NomadMappedGenericHID extends GenericHID {
    public static NomadMappedGenericHID noneMappedHID = new NomadMappedGenericHID(Integer.MIN_VALUE);
    private NomadMappingEnum selectedMap = NomadMappingEnum.UNCATEGORIZED;

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
        
        return 0;
    }

    @Override
    public double getY(Hand hand) {
        
        return 0;
    }

    @Override
    public double getRawAxis(int id){
        return NomadOperatorConsole.inputEnumMap.get(selectedMap)
        .getRawAxis(id);
    }

    @Override
    public boolean getRawButton(int id){
        return NomadOperatorConsole.inputEnumMap.get(selectedMap).getRawButton(id);
    }

    public double getHIDRawAxis(int id){
        return super.getRawAxis(id);
    }
    
    public boolean getHIDRawButton(int id){
        return super.getRawButton(id);
    }


    
}
