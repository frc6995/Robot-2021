package frc.robot.constants;

import java.awt.Point;

/** The constants for the {@link Hood}. */
public interface HoodConstants {
    /** Get the array of data points */
    Point[] getDataPointArray();
    /** Get the number of data points in the array */
    int getNumDataPoints();

    /** Get the closest endpoint that has a distance that is less than or equal to the given point.
     * 
     * @param point The current distance from the target
     * @return The data point that is closest to the specified position
     */
    Point getClosestEndpointDownard(double point);
    /** Get the closest endpoint that has a distance that is greater than or equal to the given point.
     * 
     * @param point The current distance from the target
     * @return The data point that is closest to the specified position
     */
    Point getClosestEndpointUpward(double point);  
    /**
     * Get the allowable error, within which the hood counts as being at the setpoint.
     * @return The allowable error
     */
    double getAllowableError();
}
