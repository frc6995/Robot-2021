package frc.lib.wrappers.inputdevices;

import java.util.function.DoubleSupplier;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

/**
 * A representation of a controller axis, with its behavior defined at construction.
 * This allows a NomadAxis to perform calculations on inputs before returning its value.
 */
public class NomadAxis {

    public static NomadAxis noneAxis = new NomadAxis();
    /**
     * A brief description of the Axis's functionality.
     */
    private String name = "unnamed";
    /**
     * The map 
     */
    private NomadMappingEnum map = NomadMappingEnum.UNCATEGORIZED;
    private int id;
    private DoubleSupplier customBehavior = () -> {return 0.0;};

    /**
   * Default constructor; creates a Axis that is never moved away from 0 (unless {@link Axis#get()} is
   * overridden).
   */
  public NomadAxis() {}
  /**
   * Default with id for filling in input maps.
   * @param id
   */
  public NomadAxis(int id) {
      
      this.id = id;
  }

  /**
   * Creates a NomadAxis with an id and name.
   * @param id The id.
   * @param axisName The name.
   */
  public NomadAxis(int id, String axisName) {
      
      this.id = id;
      name = axisName;
  }

    /**
     * Constructs a NomadAxis with a custom behavior.
     * @param id The identifier of this axis. Axes 0-11 are reserved for direct hardware access. The custom behavior will not run if id is below 11.
     * @param axisName A brief summary of the axis' functionality
     * @param customAxisBehavior A function that returns the axis value.
     */
    public NomadAxis(int id, String axisName, DoubleSupplier customAxisBehavior) {
        
        this.id = id;
        name = axisName;
        customBehavior = customAxisBehavior;
    }
    /**
     * If the operator console's selected map matches this map, read the value of the axis using the defined custom behavior (which may be a simple hardware controller call).
     * @return the value of the axis, or 0.0 if the map is not correct.
     */
  public final double get() {
      if (map.equals(NomadOperatorConsole.getSelectedMap()) 
      && !(map.equals(NomadMappingEnum.UNCATEGORIZED))) {
        return customBehavior.getAsDouble();
      }
      return 0.0;

  }
  /**
   * 
   * @return The axis's ID.
   */
  public int getId() {
      return id;
  }

  /**
   * 
   * @return The axis name.
   */
  public String getName() {
      return name;
  }
  /**
   * Set the map this axis is a part of.
   * @param mapType The map as an enum.
   * @return This axis, modified.
   */
  public NomadAxis withMap(NomadMappingEnum mapType) {
      map = mapType;
      return this;
  }
  /**
   * Set the name for this axis.
   * @param name The new name.
   * @return This axis, modified.
   */
  public NomadAxis withName(String name) {
      this.name = name;
      return this;
  }
  /**
   * Set the custom behavior of the axis.
   * @param customBehavior The functionality that returns this axis's value.
   * @return This axis, modified.
   */
  public NomadAxis withCustomBehavior(DoubleSupplier behavior) {
      customBehavior = behavior;
      return this;
  }
}
