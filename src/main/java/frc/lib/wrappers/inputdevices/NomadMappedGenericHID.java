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
 * Provides convenience methods that get the correct axis/button value for the role (fwdBackAxis, etc), based on the selected NomadInputMap.
 */
public class NomadMappedGenericHID extends GenericHID {
    /**
     * A placeholder controller.
     */
    public static NomadMappedGenericHID noneMappedHID = new NomadMappedGenericHID(Integer.MIN_VALUE);
    /**
     * The selected input map.
     */
    private NomadMappingEnum selectedMap = NomadMappingEnum.UNCATEGORIZED;

    /**
     * Creates a new NomadMappedGenericHID on the specified USB port.
     * @param port the USB port
     */
    public NomadMappedGenericHID(int port) {
        super(port);
    }
    /**
     * Sets the selected map.
     * @param mapType The new map.
     * @return This object, modified.
     */
    public NomadMappedGenericHID setMap(NomadMappingEnum mapType) {
        selectedMap = mapType;
        return this;
    }
    /**
     * @return The selected map.
     */
    public NomadMappingEnum getSelectedMap() {
        return selectedMap;
    }
    /**
     * Don't use.
     */
    @Override
    public double getX(Hand hand) {
        
        return 0;
    }
    /**
     * Don't use.
     */
    @Override
    public double getY(Hand hand) {
        
        return 0;
    }
    /**
     * Gets the value of the axis at the specified ID.
     */
    @Override
    public double getRawAxis(int id){
        return NomadOperatorConsole.INPUT_ENUM_MAP.get(selectedMap)
        .getRawAxis(id);
    }
    /**
     * Gets the value of the specified button.
     */
    @Override
    public boolean getRawButton(int id){
        return NomadOperatorConsole.INPUT_ENUM_MAP.get(selectedMap).getRawButton(id);
    }
    /**
     * Gets the raw axis value at the given id, not counting for any custom maps or behaviors
     * @param id The id. 
     * @return the value.
     */
    public double getHIDRawAxis(int id) throws IllegalArgumentException{
        return super.getRawAxis(id);
    }
    /**
     * Gets the raw button value at the given id, not counting for any custom maps or behaviors
     * @param id The id.
     * @return The value.
     */
    public boolean getHIDRawButton(int id) throws IllegalArgumentException{
        return super.getRawButton(id);
    }
}
