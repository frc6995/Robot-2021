// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.constants;

import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** Subclass this class to describe the operator console-related constants. */
public interface DriverStationConstants {
    public int getDriveControllerUsbPort();
    public NomadMappingEnum getSelectedMap();
}
