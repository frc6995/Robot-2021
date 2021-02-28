package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import frc.lib.constants.DriveConstants;
import frc.lib.utility.inputs.NomadInputMap;
import frc.lib.utility.inputs.NomadInputMaps;
import frc.lib.utility.math.NomadMathUtil;
import frc.lib.wrappers.inputdevices.NomadAxis;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;
import frc.robot.constants.interfaces.DriverStationConstants;

/** The collection of input maps for the 2021 robot.*/
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
        final double deadzone = 0.1;
        final double scaleFactor = 0.5;

        DoubleSupplier leftRightAxisSupplier = () -> driveConstants.getDriveControllerLeftRightAxisMultiplier() * NomadOperatorConsole.getRawAxis(XboxController.Axis.kLeftX.value);
        DoubleSupplier fwdBackAxisSupplier = () -> driveConstants.getDriveControllerFwdBackAxisMultiplier() * NomadOperatorConsole.getRawAxis(NomadOperatorConsole.getCombinedID(driverStationConstants.getDriveControllerUsbPort(), XboxController.Axis.kLeftY.value));

        NomadAxis axisOne = new NomadAxis(driveConstants.getDriveControllerFwdBackAxis(), "FWD/BACK", fwdBackAxisSupplier)
            .withNegativeDeadzone(-deadzone)
            .withPositiveDeadzone(deadzone);
        
        NomadAxis axisTwo = new NomadAxis(driveConstants.getDriveControllerLeftRightAxis(), "Left/Right", leftRightAxisSupplier)
            .withNegativeDeadzone(-deadzone)
            .withPositiveDeadzone(deadzone);

        map.withAxis(axisOne).withAxis(axisTwo);

        return map;
    }

    public static NomadInputMap createTriggerDriveControllerMap(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadInputMap map, String name) {        
        createDriveControllerMap(driveConstants, driverStationConstants, map, name);

        DoubleSupplier customBehavior = () -> NomadOperatorConsole.getRawAxis(XboxController.Axis.kLeftTrigger.value) - NomadOperatorConsole.getRawAxis(XboxController.Axis.kRightTrigger.value);

        NomadAxis axis = map.getAxis(driveConstants.getDriveControllerFwdBackAxis()).withCustomBehavior(customBehavior);

        return map.withAxis(axis).withType(NomadMappingEnum.TRIGGER_DRIVE);
    }
    
    public static NomadInputMap createOGXboxTriggerDriveControllerMap(DriveConstants driveConstants, DriverStationConstants driverStationConstants, NomadInputMap map, String name) {
        createDriveControllerMap(driveConstants, driverStationConstants, map, name);

        DoubleSupplier customBehavior = () -> {
            final double leftTrigger = NomadOperatorConsole.getRawAxis(XboxController.Axis.kLeftTrigger.value);
            final double rightTrigger = NomadOperatorConsole.getRawAxis(XboxController.Axis.kRightTrigger.value);

            return NomadMathUtil.lerp(leftTrigger, -1.0, 1.0, 0.0, 1.0) - NomadMathUtil.lerp(rightTrigger, -1.0, 1.0, 0.0, 1.0);
        };

        NomadAxis axis = map.getAxis(driveConstants.getDriveControllerFwdBackAxis()).withCustomBehavior(customBehavior);

        return map.withAxis(axis).withType(NomadMappingEnum.OG_TRIGGER_DRIVE);
    }
}
