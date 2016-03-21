/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt1;

/**
 *
 * @author Marko
 */
public interface Algoritem {
    public int naslednjaPoteza(); // 0-4
    public void nasprotnikovaPoteza(int poteza); // poslji igralcevo potezo, da jo algoritem lahko uposteva
    public void ponastavi();
}
