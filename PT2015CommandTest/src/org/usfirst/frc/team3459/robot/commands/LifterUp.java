package org.usfirst.frc.team3459.robot.commands;

import org.usfirst.frc.team3459.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

public class LifterUp extends Command {

	private final Lifter myLifter;
	
	public LifterUp(Lifter lift) {
		myLifter = lift;
		requires(lift);
	}
	
	@Override
	protected void initialize() {
		setTimeout(10);
	}

	@Override
	protected void execute() {
		myLifter.setUp();
	}

	@Override
	protected boolean isFinished() {
		return myLifter.atTop() || isTimedOut();
	}

	@Override
	protected void end() {
		myLifter.setStop();
	}

	@Override
	protected void interrupted() {
		end();
		
	}
}