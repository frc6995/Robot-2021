// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.lib.wrappers.inputdevices;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.button.Button;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/** A representation of a controller button, with its behavior defined at construction.
 * This allows it to return a value based on an arbitrary calcluation, and trigger commands based on that.
 */
public class NomadButton extends Button {
    /**
     * A placeholder button object that is never triggered, but is sufficient to avoid a NullPointerException.
     */
    public static NomadButton noneButton = new NomadButton();
	/**
     * A brief description of the button's functionality.
     */
    private String name = "unnamed";
    /**
     * The map this button is part of.
     */
    private NomadMappingEnum map = NomadMappingEnum.UNCATEGORIZED;
    private int id;
    private BooleanSupplier customBehavior = () -> false;

    /**
   * Default constructor; creates a button that is never pressed (unless {@link Button#get()} is
   * overridden).
   */
  public NomadButton() {}

  /**
   * Creates a NomadButton with a given id.
   * @param id The id.
   */
  public NomadButton(int id) {
      super();
      this.id = id;
  }

  /**
   * Creates a NomadButton with an id and a name.
   * @param id The id.
   * @param buttonName The name.
   */
  public NomadButton(int id, String buttonName) {
      super();
      this.id = id;
      name = buttonName;      
  }
  /**
   * Returns the value from the defined custom behavior, if the selected map matches this one.
   * @return The button's value, or false if the map is not correct.
   */
  @Override
  public boolean get() {
    if (map.equals(NomadOperatorConsole.getSelectedMap())) {
        return customBehavior.getAsBoolean();
      }
      return false;
  }
  /**
   * @return the button id.
   */
  public int getId() {
      return id;
  }
  /**
   * @return the button name.
   */
  public String getName() {
      return name;
  }
  /**
   * Set the button's map.
   * @param mapType The new map.
   * @return This button, modified.
   */
  public NomadButton withMap(NomadMappingEnum mapType) {
      map = mapType;
      return this;
  }
  /**
   * Set the button's name.
   * @param name the new name.
   * @return This button, modified.
   */
  public NomadButton withName(String name) {
      this.name = name;
      return this;
  }
  /**
   * Set the button's custom behavior.
   * @param behavior The new behavior.
   * @return This button, modified.
   */
  public NomadButton withCustomBehavior(BooleanSupplier behavior) {
    customBehavior = behavior;
        return this;
    }
}
