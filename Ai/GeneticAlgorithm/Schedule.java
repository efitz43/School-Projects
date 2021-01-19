
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 * Build the schedule with a base list of rooms and courses
 */
public class Schedule {
    //keep track of professors
    private Map<Integer,ArrayList<Course>> testDict = new HashMap<Integer,ArrayList<Course>>() {};
    //takes objects from course list(remove them)
    //put them into schedule
    private ArrayList<Course> courseList,schedule = new ArrayList();
    //private ArrayList<Course> courseList;
    private ArrayList<Room> roomList,roomsLeft;
    //private ArrayList<Professor> professors = new ArrayList();
    private Map<String,Professor> professors = new HashMap<String,Professor>() {};
    private Set<String> professorsName = new HashSet();
    private ArrayList<Time> timeList;
    private int fitness;
    private Random r;
    public Schedule(ArrayList<Course> courseList, ArrayList<Room> roomList,  ArrayList<Time> timeList) {
        this.courseList = courseList;
        this.roomList = roomList;
        this.timeList = timeList;
        r = new Random();
        schedule = new ArrayList();
        fitness = 1000;
        for(Time time:timeList)
           testDict.put(time.getTime(),new ArrayList());
        for(Course course : courseList){
            professorsName.add(course.getProfessor());
        }
        for(String name : professorsName){
            professors.put(name,new Professor(name));
        }
        
        
    }
    
    public Schedule(Schedule that){
        this(that.courseList,that.roomList,that.timeList);
    }
    
    public void setFitness(int fitness){
        this.fitness = fitness;
    }
    public int getFitness(){
        return fitness;
    }

