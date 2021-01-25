// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.wrappers.inputdevices;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Button;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** Add your docs here. */
public class NomadButton extends Button {
    public static NomadButton noneButton = new NomadButton();
	/**
     * A brief description of the button's functionality.
     */
    private String name = "unnamed";
    /**
     * The map 
     */
    private NomadMappingEnum map = NomadMappingEnum.UNCATEGORIZED;
    private NomadMappedGenericHID controller = NomadMappedGenericHID.noneMappedHID;
    private int id;
    private BooleanSupplier customBehavior = () -> {return false;};

    /**
   * Default constructor; creates a button that is never pressed (unless {@link Button#get()} is
   * overridden).
   */
  public NomadButton() {}
  public NomadButton(int id) {
      super();
      this.id = id;
  }

    /**
   * 
   */
  public NomadButton(int id, String buttonName) {
      super();
      this.id = id;
      name = buttonName;      
  }

  @Override
  public boolean get() {
    if (map.equals(controller.getSelectedMap())) {
        if (id > 0 && id < controller.getButtonCount()) { // DS limitation on buttons sent from a single controller is 32
            return (controller.getHIDRawButton(id));
        }    
        else{
            return customBehavior.getAsBoolean(); // custom behavior for custom buttons
        }
      }
    return false; //real button, does not exist on current controller.
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

  public NomadButton withCustomBehavior(BooleanSupplier behavior) {
    customBehavior = behavior;
        return this;
    }
  
  public NomadButton withController(NomadMappedGenericHID controller){
    this.controller = controller;
    return this;
  }
}
