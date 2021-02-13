// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDouble;
import edu.wpi.first.hal.SimDevice.Direction;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.simulation.ADXRS450_GyroSim;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.subsystems.DifferentialDrivebaseS;
import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.lib.wrappers.motorcontrollers.NomadPWMMotor;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.lib.constants.AutoConstants;
import frc.lib.constants.DriveConstants;

public class DrivebaseS extends DifferentialDrivebaseS {
  private NomadTalonSRX leftLeader;
  private NomadTalonSRX rightLeader;
  private NomadTalonSRX leftFollower;
  private NomadTalonSRX rightFollower;
  
  //private DifferentialDrive m_drive;

  private Encoder m_leftEncoder;
  private Encoder m_rightEncoder;

  private EncoderSim m_leftEncoderSim;
  private EncoderSim m_rightEncoderSim;

  private AHRS gyro = new AHRS(SPI.Port.kMXP);
  private SimDevice m_gyroSim;
  private SimDouble m_gyroYawSim;

  // Create the simulation model of our drivetrain.
  private DifferentialDrivetrainSim m_driveSim;

  private DifferentialDriveOdometry m_odometry = new DifferentialDriveOdometry(new Rotation2d());

  private Field2d m_field = new Field2d();

  /** Creates a new AutonomousDrivebaseS. */
  public DrivebaseS(DriveConstants driveConstants, AutoConstants autoConstants) {
    super(driveConstants, autoConstants);

    leftLeader = new NomadTalonSRX(driveConstants.getCanIDLeftDriveMaster(), 
        driveConstants.getLeftDriveLeaderInverted());

    rightLeader = new NomadTalonSRX(driveConstants.getCanIDRightDriveMaster(),
        driveConstants.getRightDriveLeaderInverted());

    leftFollower = new NomadTalonSRX(driveConstants.getCanIDLeftDriveFollower(),
        driveConstants.getLeftDriveFollowerInverted(), leftLeader);

    rightFollower = new NomadTalonSRX(driveConstants.getCanIDRightDriveFollower(),
        driveConstants.getRightDriveFollowerInverted(), rightLeader);
    


    //m_drive = new DifferentialDrive(leftLeader, rightLeader);

    

    m_leftEncoder = new Encoder(driveConstants.getLeftEncoderPorts()[0], driveConstants.getLeftEncoderPorts()[1],
        driveConstants.getLeftEncoderReversed(), EncodingType.k4X);

    m_rightEncoder = new Encoder(driveConstants.getRightEncoderPorts()[0], driveConstants.getRightEncoderPorts()[1],
        driveConstants.getRightEncoderReversed(), EncodingType.k4X);

    m_leftEncoder.setDistancePerPulse(driveConstants.getEncoderDistancePerPulse());
    m_rightEncoder.setDistancePerPulse(driveConstants.getEncoderDistancePerPulse());



    
    m_driveSim = new DifferentialDrivetrainSim(
        driveConstants.getDrivetrainPlant(),
        driveConstants.getDriveGearbox(),
        driveConstants.getEncoderRevolutionsPerWheelRevolution(),
        driveConstants.getkTrackWidthMeters(),
        driveConstants.getkWheelDiameter()/2.0,
        driveConstants.getSimEncoderStdDev());

    if(RobotBase.isSimulation()){
      m_leftEncoderSim = new EncoderSim(m_leftEncoder);
      m_rightEncoderSim = new EncoderSim(m_rightEncoder);
      m_gyroSim = SimDevice.create("navX");
      m_gyroYawSim = m_gyroSim.createDouble("Yaw", Direction.kOutput, 0.0);
    }
    
    SmartDashboard.putData("Field", m_field);

    resetOdometry(new Pose2d());
  }

  @Override
  public void periodic() {
    m_odometry.update(gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());
    SmartDashboard.putNumber("PoseX", m_odometry.getPoseMeters().getX());
    SmartDashboard.putNumber("PoseY", m_odometry.getPoseMeters().getY());
    m_field.setRobotPose(m_odometry.getPoseMeters());
  }

