/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class Batter extends Player{
    public Batter(){
        SetThrowBehavior(new NoThrow());
        SetCatchBehavior(new BareHand());
        SetBatBehavior(new Contact());
    }
    
}
