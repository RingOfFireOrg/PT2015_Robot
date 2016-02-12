package org.usfirst.frc.team3459.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class DoublePiston {
    boolean desiredState = false, currentState = false;
    long lockUntil = 0;
    Solenoid outS1, inS1, outS2, inS2;
    
    public DoublePiston(int channel1, int channel2, int channel3, int channel4) {
    	outS1 = new Solenoid(channel1);
    	inS1 = new Solenoid(channel2);
    	outS2 = new Solenoid(channel3);
    	inS2 = new Solenoid(channel4);
    }
    
    public void update() {
        if(desiredState != currentState) {
            if(desiredState) {
                //extend code
            	outS1.set(true);
            	inS1.set(false);
            	outS2.set(true);
            	inS2.set(false);
            } else {
                //contract code
            	outS1.set(false);
            	inS1.set(true);
            	outS2.set(false);
            	inS2.set(true);
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
