package projekt1;

/**
 * Created by Lojze on 25.3.2016.
 */

// Primerjava različnih algoritmov
public class VsMode {
    static final int[][] matrika =
            { {0, 1, -1, -1, 1},
                    {-1, 0, 1, 1, -1},
                    {1, -1, 0, -1, 1},
                    {1, -1, 1, 0, -1},
                    {-1, 1, -1, 1, 0} };

    static Algoritem a1;
    static Algoritem a2;

    public static void main(String[] args){
        a1 = new PredictiveAlgoritem(PredictiveAlgoritem.NACIN_POTEZE_OBEH, 5,
                PredictiveAlgoritem.UTEZITEV_EKSPONENTNO,
                PredictiveAlgoritem.UTEZITEV_LINEARNO,
                PredictiveAlgoritem.IZBIRANJE_PAMETNO, true);

        a2 = new AlgoritemPrehodaStanj();


        simulateGame(1000);
    }

    static void simulateGame(int len){
        int z1=0, z2=0;
        for(int i=0; i<len; i++){
            int m1 = a1.naslednjaPoteza();
            int m2 = a2.naslednjaPoteza();

            a1.nasprotnikovaPoteza(m2);
            a2.nasprotnikovaPoteza(m1);

            int z = matrika[m1][m2];
            if(z<0){
                z1++;
            }
            else if(z>0){
                z2++;
            }
        }

        System.out.println(String.format("1: %d zmag   |   2: %d zmag", z1, z2));
    }



}
