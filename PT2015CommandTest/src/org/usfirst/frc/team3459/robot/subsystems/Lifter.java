package org.usfirst.frc.team3459.robot.subsystems;

import org.usfirst.frc.team3459.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lifter extends Subsystem{

	Victor motor;
	DigitalInput switch_top;
	DigitalInput switch_bottom;
	
	public Lifter() {
		motor = new Victor(Constants.PWM_LIFT);
		switch_top = new DigitalInput(Constants.IO_LIFTER_TOP);
		switch_bottom = new DigitalInput(Constants.IO_LIFTER_BOTTOM);
	}

	@Override
	protected void initDefaultCommand() {}
	
	public void setUp() {
		motor.set(0.7);
	}
	
	public void setDown() {
		motor.set(-0.7);
	}
	
	public void setStop() {
		motor.set(0);
	}
	
	public boolean atTop() {
		return switch_top.get();
	}
	
	public boolean atBottom() {
		return switch_bottom.get();
	}
}
