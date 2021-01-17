// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.utility.inputs;

import java.util.EnumMap;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import frc.template.constants.DriveConstants;
import frc.template.constants.DriverStationConstants;
import frc.lib.utility.math.NomadMathUtil;
import frc.lib.wrappers.inputdevices.NomadAxis;
import frc.lib.wrappers.inputdevices.NomadButton;
import frc.lib.wrappers.inputdevices.NomadMappedGenericHID;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** Add your docs here. */
public class NomadInputMaps {


    public static final EnumMap<NomadMappingEnum, NomadInputMap> inputEnumMap = new EnumMap<NomadMappingEnum, NomadInputMap>(NomadMappingEnum.class);
    /**
     * All NomadAxes and Buttons are by default part of this map. 
     */
    public static final NomadInputMap uncategorized = new NomadInputMap(NomadMappingEnum.UNCATEGORIZED, "Uncategorized");
    /**
     * The default/template drive map. Other drive maps should follow this standard:
     * Custom Axis 33: FWD/BACK
     * Custom Axis 34: LFT/RGHT
     */
    public static NomadInputMap baseControllerMap = new NomadInputMap(NomadMappingEnum.UNCATEGORIZED, "BaseDriveUninit");
    public static NomadInputMap driveControllerMap = new NomadInputMap(NomadMappingEnum.DEFAULT_DRIVE, "TemplateDriveUninit");
    public static NomadInputMap driveControllerTriggerDrive = new NomadInputMap(NomadMappingEnum.TRIGGER_DRIVE, "Trigger DriveUninit");
    public static NomadInputMap driveControllerOgXboxTriggerDrive = new NomadInputMap(NomadMappingEnum.OG_TRIGGER_DRIVE, "OGXboxDriveUninit");

    
    public static void createMaps(NomadMappedGenericHID driveController, DriveConstants driveConstants) {
        createControllerMap(driveController, baseControllerMap, "BaseDrive");
        createDriveControllerMap(driveController, driveConstants, driveControllerMap, "TemplateDrive");
        //Trigger drive only differs in fwd/back axis
        createTriggerDriveControllerMap(driveController, driveConstants, driveControllerTriggerDrive, "TriggerDrive");
        createOGXboxTriggerDriveControllerMap(driveController, driveConstants, driveControllerOgXboxTriggerDrive, "OGXboxDrive");
        
    }

    /**
     * Modifies a bare map to include all axes and buttons present on the given controller.
     * @param driveController
     * @param map
     * @param name
     * @return
     */
    public static NomadInputMap createControllerMap(NomadMappedGenericHID driveController, NomadInputMap map, String name){
        map.withName(name);
        for (int axisId = 0; axisId < driveController.getAxisCount(); axisId++) {
            if (map.getAxis(axisId) == null) { //make sure we actually need to make a new axis.
                map.withAxis(new NomadAxis(axisId, "" + axisId).withController(driveController));
            }
        }
        for (int buttonId = 0; buttonId < driveController.getButtonCount(); buttonId++) {
            if (map.getButton(buttonId) == null) {
                map.withButton(new NomadButton(buttonId, "" + buttonId).withController(driveController));
            }
        }
        inputEnumMap.put(map.getType(), map);
        return map;
    }
    /**
     * Change the given map to have the stick drive functionality of the driveControllerMap. Will change the actual given map object..
     * @param map
     * @return
     */
    public static NomadInputMap createDriveControllerMap(NomadMappedGenericHID driveController, DriveConstants driveConstants, NomadInputMap map, String name) {
        createControllerMap(driveController, map, name);
        map.withAxis(
            //new NomadAxis(id, axisName, customAxisBehavior)
            new NomadAxis(driveConstants.getDriveControllerFwdBackAxis(), "FWD/BACK", (DoubleSupplier) () -> {
                return driveController.getRawAxis(XboxController.Axis.kLeftY.value);
            }).withController(driveController))
        .withAxis(
            new NomadAxis(driveConstants.getDriveControllerLeftRightAxis(), "LEFT/RIGHT", (DoubleSupplier) () -> {
                return driveController.getRawAxis(XboxController.Axis.kLeftX.value);
            }).withController(driveController));
        return map;
    }

    public static NomadInputMap createTriggerDriveControllerMap(NomadMappedGenericHID driveController, DriveConstants driveConstants, NomadInputMap map, String name) {
        createDriveControllerMap(driveController, driveConstants, map, name); //Add the default left stick drive behavior
        map.withAxis( //
            map.getAxis(driveConstants.getDriveControllerFwdBackAxis()) //Change the axis behavior for fwd/back 
            .withCustomBehavior(
                (DoubleSupplier) () -> {
                    return driveController.getRawAxis(XboxController.Axis.kLeftTrigger.value) 
                    - driveController.getRawAxis(XboxController.Axis.kRightTrigger.value);
                }
            )
        )
        .withType(NomadMappingEnum.TRIGGER_DRIVE)
        ;
        return map;
    }
    
    public static NomadInputMap createOGXboxTriggerDriveControllerMap(NomadMappedGenericHID driveController, DriveConstants driveConstants, NomadInputMap map, String name) {
        createDriveControllerMap(driveController, driveConstants, map, name);
        map.withAxis(
            map.getAxis(driveConstants.getDriveControllerFwdBackAxis())
            .withCustomBehavior(
                (DoubleSupplier) () -> {
                    return 
                    NomadMathUtil.lerp(driveController.getRawAxis(XboxController.Axis.kLeftTrigger.value), -1.0, 1.0, 0.0, 1.0) 
                        - NomadMathUtil.lerp(driveController.getRawAxis(XboxController.Axis.kRightTrigger.value), -1.0, 1.0, 0.0, 1.0);
                }
            )
        )
        .withType(NomadMappingEnum.OG_TRIGGER_DRIVE)
        ;
        return map;
    }
}
