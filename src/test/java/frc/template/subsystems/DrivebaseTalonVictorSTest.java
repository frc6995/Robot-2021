/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.template.subsystems;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.ctre.phoenix.motorcontrol.ControlMode;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import frc.lib.utility.drivebase.DrivebaseWheelPercentages;
import frc.lib.wrappers.motorcontrollers.NomadTalonSRX;
import frc.template.constants.AutoConstantsDefault;
import frc.template.constants.DriveConstantsDefault;

/**
 * Add your docs here.
 */
public class DrivebaseTalonVictorSTest {
    @SuppressWarnings("unchecked")
    @Mock
    NomadTalonSRX leftTalonSRX = Mockito.mock(NomadTalonSRX.class);
    @SuppressWarnings("unchecked")
    @Mock
    NomadTalonSRX rightTalonSRX = Mockito.mock(NomadTalonSRX.class);
    
    
    DifferentialDrivebaseTalonVictorS drivebaseSTest;
    DriveConstantsDefault testDriveConstants = new DriveConstantsDefault(){
        @Override
        public boolean getGyroReversed() {
            return false;
        }
    };

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        drivebaseSTest = new DifferentialDrivebaseTalonVictorS(leftTalonSRX, 
        rightTalonSRX, 
        testDriveConstants, new AutoConstantsDefault(testDriveConstants));
        
    }
    @Test
    public void arcadeDriveControllerInsideRangeTest(){
        assertEquals(1.0, drivebaseSTest.arcadeDriveController(1, 0).getLeftPercentage(), 0.001);
        assertEquals(1.0, drivebaseSTest.arcadeDriveController(1, 0).getRightPercentage(), 0.001);
    }

    @Test
    public void arcadeDriveControllerOutsideRangeTest(){
        assertEquals(2.0, drivebaseSTest.arcadeDriveController(1, 1).getLeftPercentage(), 0.001); //Value is unclamped intentionally.
        assertEquals(0.0, drivebaseSTest.arcadeDriveController(1, 1).getRightPercentage(), 0.001);
    }

    @Test
    public void drivePercentageTest(){
        drivebaseSTest = new DifferentialDrivebaseTalonVictorS(leftTalonSRX, rightTalonSRX, testDriveConstants, new AutoConstantsDefault(testDriveConstants));
        DrivebaseWheelPercentages testWheelPercentages = new DrivebaseWheelPercentages();
        testWheelPercentages.setLeftPercentage(0.69).setRightPercentage(0.95);
        //doNothing().when(leftTalonSRX).set(Mockito.eq(ControlMode.PercentOutput), anyDouble());
        //doNothing().when(rightTalonSRX).set(Mockito.eq(ControlMode.PercentOutput), anyDouble());
        drivebaseSTest.drivePercentages(testWheelPercentages);
        verify(leftTalonSRX).set(ControlMode.PercentOutput, testWheelPercentages.getLeftPercentage());
        verify(rightTalonSRX).set(ControlMode.PercentOutput, testWheelPercentages.getRightPercentage());
        verifyNoMoreInteractions(leftTalonSRX, rightTalonSRX);
    }


}
