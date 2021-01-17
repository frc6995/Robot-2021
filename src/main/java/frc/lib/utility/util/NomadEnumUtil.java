/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.lib.utility.util;

import java.util.Arrays;

/**
 * Add your docs here.
 */
public class NomadEnumUtil {
    /**
     * Useful when you need to add an enum to a SimDevice. Might help for other things.
     * @param e the enum
     * @return a String[] of the enum's values.
     */
    public static String[] enumToStringArray(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
