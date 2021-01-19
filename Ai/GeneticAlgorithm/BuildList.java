
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class BuildList {
    private String courseFile;
    private String roomFile;
    private String timeFile;
    private ArrayList<Course> courseList = new ArrayList();
    private ArrayList<Room> roomList = new ArrayList();
    private ArrayList<Time> timeList = new ArrayList();

    public BuildList(String courseFile, String roomFile, String timeFile) {
        this.courseFile = courseFile;
        this.roomFile = roomFile;
        this.timeFile = timeFile;
        BuildCourseList();       
        BuildTimeList();
        BuildRoomList();
    }
    
    /*
    Reads in order
    CRN	Course	Professor	Size	Need Multimedia
    1	cs456	Bilitski	20	x
    */
    private void BuildCourseList(){
        try{
            File openFile = new File(courseFile);
            Scanner readFile = new Scanner(openFile);
            String line;
            String [] splitLine;
            int CRN,size;
            boolean multi;
            while(readFile.hasNext()){
                line = readFile.nextLine();
                splitLine = line.split(",");
                CRN = Integer.parseInt(splitLine[0]);
                size = Integer.parseInt(splitLine[3]);
                if(splitLine[4].equals("x"))
                    multi = true;
                else{
                    multi = false;
                }
                Course course = new Course(CRN,splitLine[1],splitLine[2],size,multi);
                courseList.add(course);
            }
            
            
        }catch(IOException e){
            System.out.println("Course list issue");
            exit(1);
        }
    }
    
    /*
    Reads in order
    Room ID	Size	Multimedia
    BL134	30	X
    */
    private void BuildRoomList(){
        try{
            File openFile = new File(roomFile);
            Scanner readFile = new Scanner(openFile);
            String line;
            String [] splitLine;
            int size;
            boolean multi = false;
            while(readFile.hasNext()){
                line = readFile.nextLine();
                splitLine = line.split(",");
                size = Integer.parseInt(splitLine[1]);
                if(splitLine[2].equals("X"))
                    multi = true;
                for(Time time:timeList){
                    Room room = new Room(splitLine[0],size,multi,time.getTime());
                    roomList.add(room);
                }
            }
            
            
        }catch(IOException e){
            System.out.println("Room list issue");
            exit(2);
        }
    }
    /*
    Reads in order
    Period ID	Days	Time
    2           M W F	9
    */
    private void BuildTimeList(){
        try{
            File openFile = new File(timeFile);
            Scanner readFile = new Scanner(openFile);
            String line;
            String [] splitLine;
            int periodID,time;
            boolean multi = false;
            while(readFile.hasNext()){
                line = readFile.nextLine();
                splitLine = line.split(",");
                periodID = Integer.parseInt(splitLine[0]);
                time = Integer.parseInt(splitLine[2]);
                Time classTime = new Time(periodID,time);
                timeList.add(classTime);
            }
            
            
        }catch(IOException e){
            System.out.println("Time list issue");
            exit(3);
        }
    }
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    public ArrayList<Room> getRoomList() {
        return roomList;
    }

    public ArrayList<Time> getTimeList() {
        return timeList;
    }
    
    
}
