/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// SUBSYSTEM IMPORTS //
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Drivetrain;
// ROBOT MODES (COMMAND GROUP) IMPORTS //
import frc.robot.robotModes.Teleop;
import frc.robot.robotModes.Auto;
import frc.robot.RobotMap;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  
  public static Encoder EncoderRight = new Encoder(2, 3, false, EncodingType.k4X);
  public static Encoder EncoderLeft = new Encoder(0, 1, true, EncodingType.k4X);
  private final ADXRS450_Gyro Gyro = new ADXRS450_Gyro(); 

  public static Drivetrain drivetrain;
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;
  public DifferentialDrive chassis;

// DECLARE ROBOT MODES (COMMAND GROUPS) //

  Command teleop;
  Command auto;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {


    m_oi = new OI();

    drivetrain = new Drivetrain();
		drivetrain.newTankDrive();
    //chassis = new DifferentialDrive(driveTrainSpeedControllerGroup1,driveTrainSpeedControllerGroup2);

    m_chooser.addDefault("Default Auto", new ExampleCommand());
    // chooser.addObject("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    


    SmartDashboard.putNumber("Angle", Gyro.getAngle());
		SmartDashboard.putNumber("Right Distance", EncoderRight.getDistance());
		SmartDashboard.putNumber("Right Count", EncoderRight.get());
		SmartDashboard.putNumber("Left Distance", EncoderLeft.getDistance());
		SmartDashboard.putNumber("Left Count", EncoderRight.get());
     EncoderRight.setDistancePerPulse((Math.PI * 3.25) / 256);
     EncoderRight.setPIDSourceType(PIDSourceType.kRate);
     EncoderLeft.setDistancePerPulse((Math.PI * 3.25) / 256);
     EncoderLeft.setPIDSourceType(PIDSourceType.kRate);
    int countRight = EncoderRight.get();
		double rawRight = EncoderRight.getRaw();
		double distanceRight = EncoderRight.getDistance();
		double rateRight = EncoderRight.getRate();
		boolean directionRight = EncoderRight.getDirection();
		boolean stoppedRight = EncoderRight.getStopped();
		int countLeft = EncoderLeft.get();
		double rawleft = EncoderLeft.getRaw();
		double distanceLeft = EncoderLeft.getDistance();
		double rateLeft = EncoderLeft.getRate();
		boolean directionLeft = EncoderLeft.getDirection();
    boolean stoppedLeft = EncoderLeft.getStopped();
    
    //GYRO//
    double currentAngle = Gyro.getAngle();

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
      // STOP AUTONOMOUS COMMAND GROUP //

    if (auto != null)
    {
      auto.cancel();
    }

    // START TELEOP COMMAND GROUP //

    if (teleop != null)
    {
      teleop.start();
    }
  }

      /**
       * This function is called periodically during operator control.
       */
      @Override
      public void teleopPeriodic() {
        Scheduler.getInstance().run();
      }


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
