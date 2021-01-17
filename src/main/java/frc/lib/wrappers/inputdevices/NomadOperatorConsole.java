// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.wrappers.inputdevices;

import java.util.EnumMap;
import java.util.HashMap;

import frc.lib.utility.inputs.NomadInputMap;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/**
 * This class condenses all operator input into one class.
 */
public class NomadOperatorConsole {
    public static final int maxSupportedPOVs = 8; // 12 is too many to easily fit in the mapping. Plus, who does that?
    private NomadMappingEnum selectedMap = NomadMappingEnum.UNCATEGORIZED;
    public static enum NomadMappingEnum {
        UNCATEGORIZED,
        TRIGGER_DRIVE,
        OG_TRIGGER_DRIVE,
        DEFAULT_DRIVE
        /*UNCATEGORIZED (NomadInputMaps.baseControllerMap),
        TRIGGER_DRIVE (NomadInputMaps.controllerTriggerDrive),
        OG_TRIGGER_DRIVE (NomadInputMaps.driveControllerOgXboxTriggerDrive),
        DEFAULT_DRIVE (NomadInputMaps.driveControllerMap);*/

    }
    public static final EnumMap<NomadMappingEnum, NomadInputMap> inputEnumMap = new EnumMap<NomadMappingEnum, NomadInputMap>(NomadMappingEnum.class);
    private static HashMap<Integer, NomadMappedGenericHID> controllers = new HashMap<>();

    public static void init() {
        for (int i = 0; i < 5; i++){
            NomadMappedGenericHID controller = new NomadMappedGenericHID(i);
            controllers.put(i, controller);
        }
    }
    /**
     * 
     */
   
    public static boolean getRawButton(int id) {
        return controllers.get(getControllerPort(id)).getRawButton(getInputPort(id));
    }

    public static double getRawAxis(int id) {
        return controllers.get(getControllerPort(id)).getRawAxis(getInputPort(id));
    }

    public static int getControllerPort(int id) {
        int controllerPort = (int) (id/100.0);
        return controllerPort;
    }

    public static int getInputPort(int id) {
        int inputPort = id % 100;
        return inputPort;
    }
    /**
     * Takes a NomadInputMap and populates it with axes and buttons for every HID connected. To prevent overlap, IDs are assigned using a "hotel room" scheme:
     * id = (port * 100) + id
     * POVs are assigned in the same list as, but in a higher index range than, the buttons. The formula is (povIndex*8) + (povAngle / 45) + (port * 100) + 600
     * Thus, the button list is broken up as follows:
     * [000-031 Controller 0 buttons][032-040 Controller 0 POV 0 buttons][]
     * @param map A NomadInputMap to populate.
     * @return the populated map.
     */
    public static NomadInputMap populateMap(NomadInputMap map) {
        controllers.forEach(
            (port, controller) -> {
                for (int axisId = 0; axisId < controller.getAxisCount(); axisId++) {
                    if (map.getAxis(axisId) == null) { //make sure we actually need to make a new axis.
                        map.withAxis(new NomadAxis(
                            axisId + 100*port, "" + axisId)
                            .withController(controller));
                    }
                }
                for (int buttonId = 0; buttonId < controller.getButtonCount(); buttonId++) {
                    if (map.getButton(buttonId) == null) {
                        map.withButton(
                            new NomadButton(
                                buttonId + 100*port, "" + buttonId)
                                .withController(controller));
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
                                .withController(controller)
                            );
                        }
                    }
                }
            }
        );

        inputEnumMap.put(map.getType(), map);
        return map;
    }
}
