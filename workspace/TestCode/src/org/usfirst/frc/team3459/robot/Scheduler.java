package org.usfirst.frc.team3459.robot;

import java.util.List;
import java.util.ListIterator;

public class Scheduler {
    List<Event> schedule;
    ListIterator<Event> scheduleIt;

    Event temp;

    public Scheduler() {}
    
    public void addEvent(int type, long startTime, long endTime, Runnable startF, Runnable endF) {
        if(type == 1 || type ==2)
            schedule.add(new Event(type, startTime, endTime, startF, endF));
    }
    
    public void addEvent(int type, long startTime, long endTime, Runnable startF) {
        if(type == 3)
            schedule.add(new Event(type, startTime, endTime, startF));
    }
    
    public void addEvent(int type, long startTime, Runnable startF) {
        if(type == 4)
            schedule.add(new Event(type, startTime, startF));
    }
    
    public void addEvent(Event event) {
    	schedule.add(event);
    }
    
    public void update(long currentTime) {
        scheduleIt = schedule.listIterator();
        while(scheduleIt.hasNext()) {
            temp = scheduleIt.next();
            if(temp != null) {
            	if(!temp.expired)
            		temp.update(currentTime);
            	else 
            		scheduleIt.remove();
            } else
            	scheduleIt.remove();
        }
    }
}