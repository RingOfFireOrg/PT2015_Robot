package org.usfirst.frc.team3459.robot;


import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically it 
 * contains the code necessary to operate a robot with tank drive.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    Joystick leftStick;  // set to ID 1 in DriverStation
    Joystick rightStick; // set to ID 2 in DriverStation
    Joystick controlStick;
    Joystick potentiometers;
    
    JoystickButton twist1;
    JoystickButton twist2;
    JoystickButton elevator1;
    JoystickButton elevator2;
    
    RobotDrive myRobot;  // class that handles basic drive operations
    
    Victor twist;
    Jaguar elevator;
    
    double twistS, elevatorS;
    
    public Robot() {
    	leftStick = new Joystick(0);
        rightStick = new Joystick(1);
        controlStick = new Joystick(2);
        potentiometers = new Joystick(3);
   
        twist1 = new JoystickButton(controlStick, 1);
        twist2 = new JoystickButton(controlStick, 2);
        elevator1 = new JoystickButton(controlStick, 3);
        elevator2 = new JoystickButton(controlStick, 4);
        
    	myRobot = new RobotDrive(0, 1);
        myRobot.setExpiration(0.1);
        
        twist = new Victor(0);
d        elevator = new Jaguar(1);
    }

    
    /**
     * Runs the motors with tank steering.
     */
    public void operatorControl() {
        myRobot.setSafetyEnabled(true);
        
        while (isOperatorControl() && isEnabled()) {
        	myRobot.tankDrive(leftStick, rightStick);	//set the tank drive
        	
        	twistS = potentiometers.getY();		//set the twist speed
        	
        	if(twist1.get()) {					//if the forward button is pressed
        		if(!twist2.get()) 					//if the backwards button is not pressed
        			twist.set(twistS);					//set twist forward
        		else								//if the backwards button is pressed
        			twist.set(0);						//set twist off
        	} else {							//if the forward button is not pressed
        		if(twist2.get()) 					//if the backwards button is pressed
        			twist.set(-twistS);					//set twist backwards
        		else								//if the backwards button is not pressed
        			twist.set(0);						//set twist off
        	}
        	
        	elevatorS = potentiometers.getX();
        	
        	if(elevator1.get()) {
        		if(!elevator2.get()) 
        			elevator.set(-elevatorS);
        		else
        			elevator.set(0);
        	} else {
        		if(elevator2.get()) 
        			elevator.set(elevatorS);
        		else
        			elevator.set(0);
        	}
        	
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

}