  public void simulationPeriodic() {
    // Set the inputs to the system. Note that we need to convert
    // the [-1, 1] PWM signal to voltage by multiplying it by the
    // robot controller voltage.
    m_driveSim.setInputs(leftLeader.get() * RobotController.getInputVoltage(),
    (driveConstants.getDrivebaseRightSideInverted() ? -1 : 1) * rightLeader.get() * RobotController.getInputVoltage());

    // Advance the model by 20 ms. Note that if you are running this
    // subsystem in a separate thread or have changed the nominal timestep
    // of TimedRobot, this value needs to match it.
    m_driveSim.update(0.02);
    SmartDashboard.putNumber("Left position", m_driveSim.getLeftPositionMeters());

    // Update all of our sensors.
    m_leftEncoderSim.setDistance(m_driveSim.getLeftPositionMeters());
    m_leftEncoderSim.setRate(m_driveSim.getLeftVelocityMetersPerSecond());
    m_rightEncoderSim.setDistance(m_driveSim.getRightPositionMeters());
    m_rightEncoderSim.setRate(m_driveSim.getRightVelocityMetersPerSecond());


    
    m_gyroYawSim.set(driveConstants.getGyroReversed() ? 1: -1 * m_driveSim.getHeading().getDegrees());


  }

  @Override
  public double getYaw() {
    return Math.IEEEremainder(gyro.getAngle(), 360) * (driveConstants.getGyroReversed() ? -1.0 : 1.0);
  }

  @Override
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getRate(), m_rightEncoder.getRate());
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  @Override
  public double getLeftVelocity() {
    return m_leftEncoder.getRate();
  }

  @Override
  public double getRightVelocity() {
    return m_rightEncoder.getRate();
  }

  @Override
  public DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight) {
    double leftPercent = fwdBack + leftRight;
    double rightPercent = fwdBack - leftRight;
    return new DrivebaseWheelPercentages().setLeftPercentage(leftPercent).setRightPercentage(rightPercent);
  }

  @Override
  public void drivePercentages(DrivebaseWheelPercentages percentages) {
    leftLeader.set(percentages.getLeftPercentage());
    rightLeader.set((driveConstants.getDrivebaseRightSideInverted() ? -1 : 1) * percentages.getRightPercentage());
  }

  @Override
  public void stopMotor() {
    leftLeader.stopMotor();
    rightLeader.stopMotor();
  }

  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    gyro.reset();
    m_driveSim.setPose(pose);
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getYaw()));
  }

  @Override
  public void updateTelemetry() {

    SmartDashboard.putData("Drivebase", this);
    SmartDashboard.putNumber("Left Position", m_leftEncoder.get());
    SmartDashboard.putNumber("Right Position", m_rightEncoder.get());

    // SmartDashboard.putNumber("fwdbackaxis",
    // driveConstants.getDriveControllerFwdBackAxis());
    
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    builder.setSmartDashboardType("DifferentialDrive");
    builder.setActuator(false);
    builder.setSafeState(this::stopMotor);
    builder.addDoubleProperty("Left Motor Speed", this::getLeftVelocity, null);
    builder.addDoubleProperty("Right Motor Speed", this::getRightVelocity, null);
    builder.addDoubleProperty("Left Motor % Speed", this::getLeftSetSpeed, null);
    builder.addDoubleProperty("Right Motor % Speed", this::getRightSetSpeed, null);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    var batteryVoltage = RobotController.getBatteryVoltage();
    if (Math.max(Math.abs(leftVolts), Math.abs(rightVolts)) > batteryVoltage) {
      leftVolts *= batteryVoltage / 12.0;
      rightVolts *= batteryVoltage / 12.0;
    }
    leftLeader.setOutputVoltage(leftVolts);
    rightLeader.setOutputVoltage((driveConstants.getDrivebaseRightSideInverted() ? -1 : 1) * rightVolts);
    //m_drive.feed();
  }

  @Override
  public double getLeftSetSpeed() {
    // TODO Auto-generated method stub
    return leftLeader.getActualOutputPercent();
  }

  @Override
  public double getRightSetSpeed() {
    // TODO Auto-generated method stub
    return rightLeader.getActualOutputPercent();
  }

  public void tankDriveVelocity(double leftVelocity, double rightVelocity) {
    leftLeader.set(ControlMode.Velocity, leftVelocity);
    rightLeader.set(ControlMode.Velocity, rightVelocity);
  }

}
