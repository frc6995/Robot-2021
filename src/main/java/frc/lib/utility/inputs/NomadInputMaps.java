// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.utility.inputs;

import java.util.EnumMap;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.XboxController;
import frc.lib.constants.DriveConstants;
import frc.lib.constants.DriverStationConstants;
import frc.lib.utility.inputs.NomadInputMap;
import frc.lib.utility.math.NomadMathUtil;
import frc.lib.wrappers.inputdevices.NomadAxis;
import frc.lib.wrappers.inputdevices.NomadButton;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** Add your docs here. */
public class NomadInputMaps {


    //public static final EnumMap<NomadMappingEnum, NomadInputMap> inputEnumMap = new EnumMap<NomadMappingEnum, NomadInputMap>(NomadMappingEnum.class);
    /**
     * All NomadAxes and Buttons are by default part of this map. 
     */
    public static final NomadInputMap uncategorized = new NomadInputMap(NomadMappingEnum.UNCATEGORIZED, "Uncategorized");

    public static void createMaps() {}
    public static void repopulateMaps() {
        for (NomadInputMap map : NomadOperatorConsole.INPUT_ENUM_MAP.values()) {
            if (map.getType() != NomadMappingEnum.UNCATEGORIZED) {
                NomadOperatorConsole.populateMap(map);
            }
        }
    }

    public static NomadInputMap createControllerMap(NomadInputMap map, String name){
        map.withName(name);
        NomadOperatorConsole.populateMap(map);
        NomadOperatorConsole.INPUT_ENUM_MAP.put(map.getType(), map);
        return map;
    }
}
