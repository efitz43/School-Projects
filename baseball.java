/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class baseball {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pitcher kent = new Pitcher();
        kent.SetName("Kent T");
        kent.Display();
        kent.PerformThrow();
        kent.PerformCatch();
        kent.PerformBat();
        Batter pedro = new Batter();
        pedro.SetName("Pedro A");
        pedro.Display();
        pedro.PerformThrow();
        pedro.PerformCatch();
        pedro.PerformBat();
        Fielder max = new Fielder();
        max.SetName("Max M");
        max.Display();
        max.PerformThrow();
        max.PerformCatch();
        max.PerformBat();
        Catcher chris = new Catcher();
        chris.SetName("Chris S");
        chris.Display();
        chris.PerformThrow();
        chris.PerformCatch();
        chris.PerformBat();
        System.out.println("Kent the pitcher will not bat any more because we have a pinch hitter.");
        kent.SetBatBehavior(new BatWithNothing());
        kent.Display();
        kent.PerformBat();

        PinchHitter bobcat = new PinchHitter();
        bobcat.SetName("Bobcat");
        bobcat.Display();
        bobcat.PerformThrow(); 
        bobcat.PerformCatch();
        bobcat.PerformBat();
    }
    
}
