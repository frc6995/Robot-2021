package frc.robot.constants;

import java.awt.Point;

/** The {@link Hood} constants for the 2021 robot. */
public class HoodConstants2021 implements HoodConstants {

    public Point[] getDataPointArray() {        
        return null;
    }

    public int getNumDataPoints() {        
        return 0;
    }

    public Point getClosestEndpointDownard(double point){
        Point closestDataPoint = getDataPointArray()[0];

        for (var currentPoint : getDataPointArray()){
            if (currentPoint.getX() > closestDataPoint.getX() && currentPoint.getX() <= point) closestDataPoint = currentPoint;            
            // otherwise, compare the two. If the current point is greater than the current closest point and less than the input point, then set it as the new closest point
        }

        return closestDataPoint;
    }

    @Override
    public Point getClosestEndpointUpward(double point) {
        Point closestDataPoint = getDataPointArray()[0];

        for (var currentPoint : getDataPointArray()){
            if (currentPoint.getX() < closestDataPoint.getX() && currentPoint.getX() >= point) closestDataPoint = currentPoint;
        }

        return closestDataPoint;
    }

}
