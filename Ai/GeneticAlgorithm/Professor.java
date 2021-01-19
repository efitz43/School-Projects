
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class Professor {
    private Map<Integer,String> profClasses;
    private String name;
    public Professor(String name){
        this.name = name;
        profClasses = new HashMap<>();
    }
    public void add(String course,int time){
        profClasses.put(time,course);
    }
    
    public void remove(int time){
        profClasses.remove(time);
    }
    
    public int size(){
        return profClasses.size();
    }
    
    public boolean checkTime(int time){
        return profClasses.containsKey(time);
    }
    
    public boolean checkTime(Room room){
        return profClasses.containsKey(room.getTime());
    }
    
    //return a postive value to be taken off the fitness
    public int getScore(){
        int fitness = 0;
        Set<Integer> timeSet = profClasses.keySet();
        ArrayList<Integer> sortedList = new ArrayList();
        //Probably not going to have a class before 7am or after 7pm
        //quick fix
        //If time start in 24 format
        for(int time : timeSet){
            if(time>=1 && time<7){
                sortedList.add(time+12);
            }else{
                sortedList.add(time);
            }
        }
        Collections.sort(sortedList);
        
        int checkRep = 1;
        for(int i = 0; i<sortedList.size()-1;i++){
            //make sure when checking map you keep in account of the added 12
            //Subtract 15 points for each delay of 3 or more hours between courses.
            
            int v1 = sortedList.get(i+1);
            int v2 = sortedList.get(i);
            int v1Index,v2Index;
            if(v1-v2>=3){
                fitness+=15;
            }
            
            /*
            Subtract 10 points for each instance a professor has 3 or more courses in a row.  
            ( Three courses in a row would subtract 10 points.   
            Four courses in row would subtract 20 points.)
            */
            if(v1-v2==1){
                checkRep++;
            }else{
                checkRep = 1;
            }
            if(checkRep>=3){
                fitness+=10;
            }
            
            /*
            Add 5 points for each instance that a room is duplicated past the first room for a professor
            */
            if(v1>12){
                v1Index= v1-12;
            }else{
                v1Index = v1;
            }
            if(v2>12){
                v2Index= v2-12;
            }else{
                v2Index = v2;
            }
            if(profClasses.get(v1Index).equals(profClasses.get(v2Index))){
                fitness-=5;
            }
        }
     
        
        return fitness;
    }
}
