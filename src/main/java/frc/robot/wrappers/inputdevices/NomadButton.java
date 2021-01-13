// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.wrappers.inputdevices;

import java.util.function.BooleanSupplier;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.utility.inputs.NomadInputMaps.NomadMappingEnum;

/** Add your docs here. */
public class NomadButton extends Button {
    /**
     * A brief description of the button's functionality.
     */
    private String name = "unnamed";
    /**
     * The map 
     */
    private NomadMappingEnum map;
    private NomadMappedGenericHID controller;
    private int id;
    private BooleanSupplier customBehavior;

    /**
   * Default constructor; creates a button that is never pressed (unless {@link Button#get()} is
   * overridden).
   */
  public NomadButton() {}

    /**
   * 
   */
  public NomadButton(NomadMappedGenericHID controller, int id, String buttonName, NomadMappingEnum buttonMap) {
      super();
      this.id = id;
      this.controller = controller;
      name = buttonName;
      map = buttonMap;
      
  }

  @Override
  public boolean get() {
    if (map == controller.getSelectedMap()) {
        if (id > 0 && id < 31) { // DS limitation on buttons sent from a single controller
            return (controller.getRawButton(id));
        }    
        else{
            return customBehavior.getAsBoolean();
        }
      }
    return false;
  }

  public int getId() {
      return id;
  }
  public String getName() {
      return name;
  }

  public NomadButton withMap(NomadMappingEnum mapType) {
      map = mapType;
      return this;
  }

  public NomadButton withName(String name) {
      this.name = name;
      return this;
  }
}
