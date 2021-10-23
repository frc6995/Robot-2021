package frc.robot.subsystems.cannon;

import java.awt.Point;

import edu.wpi.first.wpilibj.Servo;
import frc.lib.utility.math.NomadMathUtil;
import frc.robot.constants.HoodConstants2021;
import frc.robot.constants.interfaces.HoodConstants;

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

    private double desiredAngle;

    /**
     * Create a new Hood with the {@link HoodConstants2021} as the constants.
     * 
     * @param leftLinearServo  The left {@link Servo}
     * @param rightLinearServo The right {@link Servo}
     */
    public Hood(Servo leftLinearServo, Servo rightLinearServo) {
        this(new HoodConstants2021(), leftLinearServo, rightLinearServo);
    }

    /**
     * Create a new Hood with the specified constants.
     * 
     * @param constants        The constants to use in this Hood
     * @param leftLinearServo  The left {@link Servo}
     * @param rightLinearServo The right {@link Servo}
     */
    public Hood(HoodConstants constants, Servo leftLinearServo, Servo rightLinearServo) {
        this.constants = constants;
        this.leftLinearServo = leftLinearServo;
        this.rightLinearServo = rightLinearServo;
        desiredAngle = 0;
    }

    public double getAngleBasedOnDistance(double distance) {
        Point leftEndPoint = constants.getClosestEndpointDownard(distance);
        Point rightEndPoint = constants.getClosestEndpointUpward(distance);

        // percentage is the inverse lerp between x-values
        double percentage = NomadMathUtil.inverseLerp(leftEndPoint.getX(), rightEndPoint.getX(), distance);

        // angle is the lerp between the y-values
        return NomadMathUtil.lerp(leftEndPoint.getY(), rightEndPoint.getY(), percentage);
    }

    /**
     * Get the position of the linear servos, by averaging the two.
     * 
     * @return The position of the linear servo
     */
    public double getLinearServoPosition() {
        return (leftLinearServo.get() + rightLinearServo.get()) / 2;
    }

    /**
     * Get the position of the linear servos, choosing whether or not to average.
     * 
     * @param averageOfTwo If true, averages the linear servo positions. If false,
     *                     just get the left servo's position
     * @return The position of the linear servo
     */
    public double getLinearServoPosition(boolean averageOfTwo) {
        return averageOfTwo ? getLinearServoPosition() : leftLinearServo.get();
    }

    /**
     * Move the hood to the specified position
     * 
     * @param position hood position
     */
    public void moveHoodToPosition(double position) {
        leftLinearServo.set(position);
        rightLinearServo.set(position);
        desiredAngle = position;
    }

    /**
     * Check if the servos are at the proper position
     * 
     * @return whether it is at position
     */
    public boolean isAtSetpoint() {
        return Math.abs(getLinearServoPosition() - desiredAngle) < constants.getAllowableError();
    }

}
