package ohtu.intjoukkosovellus;

import java.util.ArrayList;
import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla.

    private static int[] aTaulu;
    private static int[] bTaulu;

    public IntJoukko() {
        taulukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        taulukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti tai kasvatuskoko väärin");
        }
        taulukko = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public void lisaa(int luku) {
        if (!kuuluu(luku)) {
            if (taulukko.length == alkioidenLkm) {
                kasvataTaulukkoa();
            }
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return true;
            }
        }
        return false;
    }

    private void eheytaTaulukko(int tyhja) {
        int[] temp = new int[taulukko.length];
        if (tyhja == 0) {
            System.arraycopy(taulukko, 1, temp, 0, taulukko.length - 1);
            taulukko = temp;
        } else {
            System.arraycopy(taulukko, 0, temp, 0, tyhja);
            System.arraycopy(taulukko, tyhja + 1, temp, tyhja, taulukko.length - tyhja - 1);
            taulukko = temp;
        }
    }

    public void poista(int luku) {
        if (kuuluu(luku)) {
            for (int i = 0; i < alkioidenLkm; i++) {
                if (taulukko[i] == luku) {
                    eheytaTaulukko(i);
                    alkioidenLkm--;
                    break;
                }
            }
        }
    }

    private void kasvataTaulukkoa() {
        int[] temp = new int[taulukko.length + kasvatuskoko];
        System.arraycopy(taulukko, 0, temp, 0, taulukko.length);
        taulukko = temp;
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String tuloste = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuloste += taulukko[i];
            if (i < alkioidenLkm - 1) {
                tuloste += ", ";
            }
        }
        tuloste += "}";
        return tuloste;
    }

    public int[] toIntArray() {
        int[] aputaulu = new int[alkioidenLkm];
        System.arraycopy(taulukko, 0, aputaulu, 0, aputaulu.length);
        return aputaulu;
    }

    private static void passToIntArray(IntJoukko a, IntJoukko b) {
        aTaulu = a.toIntArray();
        bTaulu = b.toIntArray();
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        passToIntArray(a, b);
        for (int i = 0; i < aTaulu.length; i++) {
            x.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            x.lisaa(bTaulu[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        passToIntArray(a, b);
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    y.lisaa(bTaulu[j]);
                }
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        passToIntArray(a, b);
        for (int i = 0; i < aTaulu.length; i++) {
            z.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            z.poista(i);
        }
        return z;
    }

}
