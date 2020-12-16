package NotDefteri;

import java.io.Serializable;
import java.util.ArrayList;

public class NotDefteri implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    static int tid = 0;
    static ArrayList<Kisi> Kisiler = new ArrayList<>();

    public static void main(String[] args) {
        AnaEkran anaEkran;
        anaEkran = new AnaEkran();
        tid = anaEkran.tidOku();
        anaEkran.objeleriOku();
        anaEkran.ana_Ekran();
    }
    
}