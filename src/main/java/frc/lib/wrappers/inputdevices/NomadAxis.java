package frc.lib.wrappers.inputdevices;

import java.util.function.DoubleSupplier;

import frc.lib.utility.math.NomadMathUtil;
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
    private DoubleSupplier customBehavior = () -> 0.0;
    private double positiveDeadzone = 0;
    private double negativeDeadzone = 0;
    private double scaleFactor = 1;


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
      this(id);
      name = axisName;
  }

    /**
     * Constructs a NomadAxis with a custom behavior.
     * @param id The identifier of this axis. Axes 0-11 are reserved for direct hardware access. The custom behavior will not run if id is below 11.
     * @param axisName A brief summary of the axis' functionality
     * @param customAxisBehavior A function that returns the axis value.
     */
    public NomadAxis(int id, String axisName, DoubleSupplier customAxisBehavior) {
        this(id, axisName);
        customBehavior = customAxisBehavior;
    }
    /**
     * If the operator console's selected map matches this map, read the value of the axis using the defined custom behavior (which may be a simple hardware controller call).
     * @return the value of the axis, or 0.0 if the map is not correct.
     */
  public final double get() {
    if (!map.equals(NomadOperatorConsole.getSelectedMap())) return 0.0;

    double output = customBehavior.getAsDouble();
    
    if (output >= 0) {
        output = NomadMathUtil.lerp(output, positiveDeadzone, 1, 0, 1); //Deadzone adjustment;
        output = NomadMathUtil.clamp(output, 0.0, 1.0);
    } 
    else {
        output = NomadMathUtil.lerp(output, -1, negativeDeadzone, -1, 0); //Deadzone adjustment;
        output = NomadMathUtil.clamp(output, -1.0, 0.0);
    }
    
    return output * scaleFactor;

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
  /**
   * Set the deadzone on the custom behaviour output. Result will be as follows:
   *        0 from x= 0 to x=deadzone
   * y(x){
   *        lerp(x, deadzone, 1, 0, 1) from x = deadzone to x= 1
   */
  public NomadAxis withPositiveDeadzone(double deadzone){
      positiveDeadzone = deadzone;
      return this;
  }
  public NomadAxis withNegativeDeadzone(double deadzone){
      negativeDeadzone = deadzone;
      return this;
  }

  public NomadAxis withScaleFactor(double scale){
      scaleFactor = scale;
      return this;
  }
}
