package frc.lib.wrappers.inputdevices;

import java.util.function.DoubleSupplier;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.lib.wrappers.inputdevices.NomadOperatorConsole.NomadMappingEnum;

public class NomadAxis {

    public static NomadAxis noneAxis = new NomadAxis();
    /**
     * A brief description of the Axis's functionality.
     */
    private String name = "unnamed";
    private boolean isCustomAxis;
    /**
     * The map 
     */
    private NomadMappingEnum map = NomadMappingEnum.UNCATEGORIZED;
    private NomadMappedGenericHID controller = NomadMappedGenericHID.noneMappedHID;
    private int id;
    private DoubleSupplier customBehavior;

    /**
   * Default constructor; creates a Axis that is never pressed (unless {@link Axis#get()} is
   * overridden).
   */
  public NomadAxis() {}
  /**
   * Default with id for filling in input maps.
   * @param id
   */
  public NomadAxis(int id) {
      super();
      this.id = id;
  }

    /**
     * Constructs a NomadAxis
     * @param controller the NomadMappedGenericHID this axis pulls inputs from
     * @param id The identifier of this axis. Axes 0-11 are reserved for direct hardware access
     * If you are overriding get() to make a custom axis behavior, use an id above 11. 
     * @param axisName A brief summary of the axis' functionality
     * @param axisMap Which input map this axis belongs to.
     */
  public NomadAxis(int id, String axisName) {
      super();
      this.id = id;
      name = axisName;
  }

      /**
     * Constructs a NomadAxis with a custom behavior.
     * @param controller the NomadMappedGenericHID this axis pulls inputs from
     * @param id The identifier of this axis. Axes 0-11 are reserved for direct hardware access. The custom behavior will not run if id is below 11.
     * @param axisName A brief summary of the axis' functionality
     * @param axisMap Which input map this axis belongs to.
     * @param customAxisBehavior A function that returns the axis value.
     */
    public NomadAxis(/*NomadMappedGenericHID controller,*/ int id, String axisName, DoubleSupplier customAxisBehavior) {
        super();
        this.id = id;
        //this.controller = controller;
        name = axisName;
        customBehavior = customAxisBehavior;
    }
    /**
     * Read the value of the axis. Depending on the id, this will either read from the controller's hardware axis or the defined custom behavior.
     * @return the value of the axis.
     */
  public final double get() {
      if (map.equals(NomadOperatorConsole.getSelectedMap()) 
      && !(map.equals(NomadMappingEnum.UNCATEGORIZED))) {
        return customBehavior.getAsDouble();
      }
      return 0.0;

  }

  public int getId() {
      return id;
  }
  public String getName() {
      return name;
  }

  public NomadAxis withMap(NomadMappingEnum mapType) {
      map = mapType;
      return this;
  }

  public NomadAxis withName(String name) {
      this.name = name;
      return this;
  }

  public NomadAxis withCustomBehavior(DoubleSupplier behavior) {
      customBehavior = behavior;
      return this;
  }

  public NomadAxis withController(NomadMappedGenericHID controller){
      this.controller = controller;
      return this;
  }
}
