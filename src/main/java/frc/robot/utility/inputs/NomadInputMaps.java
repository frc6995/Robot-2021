// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.utility.inputs;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.constants.DriveConstants;
import frc.robot.constants.DriverStationConstants;
import frc.robot.wrappers.inputdevices.NomadAxis;
import frc.robot.wrappers.inputdevices.NomadMappedGenericHID;

/** Add your docs here. */
public class NomadInputMaps {
    public static enum NomadMappingEnum {
        UNCATEGORIZED (null),
        TRIGGER_DRIVE (NomadInputMaps.driveControllerTriggerDrive),
        OG_TRIGGER_DRIVE (NomadInputMaps.driveControllerOgXboxTriggerDrive),
        DEFAULT_DRIVE (NomadInputMaps.driveControllerMap);

        private NomadXboxInputMap equivalentMap;

        private NomadMappingEnum (NomadXboxInputMap map){
            equivalentMap = map;
        }

        public NomadXboxInputMap getMap() {
            return equivalentMap;
        }
    }
    /**
     * All NomadAxes and Buttons are by default part of this map. 
     */
    public static final NomadXboxInputMap uncategorized = new NomadXboxInputMap(NomadMappingEnum.UNCATEGORIZED, "Uncategorized");
    /**
     * The default/template drive map. Other drive maps should follow this standard:
     * Custom Axis 33: FWD/BACK
     * Custom Axis 34: LFT/RGHT
     */
    public static NomadXboxInputMap driveControllerMap = new NomadXboxInputMap(NomadMappingEnum.DEFAULT_DRIVE, "Template Drive");
    public static NomadXboxInputMap driveControllerTriggerDrive = new NomadXboxInputMap(NomadMappingEnum.TRIGGER_DRIVE, "Trigger Drive");
    public static NomadXboxInputMap driveControllerOgXboxTriggerDrive = new NomadXboxInputMap(NomadMappingEnum.OG_TRIGGER_DRIVE, "Stick Drive");
    public void createMaps(NomadMappedGenericHID driveController, DriveConstants driveConstants) {
        driveControllerMap.withAxis(
            //new NomadAxis(controller, id, axisName, axisMap, customAxisBehavior)
            new NomadAxis(driveController, 33, "FWD/BACK", (DoubleSupplier) () -> {
                return driveController.getRawAxis(XboxController.Axis.kLeftY.value);
            }))
        .withAxis(
            new NomadAxis(driveController, 34, "LEFT/RIGHT", (DoubleSupplier) () -> {
                return driveController.getRawAxis(XboxController.Axis.kLeftX.value);
            }))
        ;
        //Trigger drive only differs in fwd/back axis
        driveControllerTriggerDrive = driveControllerMap
        .withName("TriggerDrive")
        .withType(NomadMappingEnum.TRIGGER_DRIVE)
        .withAxis(
            driveControllerMap.getAxis(driveConstants.getDriveControllerFwdBackAxis())
            .withCustomBehavior(
                (DoubleSupplier) () -> {
                    return driveController.getRawAxis(XboxController.Axis.kLeftTrigger.value) 
                    - driveController.getRawAxis(XboxController.Axis.kRightTrigger.value);
                }
            )
        );
    }    
}
