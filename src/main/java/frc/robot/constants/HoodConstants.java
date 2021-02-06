package frc.robot.constants;

import java.awt.Point;

/** The constants for the {@link Hood}. */
public interface HoodConstants {
    Point[] getDataPointArray();
    int getNumDataPoints();

    Point getClosestEndpointDownard(double point);
    Point getClosestEndpointUpward(double point);    
}
