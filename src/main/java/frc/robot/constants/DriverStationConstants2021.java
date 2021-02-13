// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

import frc.lib.constants.DriverStationConstants;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** Add your docs here. */
public class DriverStationConstants2021 implements DriverStationConstants {
    public static final int DRIVER_CONTROLLER_USB_PORT = 0;
	public static final NomadMappingEnum DRIVER_CONTROLLER_MAP = NomadMappingEnum.DEFAULT_DRIVE;

    @Override
    public int getDriveControllerUsbPort() {
        // TODO Auto-generated method stub
        return DRIVER_CONTROLLER_USB_PORT;
    }

    @Override
    public NomadMappingEnum getSelectedMap() {
        // TODO Auto-generated method stub
        return DRIVER_CONTROLLER_MAP;
    }
}
