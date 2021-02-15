package frc.robot.constants;

import java.awt.Point;

/** The {@link Hood} constants for the 2021 robot. */
public class HoodConstants2021 implements HoodConstants {

    public Point[] getDataPointArray() {                
        return new Point[] {new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 4), new Point(4, 10)};
    }

    public int getNumDataPoints() {        
        return 5;
    }

    public Point getClosestEndpointDownard(double point){
        Point closestDataPoint = getDataPointArray()[0];

        for (var currentPoint : getDataPointArray()){            
            if (currentPoint.getX() > closestDataPoint.getX() && (currentPoint.getX() <= point)) closestDataPoint = currentPoint;            
        }

        return closestDataPoint;
    }

    @Override
    public Point getClosestEndpointUpward(double point) {
        Point closestDataPoint = getDataPointArray()[getNumDataPoints() - 1];

        for (var currentPoint : getDataPointArray()){
            if (currentPoint.getX() < closestDataPoint.getX() && currentPoint.getX() >= point) closestDataPoint = currentPoint;

        }
        
        return closestDataPoint;
    }

    @Override
    public double getAllowableError(){
        return 10;
    }

}
