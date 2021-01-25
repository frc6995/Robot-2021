package frc.template.constants;
/**
 * Add your docs here.
 */
public class AutoConversionFactors {
    /**
     * Convert the values from native Talon units (ticks/100ms) to WPITrajectory Units (m/s)
     * @param talonVelocity
     * @param wheelDiameter
     * @param usingMetric
     * @param ticksPerRevolution
     * @return
     */
    public static final double convertTalonSRXNativeUnitsToWPILibTrajectoryUnits(double talonVelocity, double wheelDiameter, boolean usingMetric, int ticksPerRevolution){
        double result = talonVelocity;
        result = result*10; //Convert ticks/100ms to ticks/sec
        
        double circumference = 0;
        if(usingMetric){
            circumference = Math.PI * wheelDiameter;
        }else{
            double diameterInMeters = wheelDiameter*0.3048;
            circumference = Math.PI*diameterInMeters;
        }
        double metersPerTick = circumference/ticksPerRevolution;
        result = result *metersPerTick;
        return result;
    }

    /**
     * Convert the values from WPITrajectory Units (m/s) to native Talon units (ticks/100ms)
     * @param metersPerSecond
     * @param wheelDiameter
     * @param givenMetric
     * @param ticksPerRevolution
     * @return
     */
    public static final double convertWPILibTrajectoryUnitsToTalonSRXNativeUnits(double metersPerSecond, double wheelDiameter, boolean givenMetric, int ticksPerRevolution){
        double result = metersPerSecond;
        double circumference = 0;
        if(givenMetric){
            circumference = Math.PI * wheelDiameter;
        }else{
            double diameterInMeters = wheelDiameter*0.3048;
            circumference = Math.PI*diameterInMeters;
        }
        double ticksPerMeter = ticksPerRevolution/circumference;
        result = result * ticksPerMeter;
        result = result * .1;
        
        return result;
    }


    public static double convertTalonEncoderTicksToMeters(int ticks, double diameter, double ticksPerRevolution, boolean givenMetric){
        double result = ticks;
        double circumference = 0;
        if(givenMetric){
            circumference = Math.PI *diameter;
        }
        else{
            double diameterInMeters = diameter*0.3048;
            circumference = Math.PI*diameterInMeters;
        }
        double metersPerTick = circumference/ticksPerRevolution;
        result = result * metersPerTick;
        return result;
    }

    public static double convertFeetToMeters(double value){
        return value * 0.3048;
    }
    public static double convertMetersToFeet(double value){
        return value * 1/0.3048;
    }
}