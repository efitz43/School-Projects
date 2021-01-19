
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class GeneticAlgorithm {
    
    private ArrayList<Schedule> schedule,newSchedule;
    private double Pc, Pm;
    private Random r;
    private Schedule mostFit;
    

    public GeneticAlgorithm(ArrayList<Schedule> schedule,double Pc, double Pm) {
        this.schedule = schedule;
        newSchedule = new ArrayList();
        this.Pc = Pc;
        this.Pm = Pm;
        mostFit = null;
        r = new Random();
    }

    public GeneticAlgorithm(ArrayList<Schedule> schedule, double Pc, double Pm, Schedule mostFit) {
        this.schedule = schedule;
        newSchedule = new ArrayList();
        this.Pc = Pc;
        this.Pm = Pm;
        this.mostFit = mostFit;
        r = new Random();
    }
    
    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Schedule getMostFit() {
        return mostFit;
    }

    public void setMostFit(Schedule mostFit) {
        this.mostFit = mostFit;
    }
    
    
    public ArrayList<Schedule> populate(){
        boolean mutate, cross;
        double randomValue;
        int randLoc,size;
        for(int i = 0; i<schedule.size();i++){
            mutate = false;
            cross = false;
            //get a random schedule
            size = schedule.size()-1;
            randLoc = r.nextInt(size);
            Schedule parent1 = schedule.get(randLoc);
            randLoc = r.nextInt(size);
            Schedule parent2 = schedule.get(randLoc);
            Schedule child = null;
            randomValue = r.nextDouble();
            //cross Pc
            if(randomValue>=1-Pc){
                child = CrossOver(parent1,parent2);
                cross = true;
            }
            
            //mutate Pm
            randomValue = r.nextDouble();
            if(randomValue>=1-Pm){
                //check if the child is empty
                if(child==null){
                    child = Selection(parent1,parent2);
                }
                child = Mutation(child);
                mutate = true;
            }
            
            //selection
            if(!mutate&&!cross){
                child = Selection(parent1,parent2);
            }
            newSchedule.add(child);
            if(mostFit==null){
                mostFit = child;
            }else{
                mostFit = child.compare(mostFit);
            }
        }
        return newSchedule;
    }
    
    private Schedule Mutation(Schedule child){
        return child.mutate();
    }
    
    private Schedule CrossOver(Schedule parent1,Schedule parent2){
        return parent1.cross(parent2);
    }
    
    private Schedule Selection(Schedule parent1,Schedule parent2){
        return parent1.select(parent2);
    }
}
