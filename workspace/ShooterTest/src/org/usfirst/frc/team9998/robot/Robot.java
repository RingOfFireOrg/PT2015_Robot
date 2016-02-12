
package org.usfirst.frc.team9998.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class Robot extends SampleRobot {
    public static final int SHOOTER_M1 = 10;
    public static final int SHOOTER_M2 = 11;
    public static final int SHOOTER_S1 = 0;
    
    Shooter shooter;
    Joystick stick;
    JoystickButton increaseVButton;
    JoystickButton decreaseVButton;
    JoystickButton fireButton;
    JoystickButton disableButton;
    JoystickButton enableButton;
    
    public Robot() {
    	shooter = new Shooter(SHOOTER_M1,SHOOTER_M2,SHOOTER_S1);
        stick = new Joystick(1);
        
        increaseVButton = new JoystickButton(stick,1);
        decreaseVButton = new JoystickButton(stick,2);
        fireButton = new JoystickButton(stick,3);
        disableButton = new JoystickButton(stick,4);
        enableButton = new JoystickButton(stick,6);
        
    }

    public void autonomous() {}

    public void operatorControl() {
    	while (isOperatorControl() && isEnabled()) {
    		updateShooter();
           
        	Timer.delay(0.005);		// wait for a motor update time
        }
    }

    public void test() {
    }
    
    public void updateShooter() {
    	//Voltage Control
		if(increaseVButton.get()) {
			if (decreaseVButton.get()) {
				System.out.println("Shooter: decreaseV/increaseV simultaneous press");
    	   	} else {
    		   	shooter.increaseVoltage();
    	   	}
		} else if (decreaseVButton.get()) {
    	   	shooter.decreaseVoltage();
       	}
		
		//State Control
		if(enableButton.get()) {
			if (disableButton.get()) {
				System.out.println("Shooter: disable/enable simultaneous press");
    	   	} else {
    		   	shooter.setShoot();
    	   	}
		} else if (disableButton.get()) {
    	   	shooter.setDisable();
       	}
		
		//Firing
		if(fireButton.get())
			shooter.fire();
		
		shooter.update();
    }
}