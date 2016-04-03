package projekt1;

import java.util.Arrays;

/**
 * Created by Lojze on 25.3.2016.
 */
public class PredictiveAlgoritem implements Algoritem {

    // Načini iskanja po zgodovini
    public static final int NACIN_NASPROTNIKOVE_POTEZE = 0;
    public static final int NACIN_POTEZE_OBEH = 1;

    // Načini utežitve
    public static final int UTEZITEV_NEOBTEZENO = 0;
    public static final int UTEZITEV_LINEARNO = 1;
    public static final int UTEZITEV_EKSPONENTNO = 2;

    // Načini izbiranja naslednje poteze
    public static final int IZBIRANJE_RANDOM = 0;
    public static final int IZBIRANJE_PAMETNO = 1;

    public static final int maksOcena = 3;

    static final int[][] matrika =
            { {0, 1, -1, -1, 1},
                    {-1, 0, 1, 1, -1},
                    {1, -1, 0, -1, 1},
                    {1, -1, 1, 0, -1},
                    {-1, 1, -1, 1, 0} };


    // Razločevanje med nasportnikovimi potezami in mojimi
    static int MyMove(int move){
        return 9-move;
    }

    static int indexOfMax(long[] t){
        long maks=Long.MIN_VALUE;
        int iMaks=-1;
        for(int i=0; i<t.length; i++){
            int cmp = Long.compare(t[i], maks);
            if(cmp==0){
                iMaks = Math.random()>0.5?iMaks:i;
                maks = t[iMaks];
            }else if(cmp>0){
                iMaks = i;
                maks = t[iMaks];
            }
        }
        return iMaks;
    }

    static int ClampOcena(int ocena){
        return Math.max(-maksOcena, Math.min(maksOcena, ocena));
    }

    int nacin; // katere poteze naj gleda algoritem
    int maxDolzina; // največja dolžina ujemanja
    int utezitevDolzine; // kako so utezena ujemanja razlicne dolzine
    int utezitevStarosti; // kako so utezena ujemanja glede na starost
    int izbiranje; // način izbiranja naslednje poteze
    boolean smartMode; // odločanje o uporabi načina, glede na rezultate

    String oponnentHistory = "";
    String totalHistory = "";

    int zadnjaPoteza;

    long[] nacinT = new long[2];
    long[] utezitevDolzineT = new long[3];
    long[] utezitevStarostiT = new long[3];
    long[] izbiranjeT = new long[2];

    // Default konstruktor
    public PredictiveAlgoritem(){
        this(NACIN_POTEZE_OBEH, 5, UTEZITEV_EKSPONENTNO, UTEZITEV_LINEARNO, IZBIRANJE_PAMETNO, true);
    }


    // Konstruktor s parametri
    public PredictiveAlgoritem(int nacin, int maxDolzina, int utezitevDolzine, int utezitevStarosti, int izbiranje, boolean smartMode){
        this.nacin = nacin;
        this.maxDolzina = maxDolzina;
        this.utezitevDolzine = utezitevDolzine;
        this.utezitevStarosti = utezitevStarosti;
        this.izbiranje = izbiranje;
        this.smartMode = smartMode;
    }

    @Override
    public int naslednjaPoteza() {
        long[] p = izracunajVerjetnosti();
        int move = izberiPotezo(p);

        totalHistory += MyMove(move);
        zadnjaPoteza = move;
        return move;
    }

    @Override
    public void nasprotnikovaPoteza(int poteza) {
        oponnentHistory += poteza;
        totalHistory += poteza;

        if(smartMode) {
            int zmaga = matrika[zadnjaPoteza][poteza];
            if (zmaga > 0) {
                updateSmartTables(1);
            } else if (zmaga < 0) {
                updateSmartTables(-1);
            }
        }
    }

    @Override
    public void ponastavi() {
        oponnentHistory = "";
        totalHistory = "";

        System.out.println(Arrays.toString(nacinT));
        System.out.println(Arrays.toString(utezitevDolzineT));
        System.out.println(Arrays.toString(utezitevStarostiT));
    }

    long[] izracunajVerjetnosti(){
        long[] p = new long[5];

        String searchString;
        int incr;
        int mult;
        if(nacin == NACIN_NASPROTNIKOVE_POTEZE) {
            searchString = oponnentHistory;
            incr=0;
            mult=1;
        }
        else {
            searchString = totalHistory;
            incr=1;
            mult=2;
        }

        // pregledamo vse dolžine
        for(int l=maxDolzina*mult; l>0; l-=mult){
            if(l>=searchString.length())
                continue;

            String substr = searchString.substring(searchString.length()-l);

            // find all matches
            int fIndex = searchString.indexOf(substr, 0);
            while(fIndex >= 0){
                if((fIndex+l+incr) >= searchString.length())
                    break;

                int m = searchString.charAt(fIndex+l+incr)-'0';
                p[m] += Math.pow((l/mult), utezitevDolzine) + Math.pow(fIndex, utezitevDolzine);

                fIndex = searchString.indexOf(substr, fIndex+1);
            }
        }

        return p;
    }

    int izberiPotezo(long p[]){

        if(izbiranje == IZBIRANJE_RANDOM) {
            // Poiščemo najbolj verjetno potezo nasprotnika
            int iMaks = indexOfMax(p);

            // Naključno izberemo eno izmed dveh potez, ki jo premagata
            int[] poteze = matrika[iMaks];
            int count=0;
            for(int i=0; i<5; i++) {
                if (poteze[i] == 1) {
                    count++;
                    if (count > 1 || Math.random() > 0.5) {
                        return i;
                    }
                }
            }

        }
        else if(izbiranje == IZBIRANJE_PAMETNO) {
            // v poteze shranimo št. zmag če izberemo i-to potezo
            // izberemo potezo z največ zmagami
            long[] poteze = new long[5];
            for(int i=0; i<5; i++){

                for(int j=0; j<5; j++) {
                    poteze[i] += -matrika[i][j]*p[j];
                }
            }
            return indexOfMax(poteze);
        }

        return 0;
    }

    void updateSmartTables(int zmaga){
        nacinT[nacin] = ClampOcena((int)nacinT[nacin]+zmaga);
        utezitevDolzineT[utezitevDolzine] = ClampOcena((int)utezitevDolzineT[nacin]+zmaga);
        utezitevStarostiT[utezitevStarosti] = ClampOcena((int)utezitevStarostiT[nacin]+zmaga);
        /*izbiranjeT[izbiranje] += zmaga;*/

        nacin = indexOfMax(nacinT);
        utezitevDolzine = indexOfMax(utezitevDolzineT);
        utezitevStarosti = indexOfMax(utezitevStarostiT);
        //System.out.println(Arrays.toString(nacinT));
        /*utezitevDolzine = indexOfMax(utezitevDolzineT);
        utezitevStarosti = indexOfMax(utezitevStarostiT);
        izbiranje = indexOfMax(izbiranjeT);*/
    }
}
