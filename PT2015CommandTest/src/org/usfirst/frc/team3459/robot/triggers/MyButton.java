package org.usfirst.frc.team3459.robot.triggers;

import org.usfirst.frc.team3459.robot.commands.LifterUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

/**
 * A custom button that is triggered when two buttons on a Joystick are
 * simultaneously pressed.
 */
public class MyButton extends Trigger {
	private Joystick joy;
	private int buttonID;
	
	public MyButton(Joystick joy, int button1) {
		this.joy = joy;
		this.buttonID = button1;
	}	
    
    public boolean get() {
        return joy.getRawButton(buttonID);
    }

	public void whenPressed(LifterUp lifterUp) {
		lifterUp.start();
	}
}
