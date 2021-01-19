/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 * Object to hold a room
 */
public class Room {
    private String roomID;
    private int size,time;
    private boolean multimedia,occupied;
    
    public Room(){
        multimedia = false;
        occupied = false;
    }

    public Room(String roomID, int size, boolean multimedia, int time) {
        this.roomID = roomID;
        this.size = size;
        this.multimedia = multimedia;
        this.time = time;
        occupied = false;
    }
    
    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isMultimedia() {
        return multimedia;
    }

    public void setMultimedia(boolean multimedia) {
        this.multimedia = multimedia;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
    
    @Override
    public boolean equals(Object o){

        if(o instanceof Room){

            Room p = (Room)o;
            if((p.time==this.time)&&(p.roomID.equals(this.roomID)))
            {
                return true;
            }
            else{ 
                return false;
            }
        }

        return false;
    }
}
