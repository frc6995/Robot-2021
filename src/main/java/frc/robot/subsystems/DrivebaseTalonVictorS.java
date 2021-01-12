/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.wrappers.motorcontrollers.NomadNoneMotor;
import frc.robot.wrappers.motorcontrollers.NomadTalonSRX;
import frc.robot.wrappers.motorcontrollers.NomadVictorSPX;
import frc.robot.constants.AutoConstants;
import frc.robot.constants.AutoConversionFactors;
import frc.robot.constants.DriveConstants;
import frc.robot.utility.drivebase.DrivebaseWheelPercentages;

public class DrivebaseTalonVictorS extends DrivebaseS<NomadTalonSRX<NomadNoneMotor>, NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>>> {
  public NomadTalonSRX<NomadNoneMotor> leftLeader;
  public NomadTalonSRX<NomadNoneMotor> rightLeader;
  public NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>> leftFollower;
  public NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>> rightFollower;
  private DriveConstants driveConstants;
  private AutoConstants autoConstants;
  /**
   * The configuration for the Talons.
   */
  private TalonSRXConfiguration talonConfig = new TalonSRXConfiguration();
  private AHRS gyro = new AHRS(Port.kMXP);
  private DifferentialDrive differentialDrive;
  private final DifferentialDriveOdometry differentialDriveOdometry;


  /**
   * Creates a new DrivebaseS.
   */
  public DrivebaseTalonVictorS(DriveConstants driveConstants, AutoConstants autoConstants) {
    this(
      new NomadTalonSRX<NomadNoneMotor>(driveConstants.getCanIDLeftDriveMaster()), 
      new NomadTalonSRX<NomadNoneMotor>(driveConstants.getCanIDRightDriveMaster()), 
      driveConstants, autoConstants);
  }

  public DrivebaseTalonVictorS(NomadTalonSRX<NomadNoneMotor> leftTalonSRX, NomadTalonSRX<NomadNoneMotor> rightTalonSRX, DriveConstants driveConstants, AutoConstants autoConstants){
    super(driveConstants, autoConstants);
    leftLeader = leftTalonSRX;
    rightLeader = rightTalonSRX;
    instantiateConfigureMotors(driveConstants, leftLeader, rightLeader, leftFollower, rightFollower);
    differentialDrive = new DifferentialDrive(leftLeader, rightLeader);
    differentialDriveOdometry = new DifferentialDriveOdometry( new Rotation2d(Math.toRadians(gyro.getYaw())));
  }

  public DrivebaseTalonVictorS(NomadTalonSRX<NomadNoneMotor> leftTalonSRX, NomadTalonSRX<NomadNoneMotor> rightTalonSRX, NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>> leftVictorSPX,
NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>> rightVictorSPX, DriveConstants driveConstants, AutoConstants autoConstants){
    super(driveConstants, autoConstants);
    leftLeader = leftTalonSRX;
    rightLeader = rightTalonSRX;
    leftFollower = leftVictorSPX;
    rightFollower = rightVictorSPX;
    instantiateConfigureMotors(driveConstants, leftLeader, rightLeader, leftFollower, rightFollower); //Does not instantiate in this case, just configures.
    differentialDriveOdometry = new DifferentialDriveOdometry( new Rotation2d(Math.toRadians(gyro.getYaw())));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run    
  }

  public Pose2d getPose() {
    return differentialDriveOdometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds(){
    return new DifferentialDriveWheelSpeeds(
    AutoConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajectoryUnits(getLeftVelocity(),
            driveConstants.getkWheelDiameter(), true, (int) driveConstants.getEncoderCountsPerEncoderRevolution()),
    AutoConversionFactors.convertTalonSRXNativeUnitsToWPILibTrajectoryUnits(getRightVelocity(),
            driveConstants.getkWheelDiameter(), true, (int) driveConstants.getEncoderCountsPerEncoderRevolution()));

  }

  public double getRightVelocity() {
    return leftLeader.getSelectedSensorVelocity();
  }

  public double getLeftVelocity() {
    return rightLeader.getSelectedSensorVelocity();
  }

  /**
   * Basic arcade drive. Converts joystick inputs into percent outputs for each
   * side of the drivebase.
   * 
   * @param fwdBack   The joystick input for the forward/back axis. It is assumed
   *                  that a value of 1 represents forward, and -1 represents
   *                  backward.
   * @param leftRight The joystick input for the left/right axis. It is assumed
   *                  that 1 represents left point turn, and -1 represents right
   *                  point turn.
   */
  public DrivebaseWheelPercentages arcadeDriveController(double fwdBack, double leftRight) {
    double leftPercent = fwdBack + leftRight; 
    double rightPercent = fwdBack - leftRight;
    return new DrivebaseWheelPercentages().setLeftPercentage(leftPercent).setRightPercentage(rightPercent);
  }

  public void drivePercentages(DrivebaseWheelPercentages percentages){
    leftLeader.set(ControlMode.PercentOutput, percentages.getLeftPercentage());
    rightLeader.set(ControlMode.PercentOutput, percentages.getRightPercentage());
  }

  @Override
  public void instantiateConfigureMotors(DriveConstants driveConstants, NomadTalonSRX<NomadNoneMotor> leftLeader,
      NomadTalonSRX<NomadNoneMotor> rightLeader, NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>> leftFollower,
      NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>> rightFollower) {
      //Create motors not yet instantiated (ex. by constructor parameter)
      if(!(leftLeader == null)) leftLeader = new NomadTalonSRX<NomadNoneMotor>(driveConstants.getCanIDLeftDriveMaster());
      if(!(leftFollower == null)) leftFollower = new NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>>(driveConstants.getCanIDLeftDriveFollower());
      if(!(rightLeader == null)) rightLeader = new NomadTalonSRX<NomadNoneMotor>(driveConstants.getCanIDRightDriveMaster());
      if(!(rightFollower == null)) rightFollower = new NomadVictorSPX<NomadTalonSRX<NomadNoneMotor>>(driveConstants.getCanIDRightDriveFollower());
      // TODO configure motors with drive constants.
      

  }

  @Override
  public double getYaw() {
    
    if(RobotBase.isReal()) {
      return gyro.getYaw();
    } else {
      return 0; // TODO gyro sim equivalent
    }
  }    


}
