// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.utility.inputs;

import java.util.HashMap;
import java.util.Map;

import frc.lib.wrappers.inputdevices.NomadAxis;
import frc.lib.wrappers.inputdevices.NomadButton;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** A way to specify a set of controller inputs (axes and buttons) which route to specified functionality. */
public class NomadInputMap {
    /**
     * The NomadMappingEnum matching this map.
     */
    private NomadMappingEnum mapType = NomadMappingEnum.UNCATEGORIZED;
    /**
     * A human-facing string describing the map.
     */
    private String mapName;
    /**
     * A HashMap of button IDs to NomadButtons. The Nomad-specific ID standard is described in {link NomadOperatorConsole}
     */
    private Map<Integer, NomadButton> buttons = new HashMap<Integer, NomadButton>();
    /**
     * A HashMap of axis IDs to NomadAxes. The Nomad-specific ID standard is described in {link NomadOperatorConsole}
     */
    private Map<Integer, NomadAxis> axes = new HashMap<Integer, NomadAxis>(); //
    /**
     * Create a new NomadInputMap with the given type and name.
     * @param map The map type. Must be unique.
     * @param name The map name. Should be descriptive of the map's functionality.
     */
    public NomadInputMap(NomadMappingEnum map, String name) {
        mapType = map;
        mapName = name;
    }
    /**
     * Changes the type of this map.
     * @param map The new type.
     * @return This map, modified.
     */
    public NomadInputMap withType(NomadMappingEnum map) {
        mapType = map;
        return this;
    }
    /**
     * Changes the name of this map.
     * @param name The new name.
     * @return This map, modified.
     */
    public NomadInputMap withName(String name) {
        mapName = name;
        return this;
    }
    /**
     * Adds the given NomadButton to the map, overwriting any current button tied to the same ID.
     * @param button The new NomadButton to add.
     * @return The modified map.
     */
    public NomadInputMap withButton(NomadButton button){ //change the map on the button to this one.
        buttons.put(button.getId(), button.withMap(mapType)); //add it
        return this;
    }
    /**
     * Adds the given NomadAxis to the map, overwriting any current axis tied to the same ID.
     * @param axis The new NomadAxis to add.
     * @return The modified map.
     */
    public NomadInputMap withAxis(NomadAxis axis) {
        axis.withMap(mapType);
        axes.put(axis.getId(),
        axis);
        return this;
    }
    /**
     * Returns a nicely-formatted multiline string describing the mapping arrangement.
     * @return
     */
    public String getMappingString() {
        /*String chart = String.format("%s:\nButtons:\n", mapName);
        buttons.forEach((id, button)-> {chart.concat(String.format("%s...%s", button.getName(), button.getId()));});
        return chart;*/
        return "Unimplemented";
    }
    /**
     * Gets the double output of the specified axis.
     * @param id The axis ID
     * @return its value.
     */
    public double getRawAxis(int id){
        return getAxis(id)
        .get();
        
    }
     /**
      * Gets the boolean output of the specified button.
      * @param id The button ID
      * @return its value.
      */
    public boolean getRawButton(int id){
        return getButton(id).get();
    }
    /**
     * Returns the NomadAxis object (not its value) with the given ID.
     * @param id The axis ID
     * @return
     */
    public NomadAxis getAxis(int id){
        try{
            return axes.get(id);
        } catch (NullPointerException e) {
            return null;
        }
    }
    /**
     * Returns the NomadButton object (not its value) with the given ID.
     * @param id The button ID
     * @return
     */
    public NomadButton getButton(int id){
        try{
            return buttons.get(id);
        } catch (NullPointerException e) {
            return null;
        }
    }
    /**
     * 
     * @return the map's name.
     */
    public String getName() {
        return mapName;
    }
    /**
     * 
     * @return the map's type.
     */
    public NomadMappingEnum getType() {
        return mapType;
    }

    
}
