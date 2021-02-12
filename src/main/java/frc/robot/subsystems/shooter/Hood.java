// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.Servo;
import frc.lib.utility.math.NomadMathUtil;
import frc.robot.constants.HoodConstants;
import frc.robot.constants.HoodConstants2021;
import java.awt.Point;

/**
 * An adjustible hood that controls the angle at which the power cell is
 * launched.
 * 
 * @author JoeyFabel
 */
public class Hood {
    /**
     * The Hood's left linear servo
     */
    private Servo leftLinearServo;
    /**
     * The Hood's right linear servo
     */
    private Servo rightLinearServo;
    /**
     * The constants for the Hood
     */
    private HoodConstants constants;

    /**
     * Create a new Hood with the {@link HoodConstants2021} as the constants.
     * @param leftLinearServo The left {@link Servo}
     * @param rightLinearServo The right {@link Servo}
     */
    public Hood(Servo leftLinearServo, Servo rightLinearServo){
        this(new HoodConstants2021(), leftLinearServo, rightLinearServo);
    }

    /**
     * Create a new Hood with the specified constants.
     * @param constants The constants to use in this Hood
     * @param leftLinearServo The left {@link Servo}
     * @param rightLinearServo The right {@link Servo}
     */
    public Hood(HoodConstants constants, Servo leftLinearServo, Servo rightLinearServo){
        this.constants = constants;
        this.leftLinearServo = leftLinearServo;
        this.rightLinearServo = rightLinearServo;
    }

    public double getAngleBasedOnDistance(double distance){
        Point leftEndPoint = constants.getClosestEndpointDownard(distance);
        Point rightEndPoint = constants.getClosestEndpointUpward(distance);

        // percentage is the inverse lerp between x-values
        double percentage = NomadMathUtil.inverseLerp(leftEndPoint.getX(), rightEndPoint.getX(), distance);

        // angle is the lerp between the y-values
        return NomadMathUtil.lerp(leftEndPoint.getY(), rightEndPoint.getY(), percentage);
    }

    /**
     * Get the position of the linear servos, by averaging the two.
     * @return The position of the linear servo
     */
    public double getLinearServoPosition(){
        return (leftLinearServo.get() + rightLinearServo.get()) / 2;
    }

    /**
     * Get the position of the linear servos, choosing whether or not to average.
     * @param averageOfTwo If true, averages the linear servo positions. If false, just get the left servo's position
     * @return The position of the linear servo
     */
    public double getLinearServoPosition(boolean averageOfTwo){
        return averageOfTwo ? getLinearServoPosition() : leftLinearServo.get();
    }

    public void moveHoodToPosition(double position){
        leftLinearServo.set(position);
    }
}
