// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.EnumMap;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import frc.lib.constants.DriveConstants;
import frc.lib.constants.DriverStationConstants;
import frc.lib.utility.inputs.NomadInputMap;
import frc.lib.utility.inputs.NomadInputMaps;
import frc.lib.utility.math.NomadMathUtil;
import frc.lib.wrappers.inputdevices.NomadAxis;
import frc.lib.wrappers.inputdevices.NomadButton;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** Add your docs here. */
public class Robot2021NomadInputMaps extends NomadInputMaps {


    //public static final EnumMap<NomadMappingEnum, NomadInputMap> inputEnumMap = new EnumMap<NomadMappingEnum, NomadInputMap>(NomadMappingEnum.class);
    /**
     * All NomadAxes and Buttons are by default part of this map. 
     */
    /**
     * The default/template drive map. Other drive maps should follow this standard:
     * Custom Axis 33: FWD/BACK
     * Custom Axis 34: LFT/RGHT
     */
    public static NomadInputMap baseControllerMap = new NomadInputMap(NomadMappingEnum.BASE_MAP, "BaseDriveUninit");
    public static NomadInputMap driveControllerMap = new NomadInputMap(NomadMappingEnum.DEFAULT_DRIVE, "TemplateDriveUninit");
    public static NomadInputMap driveControllerTriggerDrive = new NomadInputMap(NomadMappingEnum.TRIGGER_DRIVE, "Trigger DriveUninit");
    public static NomadInputMap driveControllerOgXboxTriggerDrive = new NomadInputMap(NomadMappingEnum.OG_TRIGGER_DRIVE, "OGXboxDriveUninit");

    
    public static void createMaps(DriveConstants driveConstants, DriverStationConstants driverStationConstants) {
        createControllerMap(baseControllerMap, "BaseDrive");
        createDriveControllerMap(driveConstants, driverStationConstants, driveControllerMap, "TemplateDrive");
        //Trigger drive only differs in fwd/back axis
        createTriggerDriveControllerMap(driveConstants, driverStationConstants, driveControllerTriggerDrive, "TriggerDrive");
        createOGXboxTriggerDriveControllerMap(driveConstants, driverStationConstants, driveControllerOgXboxTriggerDrive, "OGXboxDrive");
        
    }
    /**
     * Change the given map to have the stick drive functionality of the driveControllerMap. Will change the actual given map object..
     * @param map
     * @return
     */
    public static NomadInputMap createDriveControllerMap(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadInputMap map, String name) {
        createControllerMap(map, name);
        map.withAxis(
            //new NomadAxis(id, axisName, customAxisBehavior)
            new NomadAxis(driveConstants.getDriveControllerFwdBackAxis(), "FWD/BACK", (DoubleSupplier) () -> {
                return driveConstants.getDriveControllerFwdBackAxisMultiplier() * NomadOperatorConsole.getRawAxis(NomadOperatorConsole.getCombinedID(driverStationConstants.getDriveControllerUsbPort(), XboxController.Axis.kLeftY.value));
            }).withNegativeDeadzone(-0.1).withPositiveDeadzone(0.1))
        .withAxis(
            new NomadAxis(driveConstants.getDriveControllerLeftRightAxis(), "LEFT/RIGHT", (DoubleSupplier) () -> {
                return driveConstants.getDriveControllerLeftRightAxisMultiplier() * NomadOperatorConsole.getRawAxis(XboxController.Axis.kLeftX.value);
            })
            .withNegativeDeadzone(-0.1)
            .withPositiveDeadzone(0.1)
            .withScaleFactor(0.5));
        return map;
    }

    public static NomadInputMap createTriggerDriveControllerMap(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadInputMap map, String name) {
        createDriveControllerMap(driveConstants, driverStationConstants, map, name); //Add the default left stick drive behavior
        map.withAxis( //
            map.getAxis(driveConstants.getDriveControllerFwdBackAxis()) //Change the axis behavior for fwd/back 
            .withCustomBehavior(
                (DoubleSupplier) () -> {
                    return NomadOperatorConsole.getRawAxis(XboxController.Axis.kLeftTrigger.value) 
                    - NomadOperatorConsole.getRawAxis(XboxController.Axis.kRightTrigger.value);
                }
            )
        )
        .withType(NomadMappingEnum.TRIGGER_DRIVE)
        ;
        return map;
    }
    
    public static NomadInputMap createOGXboxTriggerDriveControllerMap(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadInputMap map, String name) {
        createDriveControllerMap(driveConstants, driverStationConstants, map, name);
        map.withAxis(
            map.getAxis(driveConstants.getDriveControllerFwdBackAxis())
            .withCustomBehavior(
                (DoubleSupplier) () -> {
                    return 
                    NomadMathUtil.lerp(NomadOperatorConsole.getRawAxis(XboxController.Axis.kLeftTrigger.value), -1.0, 1.0, 0.0, 1.0) 
                        - NomadMathUtil.lerp(NomadOperatorConsole.getRawAxis(XboxController.Axis.kRightTrigger.value), -1.0, 1.0, 0.0, 1.0);
                }
            )
        )
        .withType(NomadMappingEnum.OG_TRIGGER_DRIVE)
        ;
        return map;
    }
}
