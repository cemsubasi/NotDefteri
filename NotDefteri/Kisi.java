package NotDefteri;

import java.io.*;

public class Kisi implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Kisi(String Ad, String Sifre, int Kid) {

        setAd(Ad);
        setSifre(Sifre);
        setKid(Kid);
        openFile();
        fkisi = setFile();
    }

    private String Ad;
    private String Sifre;
    private int kid;
    File fkisi;

    
    public File setFile(){
        String name = "" + getKid();
        File fName = new File(name);        

        return fName;
    }

    public String getAd() {
        return Ad;
    }

    public void setAd(String ad) {
        Ad = ad;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String sifre) {
        Sifre = sifre;
    }

    public int getKid() {
        return kid;
    }

    public void setKid(int kid) {
        this.kid = kid;
    }

    public void openFile(){
        try {
            FileWriter fw = new FileWriter(setFile(), true);
            fw.write("");
            fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}