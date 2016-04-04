/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt1;

import java.util.Random;

/**
 *
 * @author Aljaz
 */
public class AlgoritemPrehodaStanj implements Algoritem {
    
    private int stanje = 0;
    
    Random rnd;

    private double[][] izbraniPrehodi;
    
    // ponavlja isti vrstni red
    private double[][] prehodi1 = {
        { 0, 1, 0, 0, 0 },
        { 0, 0, 1, 0, 0 },
        { 0, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 1 },
        { 1, 0, 0, 0, 0 }
    };
    // "večinoma" se ponavlja
    private double[][] prehodi2 = {
        { 0.05, 0.80, 0.05, 0.05, 0.05 },
        { 0.05, 0.05, 0.80, 0.05, 0.05 },
        { 0.05, 0.05, 0.05, 0.80, 0.05 },
        { 0.05, 0.05, 0.05, 0.05, 0.80 },
        { 0.80, 0.05, 0.05, 0.05, 0.05 }
    };
    // ponavlja isti vrstni red
    private double[][] prehodi3 = {
        { 0, 0, 1, 0, 0 },
        { 0, 0, 0, 1, 0 },
        { 0, 1, 0, 0, 0 },
        { 0, 0, 0, 0, 1 },
        { 1, 0, 0, 0, 0 }
    };
    // vedno isti
    private double[][] prehodi4 = {
        { 1, 0, 0, 0, 0 },
        { 0, 1, 0, 0, 0 },
        { 0, 0, 1, 0, 0 },
        { 0, 0, 0, 1, 0 },
        { 0, 0, 0, 0, 1 }
    };
    // za papirjem je vedno kamen, čene naključno
    private double[][] prehodi5 = {
        { 0.2, 0.2, 0.2, 0.2, 0.2 },
        { 0.2, 0.2, 0.2, 0.2, 0.2 },
        { 1, 0, 0, 0, 0 },
        { 0.2, 0.2, 0.2, 0.2, 0.2 },
        { 0.2, 0.2, 0.2, 0.2, 0.2 }
    };

    private double[][] prehodi6 = {
            { 0.5, 0.5, 0, 0, 0 },
            { 0, 0, 0, 0.5, 0.5 },
            { 0, 0.5, 0, 0, 0.5 },
            { 0.5, 0, 0, 0.5, 0 },
            { 0.5, 0.5, 0, 0, 0 }
    };
    
    private double[][] human = {
        { 0.215, 0.269, 0.160, 0.160, 0.195 },
        { 0.259, 0.179, 0.211, 0.176, 0.175 },
        { 0.220, 0.218, 0.182, 0.222, 0.159 },
        { 0.232, 0.182, 0.192, 0.182, 0.207 },
        { 0.216, 0.213, 0.177, 0.197, 0.197 }
    };
    
    public AlgoritemPrehodaStanj() {
        rnd = new Random();
        izbraniPrehodi = prehodi5;
    }
    
    @Override
    public int naslednjaPoteza() {
        double vrednost = rnd.nextDouble();
        for (int i = 0; i < 5; i++) {
            if (vrednost <= izbraniPrehodi[stanje][i]) {
                stanje = i;
                break;
            } else {
                vrednost -= izbraniPrehodi[stanje][i];
            }
        }
        return stanje;
    }

    @Override
    public void nasprotnikovaPoteza(int poteza) {
    }

    @Override
    public void ponastavi() {
       stanje = 0;
    }
    
}
