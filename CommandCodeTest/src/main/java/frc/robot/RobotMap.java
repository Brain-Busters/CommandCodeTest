/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // private DifferentialDrive m_myRobot;
  
// JOYSTICK / GAMEPAD CONTROLLER USB PORTS //

  public static final int leftStickPort = 0;
  public static final int rightStickPort = 1;
  
  // TANK CONFIG //

  public static SpeedController driveTrainSpeedController1;
  public static SpeedController driveTrainSpeedController2;
  public static SpeedControllerGroup driveTrainSpeedControllerGroup1;
  public static SpeedController driveTrainSpeedController3;
  public static SpeedController driveTrainSpeedController4;
  public static SpeedControllerGroup driveTrainSpeedControllerGroup2;
  
  //Encoders//
  private final ADXRS450_Gyro Gyro = new ADXRS450_Gyro(); 

  
  
  
  
  public static void init() {

  driveTrainSpeedController1 = new PWMVictorSPX(0);
    driveTrainSpeedController1.setInverted(false);
    driveTrainSpeedController2 = new PWMVictorSPX(1);
    driveTrainSpeedController2.setInverted(false);
    driveTrainSpeedControllerGroup1 = new SpeedControllerGroup(driveTrainSpeedController1, driveTrainSpeedController2  );
        
    driveTrainSpeedController3 = new PWMVictorSPX(2);
    driveTrainSpeedController3.setInverted(false);
    driveTrainSpeedController4 = new PWMVictorSPX(3);
    driveTrainSpeedController4.setInverted(false);
    driveTrainSpeedControllerGroup2 = new SpeedControllerGroup(driveTrainSpeedController3, driveTrainSpeedController4  );
   
     
    
    
  }

  

}