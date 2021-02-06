package frc.robot.constants;

import java.awt.Point;

/** The {@link Hood} constants for the 2021 robot. */
public class HoodConstants2021 implements HoodConstants {

    public Point[] getDataPointArray() {                
        return new Point[] {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};
    }

    public int getNumDataPoints() {        
        return 5;
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
