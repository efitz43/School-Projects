/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public abstract class Player {
    String name = "";
    BatBehavior batBehavior;
    ThrowBehavior throwBehavior;
    CatchBehavior catchBehavior; 
    
    public Player() {
    }

    public void SetName(String name) {
        this.name = name;
    }
    
    public void SetBatBehavior(BatBehavior batBehavior) {
        this.batBehavior = batBehavior;
    }

    public void SetThrowBehavior(ThrowBehavior throwBehavior) {
        this.throwBehavior = throwBehavior;
    }

    public void SetCatchBehavior(CatchBehavior catchBehavior) {
        this.catchBehavior = catchBehavior;
    }
    
    public void Display(){
        System.out.println(name);
    }

    public void PerformCatch() {
            catchBehavior.catching();
    }

    public void PerformThrow() {
            throwBehavior.throwing();
    }
    
    public void PerformBat() {
            batBehavior.batting();
    }

	
}
