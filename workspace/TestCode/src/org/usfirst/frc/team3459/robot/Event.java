package org.usfirst.frc.team3459.robot;

public class Event {
    int type;
    boolean inTime = false, expired = false, firstRun = true;
    long startTime, endTime;
    Runnable startF, endF;
    
    
    /*Type index
        1 - runs the startF once at the beginning and the endF once at the end
        2 - runs the startF throughout the event duration and the endF once at the end
        3 - runs the startF once at the beginning
        4 - runs the startF throughout the event
     */
     
    //Constructor for types 1 and 2
    public Event(int Type, long StartTime, long EndTime, Runnable StartF, Runnable EndF) {
        if(Type == 3 || Type == 4) expired = true;
        type = Type;
        startTime = StartTime;
        endTime = EndTime;
        startF = StartF;
        endF = EndF;
    }   
    
    //Constructor for type 3
    public Event(int Type, long StartTime, Runnable StartF) {
        if(Type == 1 || Type == 2 || Type == 4) expired = true;
        type = Type;
        startTime = StartTime;
        endTime = 0;
        startF = StartF;
        endF = null;
    } 
    
    //Constructor for type 4
    public Event(int Type, long StartTime, long EndTime, Runnable StartF) {
        if(Type == 1 || Type == 2 || Type == 3) expired = true;
        type = Type;
        startTime = StartTime;
        endTime = EndTime;
        startF = StartF;
        endF = null;
    } 
    
    public void update(long currentTime) {
        switch(type) {
            case 1: update1(currentTime);
                break;
            case 2: update2(currentTime);
                break;
            case 3: update3(currentTime);
                break;
            case 4: update4(currentTime);
                break;
                
        }
    }
    
    public void update1(long currentTime) {
        if(currentTime > startTime) {
            if(currentTime < endTime) 
                startF.run();
            else {
                endF.run();
                expired = true;
            }
        }
    }
    
    public void update2(long currentTime) {
        if(currentTime > startTime) {
            if(currentTime < endTime) { 
                if(firstRun) {
                    startF.run();
                    firstRun = false;
                }
            } else {
                endF.run();
                expired = true;
            }
        }
    }
    
    public void update3(long currentTime) {
        if(currentTime > startTime) {
            startF.run();
            expired = true;
        }
    }
    
    public void update4(long currentTime) {
        if(currentTime > startTime) {
            if(currentTime < endTime) 
                startF.run();
            else
                expired = true;
        }
    }
}