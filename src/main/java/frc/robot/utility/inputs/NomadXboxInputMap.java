// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utility.inputs;

import java.util.Map;
import java.util.function.BiConsumer;

import frc.robot.utility.inputs.NomadInputMaps.NomadMappingEnum;
import frc.robot.wrappers.inputdevices.NomadAxis;
import frc.robot.wrappers.inputdevices.NomadButton;

/** A way to   */
public class NomadXboxInputMap {
    private NomadMappingEnum mapType;
    private String mapName;
    private Map<Integer, NomadButton> buttons;
    private Map<Integer, NomadAxis> axes; //

    public NomadXboxInputMap(NomadMappingEnum map, String name) {
        mapType = map;
        mapName = name;
    }
    /**
     * Adds the given NomadButton to the map, overwriting any current button tied to the same ID.
     * @param button The new NomadButton to add.
     * @return The modified map.
     */
    public NomadXboxInputMap withType(NomadMappingEnum map) {
        mapType = map;
        return this;
    }

    public NomadXboxInputMap withName(String name) {
        mapName = name;
        return this;
    }

    public NomadXboxInputMap withButton(NomadButton button){ //change the map on the button to this one.
        buttons.put(button.getId(), button.withMap(mapType)); //add it
        return this;
    }

    public NomadXboxInputMap withAxis(NomadAxis axis) {
        axes.put(axis.getId(), axis.withMap(mapType));
        return this;
    }

    public String getMappingString() {
        String chart = String.format("%s:\nButtons:\n", mapName);
        buttons.forEach((id, button)-> {chart.concat(String.format("%s...%s", button.getName(), button.getId()));});
        return chart;
    }

    public double getRawAxis(int id){
        return axes.get((Integer) id).get();
    }

    public boolean getRawButton(int id){
        return buttons.get((Integer) id).get();
    }

    public NomadAxis getAxis(int id){
        return axes.get(id);
    }

    
}
