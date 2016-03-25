package projekt1;

/**
 * Created by Lojze on 25.3.2016.
 */
public class TestAlgoritem implements Algoritem {

    int poteza;
    @Override
    public int naslednjaPoteza() {
        return poteza;
    }

    @Override
    public void nasprotnikovaPoteza(int poteza) {
        this.poteza = (int)(Math.random()*poteza);
    }

    @Override
    public void ponastavi() {

    }
}
