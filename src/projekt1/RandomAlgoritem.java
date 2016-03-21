/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt1;

import java.util.Random;

/**
 *
 * @author Marko
 */
public class RandomAlgoritem implements Algoritem {  
    Random r;
    
    public RandomAlgoritem(){
         r = new Random();
    }
        
    @Override
    public int naslednjaPoteza() {
        return r.nextInt(5);
    }

    @Override
    public void nasprotnikovaPoteza(int poteza) {
        
    }
    
    @Override
    public void ponastavi(){
        
    }
    
}
