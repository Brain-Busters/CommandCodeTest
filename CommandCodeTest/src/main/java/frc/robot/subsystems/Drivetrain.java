/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;


import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Add your docs here.
 */
public class Drivetrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
 
  
  public DifferentialDrive chassis;

	// DEFAULT CONSTRUCTOR //

	public Drivetrain()

	{

	}

	// CREATE TANK DRIVE CHASSIS //

	public void newTankDrive()
	{
    
    final SpeedController driveTrainSpeedController1;
    final SpeedController driveTrainSpeedController2;
    final SpeedControllerGroup driveTrainSpeedControllerGroup1;
    final SpeedController driveTrainSpeedController3;
    final SpeedController driveTrainSpeedController4;
    final SpeedControllerGroup driveTrainSpeedControllerGroup2;
  
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
        
    chassis = new DifferentialDrive(driveTrainSpeedControllerGroup1,driveTrainSpeedControllerGroup2);
  }


	/* =========== TANK DRIVE ===========
	 * throttle ---> move value (Y value)
	 * yaw ---> turn value (X value)
	 * ================================*/

	public void tankDrive(double throttle, double yaw)
	{
      chassis.arcadeDrive(throttle, yaw);
  }
  
  public void stop()
	{
		tankDrive(0, 0);
	}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }
}
