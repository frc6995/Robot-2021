// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.wrappers.inputdevices;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.NetworkButton;
import frc.lib.utility.inputs.NomadInputMap;
import frc.lib.utility.inputs.NomadInputMaps;

/**
 * This class condenses all operator input into one class.
 */
public class NomadOperatorConsole {
    /**
     * The maximum supported POV switches on a single controller.
     */
    public static final int maxSupportedPOVs = 6; // 12 is too many to easily fit in the mapping. Plus, who does that?
    /**
     * The selected input mapping.
     */
    private static NomadMappingEnum selectedMap = NomadMappingEnum.UNCATEGORIZED;
    /**
     * A "Rescan" button on NetworkTables.
     */
    private static  NetworkButton rescanButton;
    /**
     * A command to rescan for controllers and populate the input map with their hardware.
     */
    private static RunCommand rescanCommand;
    /**
     * An enum with a value for every available input map. Used for comparison of matching maps.
     */
    public static enum NomadMappingEnum {
        UNCATEGORIZED,
        TRIGGER_DRIVE,
        OG_TRIGGER_DRIVE,
        DEFAULT_DRIVE,
        BASE_MAP

    }
    /**
     * An EnumMap linking the NomadMappingEnum values to their input maps.
     */
    public static final EnumMap<NomadMappingEnum, NomadInputMap> inputEnumMap = 
    new EnumMap<NomadMappingEnum, NomadInputMap>(NomadMappingEnum.class);
    /**
     * A HashMap of the controllers plugged into the driver station.
     */
    private static HashMap<Integer, NomadMappedGenericHID> controllers = new HashMap<>();
    /**
     * Initializes the controllers, and adds rescan functionality.
     */
    static {
        for (int i = 0; i < 5; i++){
            NomadMappedGenericHID controller = new NomadMappedGenericHID(i);
            controllers.put(i, controller);
        }
        SmartDashboard.putBoolean("Controller Rescan", false);
        rescanButton = new NetworkButton("SmartDashboard/", "Controller Rescan");
        rescanCommand = new RunCommand(()-> {
            NomadInputMaps.repopulateMaps();
            SmartDashboard.putBoolean("Controller Rescan", false);
            System.out.println("Rescan Controllers");
        });
        rescanButton.whenPressed(rescanCommand);
    }
   /**
    * Gets the value of the button at the given ID.
    * @param id A combined ID, combining both controller port and input ID.
    * @return The button's value.
    */
    public static boolean getRawButton(int id) {
        try{
            return controllers.get(getControllerPort(id)).getRawButton(getInputPort(id));
        } catch (NullPointerException e) {
            return false;
        } 
    }
   /**
    * Gets the value of the axis at the given ID.
    * @param id A combined ID, combining both controller port and input ID.
    * @return The axis's value.
    */
    public static double getRawAxis(int id) {
        try{
            return controllers.get(getControllerPort(id)).getRawAxis(getInputPort(id));
        } catch (NullPointerException e) {
            return 0;
        }
    }
    /**
     * Parse a controller port from a combined ID.
     * @param id The combined ID.
     * @return The matching controller port.
     */
    public static int getControllerPort(int id) {
        int controllerPort = (int) (id/100.0);
        return controllerPort;
    }
    /**
     * Parse an input ID from a combined ID.
     * @param id The combined ID.
     * @return The matching input port.
     */
    public static int getInputPort(int id) {
        int inputPort = id % 100;
        return inputPort;
    }
    /**
     * Takes a NomadInputMap and populates it with axes and buttons for every HID connected. To prevent overlap, IDs are assigned using a "hotel room" scheme:
     * id = (port * 100) + id
     * POVs are assigned in the same list as, but in a higher index range than, the buttons. The formula is (povIndex*8) + (povAngle / 45) + (port * 100) + 32
     * Thus, the button list is broken up as follows:
     * [000-031 Controller 0 buttons][032-039 Controller 0 POV 0 buttons][040-047 Controller 0 POV 1 buttons]
     * @param map A NomadInputMap to populate.
     * @return the populated map.
     */
    public static NomadInputMap populateMap(NomadInputMap map) {
        controllers.forEach((BiConsumer<Integer,NomadMappedGenericHID>)
            (port, controller) -> {
                for (int axisId = 0; axisId < controller.getAxisCount(); axisId++) {
                    if (map.getAxis(axisId) == null) { //make sure we actually need to make a new axis.
                        final int id = axisId;
                        map.withAxis(new NomadAxis(axisId + 100*port, "" + axisId)
                        .withCustomBehavior((DoubleSupplier) ()-> {return controller.getHIDRawAxis(id);}));
                    }
                }
                for (int buttonId = 0; buttonId < controller.getButtonCount(); buttonId++) {
                    if (map.getButton(buttonId) == null) {
                        final int id = buttonId;
                        map.withButton(
                            new NomadButton(
                                buttonId + 100*port, "" + buttonId)
                                .withCustomBehavior((BooleanSupplier) ()-> {return controller.getHIDRawButton(id);}));
                    }
                }
                for (int pov = 0; pov < ((controller.getPOVCount() < maxSupportedPOVs) ? controller.getPOVCount(): maxSupportedPOVs); pov++){
                    final int povId = pov;
                    for(int angle = 0; angle < 360; angle += 45) {
                        final int povAngle = angle;
                        if (map.getButton(pov) == null) {
                            map.withButton(
                                new NomadButton(
                                    (pov*8) + (angle/45) + (100*port) + 600,
                                    angle + "")
                                .withCustomBehavior(
                                    ()->{
                                        
                                        return controller.getPOV(povId) == povAngle;
                                    }
                                )
                                
                            );
                        }
                    }
                }
            }
        );

        inputEnumMap.put(map.getType(), map);
        return map;
    }
    /**
     * Set the selected map.
     * @param map The new map.
     */
    public static void setMap(NomadMappingEnum map) {
        selectedMap = map;
        controllers.forEach((port, controller) ->
            {controller.setMap(map);});
    }
    /**
     * @return the selected map.
     */
    public static NomadMappingEnum getSelectedMap() {
        return selectedMap;
    }
    /**
     * Combine a controller port and input id into a combined id, using the formula below.
     * @param controllerPort The USB port index.
     * @param id the input id.
     * @return The combined ID, using the formula (100*controllerPort) + id
     */
    public static int getCombinedID(int controllerPort, int id){
        return 100*controllerPort + id;
    }
}