    public ArrayList<Course> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Course> schedule) {
        this.schedule = schedule;
    }
    
    
    public int size(){
        return schedule.size();
    }

    public ArrayList<Room> getRoomsLeft() {
        return roomsLeft;
    }

    public void setRoomsLeft(ArrayList<Room> roomsLeft) {
        this.roomsLeft = roomsLeft;
    }
    
    
    
    public boolean BuildSchedule(){
        int randRoom;
        Room currentRoom;
        int currentTime;
        ArrayList<Room> possibleRooms = new ArrayList();
        ArrayList<Room> checkRoom = (ArrayList<Room>) roomList.clone();
        ArrayList<Course> checkCourse = (ArrayList<Course>) courseList.clone();
        //ArrayList<Room> checkRoom = roomList;
        //have a set of professors every period iteration remove at end
        for(Course currentCourse : checkCourse){
            Professor prof = professors.get(currentCourse.getProfessor());
            //Get a room that can actually be used
            //Basically runs forever near the end without this
            for(Room room: checkRoom){
                if(currentCourse.compareRoom(room)&&!prof.checkTime(room)){
                    //check teachers time
                    possibleRooms.add(room);
                }
            }
            
            //Get a random room
            //Kind of cheezy but a lot easier then trying to sort
            if(possibleRooms.size()<=0){
                return false;
            }
            if(possibleRooms.size()==1){
                randRoom = 0;
            }else{
                randRoom = r.nextInt(possibleRooms.size()-1);
            }
            currentRoom = possibleRooms.get(randRoom);
            currentTime = currentRoom.getTime();
         
            //get the course add it to the schedule, dict to sort and prof dict
            currentCourse.setTime(currentTime);
            //currentCourse.setRoom(currentRoom.getRoomID());
            currentCourse.setRoom(currentRoom);
            testDict.get(currentTime).add(currentCourse);
            schedule.add(currentCourse);
            //professors.get(currentCourse.getProfessor()).add(currentCourse.getRoom(),currentCourse.getTime());
            professors.get(currentCourse.getProfessor()).add(currentCourse.getRoom().getRoomID(),currentCourse.getTime());
           
            //First soft rule applies to size
            fitness = fitness - (currentRoom.getSize() - currentCourse.getSize());
            

            //remove rooms that are not avaliable
            checkRoom.remove(currentRoom);
            
           //clears room for next random
            possibleRooms.clear();
        }
        roomsLeft = checkRoom;
        calcFitness();
        return true;
    }

    
    
    public void calcFitness(){
        /*
        Subtract 1 point for each seat available that is not taken. (did already)
        Subtract 15 points for each delay of 3 or more hours between courses.
        Subtract 10 points for each instance a professor has 3 or more courses in a row.
        Add 5 points for each instance that a room is duplicated past the first room for a professor:  (Eg. Black214, Black214 is a 5pt bonus.     Kreb206, Kreb206, Kreb206 is a 10pt bonus.)
        */
        Collection<Professor> profs = professors.values();
        Iterator itr = profs.iterator();
        while(itr.hasNext()){
            Professor element = (Professor) itr.next();
            fitness = fitness - element.getScore();
        }
    }
    
    public Schedule select(Schedule that){
        Schedule pref;
        if(this.fitness>that.fitness){
            pref = new Schedule(this);
            pref.setSchedule((ArrayList<Course>) schedule.clone());
            pref.setRoomsLeft((ArrayList<Room>) roomsLeft.clone());
            pref.setFitness(fitness);
        }else{
            pref = new Schedule(that);
            pref.setSchedule((ArrayList<Course>) that.schedule.clone());
            pref.setRoomsLeft((ArrayList<Room>) that.roomsLeft.clone());
            pref.setFitness(that.fitness);
        }
        return pref;
    }
    
    public Schedule mutate(){
        //intialize
        Schedule mutagen = new Schedule(this);
        mutagen.setSchedule((ArrayList<Course>) schedule.clone());
        mutagen.setRoomsLeft((ArrayList<Room>) roomsLeft.clone());
        //max 3 min 1 mutations
        int mutations = r.nextInt(2)+1;
        //mutate and check for errors
        mutagen.cleanMutate(mutations);
        return mutagen;
    }
    
    public Schedule cross(Schedule that){
        //get a nice break
        int randBreak = r.nextInt(this.size()-6)+3;
        //intialize
        ArrayList<Course> parent1 = (ArrayList<Course>) this.getSchedule().clone();
        ArrayList<Course> parent2 = (ArrayList<Course>) that.getSchedule().clone();
        ArrayList<Course> child = new ArrayList();
        //split up
        List<Course> parent1List = parent1.subList(0, randBreak+1);
        List<Course> parent2List = parent2.subList(randBreak+1, this.size());
        //add back
        for(Course parent : parent1List){
            child.add(parent);
        }
        for(Course parent : parent2List){
            child.add(parent);
        }
        //check for errors
        Schedule childSchedule = new Schedule(that);
        childSchedule.setSchedule(child);
        childSchedule.cleanCross();
        
        return childSchedule;
    }
    
    private void cleanCross(){
        ArrayList<Room> possibleRooms = new ArrayList();
        ArrayList<Room> checkRoom = (ArrayList<Room>) roomList.clone();
        for(Course currentCourse:schedule){
            Professor prof = professors.get(currentCourse.getProfessor());
            //Get rid of used rooms and check to clean
            //checks if the class is avalabile and the time
            if(checkRoom.contains(currentCourse.getRoom())&&!prof.checkTime(currentCourse.getRoom())){
                //checkRoom.remove(currentCourse.getRoom());
            }
            // it is taken find a replacement
            else{
                //plop the first replacement in
                for(Room room: checkRoom){
                    if(currentCourse.compareRoom(room)&&!prof.checkTime(room)){
                        currentCourse.setTime(room.getTime());
                        currentCourse.setRoom(room);
                        break;
                    }
                }
            }
            fitness = fitness - (currentCourse.getRoom().getSize() - currentCourse.getSize());
            //remove rooms that are not avaliable
            checkRoom.remove(currentCourse.getRoom());
            professors.get(currentCourse.getProfessor()).add(currentCourse.getRoom().getRoomID(),currentCourse.getTime());
        }
        roomsLeft = checkRoom;
        calcFitness();
    }
    
    private void cleanMutate(int mutations){
        int count = 0;
        int randLoc;
        Course currentCourse;
        while(count!=mutations){
            randLoc = r.nextInt(schedule.size()-1);
            currentCourse = schedule.get(randLoc);
            for(Room room: roomsLeft){
                    if(currentCourse.compareRoom(room)){
                        currentCourse.setTime(room.getTime());
                        currentCourse.setRoom(room);
                        count++;
                        break;
                    }
            }
//            System.out.println("============================================");
//            System.out.println("Mutation at: " + currentCourse.getCRN());
//            currentCourse.disp();
//            System.out.println();
        }
        //To be extra carefull and to get fitness
        cleanCross();
    }
    
    public Schedule compare(Schedule that){
        if(this == null)
            return that;
        else if(that == null)
            return that;
        else{
            return this.select(that);
        }
    }
    
    public void Disp(){
        System.out.println("------------------------------------------------------------------------------------");
        System.out.format("%5s %10s %17s %12s %6s", 
                "CRN","Course","Professor","Room ID","Time");
        System.out.println("");
        System.out.println("------------------------------------------------------------------------------------");
        
        for(Course course:schedule){
            course.disp();
        }
        System.out.println("");
        System.out.println("Fitness: " + fitness);
        System.out.println("------------------------------------------------------------------------------------");
        
    }
}
