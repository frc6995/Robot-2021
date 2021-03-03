

package frc.robot.constants.interfaces;

import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** Subclass this class to describe the operator console-related constants. */
public interface DriverStationConstants {
    int getDriveControllerUsbPort();
    NomadMappingEnum getSelectedMap();
}
