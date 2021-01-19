/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 * Object to hold a course
 */
public class Course {
    //use periodID as an easy comparison
    private String course,professor;
    //string room
    private Room room;
    private int CRN,size,periodID,time;
    private boolean multimedia;
    
    public Course(){
        room = null;
        multimedia = false;
    }

    public Course(int CRN,String course, String professor, int size, boolean multimedia) {
        this.course = course;
        this.professor = professor;
        this.CRN = CRN;
        this.size = size;
        this.multimedia = multimedia;
        room = null;
    }


    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }


    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
    

    public int getCRN() {
        return CRN;
    }

    public void setCRN(int CRN) {
        this.CRN = CRN;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    

    public int getPeriodID() {
        return periodID;
    }

    public void setPeriodID(int periodID) {
        this.periodID = periodID;
    }

    public boolean isMultimedia() {
        return multimedia;
    }

    public void setMultimedia(boolean multimedia) {
        this.multimedia = multimedia;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
     
    
    //returns false if the room does not fit the course
    //returns true if the class can fit the course
    public boolean compareRoom(Room room){

        //check for media device
        if(isMultimedia()&&!room.isMultimedia()){
            return false;
        }
        //check for room size
        else if(size>room.getSize()){
            return false;
        }
        
        //setFound(true);
        //room.setOccupied(true);
        return true;
    }
    
    //compare professor names
    public boolean compareCourse(Course otherCourse){
        if(professor.equals(otherCourse.getProfessor())){
            return false;
        }
        return true;
    }
    
    
    
    public void disp(){
      System.out.format("%5s %10s %17s %12s %6s", 
                CRN,course,professor,room.getRoomID(),time);
      System.out.println("");
    }
    
}
