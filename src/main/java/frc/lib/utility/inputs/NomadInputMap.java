// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.utility.inputs;

import java.util.HashMap;
import java.util.Map;

import frc.lib.wrappers.inputdevices.NomadAxis;
import frc.lib.wrappers.inputdevices.NomadButton;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** A way to   */
public class NomadInputMap {
    
    private NomadMappingEnum mapType = NomadMappingEnum.UNCATEGORIZED;
    private String mapName;
    private Map<Integer, NomadButton> buttons = new HashMap<Integer, NomadButton>();
    private Map<Integer, NomadAxis> axes = new HashMap<Integer, NomadAxis>(); //

    public NomadInputMap(NomadMappingEnum map, String name) {
        mapType = map;
        mapName = name;
    }
    /**
     * Adds the given NomadButton to the map, overwriting any current button tied to the same ID.
     * @param button The new NomadButton to add.
     * @return The modified map.
     */
    public NomadInputMap withType(NomadMappingEnum map) {
        mapType = map;
        return this;
    }

    public NomadInputMap withName(String name) {
        mapName = name;
        return this;
    }

    public NomadInputMap withButton(NomadButton button){ //change the map on the button to this one.
        buttons.put(button.getId(), button.withMap(mapType)); //add it
        return this;
    }

    public NomadInputMap withAxis(NomadAxis axis) {
        axis.withMap(mapType);
        axes.put(axis.getId(),
        axis);
        return this;
    }

    public String getMappingString() {
        String chart = String.format("%s:\nButtons:\n", mapName);
        buttons.forEach((id, button)-> {chart.concat(String.format("%s...%s", button.getName(), button.getId()));});
        return chart;
    }

    public double getRawAxis(int id){
        return getAxis(id)
        .get();
        
    }

    public boolean getRawButton(int id){
        return buttons.get((Integer) id).get();
    }

    public NomadAxis getAxis(int id){
        try{
            NomadAxis axis = axes.get(id);
            if(axis != null) {
                return axes.get(id);
            } else throw new NullPointerException();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public NomadButton getButton(int id){
        try{
            return buttons.get(id);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getName() {
        return mapName;
    }

    public NomadMappingEnum getType() {
        return mapType;
    }

    
}
