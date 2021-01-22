/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class Fielder extends Player{
    public Fielder(){
        SetThrowBehavior(new AnotherFielder());
        SetCatchBehavior(new RegularGlove());
        SetBatBehavior(new BatWithNothing());
    }
}
