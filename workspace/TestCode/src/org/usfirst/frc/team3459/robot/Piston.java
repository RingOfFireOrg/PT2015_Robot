package org.usfirst.frc.team3459.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Piston{
    boolean desiredState = false, currentState = false;
    long lockUntil = 0;
    Solenoid outS, inS;
    
    public Piston(int channel1, int channel2) {
    	outS = new Solenoid(channel1);
    	inS = new Solenoid(channel2);
    }
    
    public void update() {
        if(desiredState != currentState) {
            if(desiredState) {
                //extend code
            	outS.set(true);
            	inS.set(false);
            } else {
                //contract code
            	outS.set(false);
            	inS.set(true);
            }
            currentState = desiredState;
        }
    }
    
    public void extend() {
        desiredState = true;
        update();
    }
    
    public void contract() {
        desiredState = false;
        update();
    }
    
    //piston EC and CE events are events that include one in and one out cycle of the piston
    //EC - Expand and then contract
    //CE - Contract and then expand
    //if option is selected the times are set as time from the events creation
    public Event pistonECEvent(long startTime, long endTime, boolean option) {
    	Event temp;
    	if(System.currentTimeMillis() < lockUntil) return null;
    	
        if(option) 
            temp = new Event(1, System.currentTimeMillis()+startTime, System.currentTimeMillis()+endTime, 
                                () -> this.extend(), () -> this.contract());
        else
            temp = new Event(1, startTime, endTime, () -> this.extend(), () -> this.contract());
        
        lockUntil = endTime+500;
        return temp;
    }
    
    public Event pistonCEEvent(long startTime, long endTime, boolean option) {
    	Event temp;
    	if(System.currentTimeMillis() < lockUntil) return null;
    	
        if(option)
            temp = new Event(1, System.currentTimeMillis()+startTime, System.currentTimeMillis()+endTime, 
                                () -> this.contract(), () -> this.extend());
        else 
            temp = new Event(1, startTime, endTime, () -> this.contract(), () -> this.extend());
        
        lockUntil = endTime+500;
        return temp;
    }
}
