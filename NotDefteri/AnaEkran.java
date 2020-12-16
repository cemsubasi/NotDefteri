package NotDefteri;

import java.util.Scanner;
import java.io.*;


public class AnaEkran implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Scanner scan = new Scanner(System.in);
    int secenek;
    String ad, sifre;    
    Kisi kisi;
   
    public void ana_Ekran() {
        System.out.println("NOT DEFTERİ");
        System.out.println("Seçenekler");
        System.out.println("0.Cikis Yap");
        System.out.println("1.Giris Yap");
        System.out.println("2.Hesap Oluştur");
        secimYap();  
    }

    public void girisEkrani(){
        System.out.println("Kullanıcı Adi: ");
        ad = scan.next();
            if(adKarsilastir(ad) == false){
                System.out.println("Bu kullanıcı adı bulunmuyor!");
                ana_Ekran();
            }
            else{
                System.out.println("Şifre: ");
                sifre = scan.next();
                    if(sifreKarsilastir(ad, sifre) == false){
                        System.out.println("Kullanıcı adı ve şifre uyuşmuyor!");
                        ana_Ekran();
                    }
                    else{
                        System.out.print("Giris Basarili!\n");
                        dosyadanOku(ad);
                        dosyayaYaz(ad);
                        ana_Ekran();
                    }
            }
    }

    public void secimYap(){
        secenek = scan.nextInt();
	    if(secenek == 1){
                girisEkrani();            
        }
            else if(secenek == 2){
                kisiOlustur();
            }
                else if(secenek == 0){
                    System.exit(0);
                }
                    else{
                            System.out.println("Hatalı Giriş");
                            ana_Ekran();
                        }
    }

    public void kisiOlustur(){
        System.out.println("Kullanıcı Adi: ");
        ad = scan.next();
        if(adKarsilastir(ad) == true){
            System.out.println("Bu kullanıcı adı daha önce kullanılmış!");
            kisiOlustur();
        }
        else{
        System.out.println("Şifre: ");
        sifre = scan.next();
        kisi = new Kisi(ad, sifre, NotDefteri.tid);
        NotDefteri.Kisiler.add(NotDefteri.tid, kisi);
        try {
            FileOutputStream fos = new FileOutputStream("obje" + NotDefteri.tid);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(NotDefteri.Kisiler.get(NotDefteri.tid));
            oos.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        NotDefteri.tid++;
        tidYaz(NotDefteri.tid);
        ana_Ekran();
        }
    }
    
    public void objeleriOku(){
	//Hatali yol
       /*
        int i = 0;
        try {
                FileInputStream fos = new FileInputStream("obje");
                while(){
                    ObjectInputStream oos = new ObjectInputStream(fos);
                    NotDefteri.Kisiler.add(i, (Kisi)oos.readObject());
                    oos.close();
                    i++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
         */
        int i = 0;
        try {
            while(i < NotDefteri.tid){
                FileInputStream fos = new FileInputStream("obje" + i);
                ObjectInputStream oos = new ObjectInputStream(fos);
                NotDefteri.Kisiler.add(i, (Kisi)oos.readObject());
                oos.close();
                i++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    boolean adKarsilastir(String name){
        boolean test = false;
        for(int i = 0; i < NotDefteri.tid; i++){
            if(name.equals(NotDefteri.Kisiler.get(i).getAd()) == true){
                test = true;
                break;
            }
        }
        return test;
    }
    

    boolean sifreKarsilastir(String name, String passwd){
        boolean test = false;
        for(int i = 0; i < NotDefteri.tid; i++){
            if((name.equals(NotDefteri.Kisiler.get(i).getAd()) == true) && (passwd.equals(NotDefteri.Kisiler.get(i).getSifre()) == true)){
                test = true;
                break;
            }
        }
        return test;
    }

    public int tidBul(String name){
        int test = 0;
        for(int i = 0; i <= NotDefteri.tid; i++){
            if((name.equals(NotDefteri.Kisiler.get(i).getAd())) == true){
                test = i;
                break;
            }
        }

    return test;
    }

    public void dosyadanOku(String name){
        int i;
        String buffer;
        i = tidBul(name);
        File f = NotDefteri.Kisiler.get(i).fkisi;
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(new FileReader(f));
            while((buffer = br.readLine()) != null){
                System.out.println(buffer);
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dosyayaYaz(String name){
        int i = 0;
        String buffer;
        i = tidBul(name);
        File f = NotDefteri.Kisiler.get(i).fkisi;
        try {
            FileWriter fw = new FileWriter(f, true);
            buffer = scan.next();
            if(buffer.equals("cikis") == true){
                System.exit(0);
            }
            else{
                fw.write(buffer + "\n");
            fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void tidYaz(int no){
        try {
            FileWriter fw = new FileWriter("tid");
            fw.write(no);
            fw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public int tidOku(){
            int no = 0;
            try {
            FileReader fr = new FileReader("tid");
            no = fr.read();
            fr.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return no;
    }

}
