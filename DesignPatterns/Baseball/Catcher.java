/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eddie
 */
public class Catcher extends Player{
    public Catcher(){
        SetThrowBehavior(new ToPitcher());
        SetCatchBehavior(new CatcherMitt());
        SetBatBehavior(new BatWithNothing());
    }
}
