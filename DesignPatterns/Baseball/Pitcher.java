/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class Pitcher extends Player{
    public Pitcher(){
        SetThrowBehavior(new Pitching());
        SetCatchBehavior(new RegularGlove());
        SetBatBehavior(new BatWithNothing());
    }
}
