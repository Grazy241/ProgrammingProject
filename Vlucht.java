import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import java.util.HashSet;

public class Vlucht {
    String code;
    String bestemming;
    int economyStoelen;
    int businessStoelen;
    List<String> passagiers = new ArrayList<>();
    Set<String> personeelRollen = new HashSet<>();

    Vlucht(String code, String bestemming, int economyStoelen, int businessStoelen) {
        this.code = code;
        this.bestemming = bestemming;
        this.economyStoelen = economyStoelen;
        this.businessStoelen = businessStoelen;

    }


    boolean voegPassagierToe(String naam, String ticketKlasse) {
        if ("economy".equals(ticketKlasse) && economyStoelen > 0){
            passagiers.add(naam);
            economyStoelen--;
            return true;
    }  else if ("business".equals(ticketKlasse) && businessStoelen > 0){
        passagiers.add(naam);
        businessStoelen--;
        return true;
    }
    System.out.println("Geen beschikbare stoelen in klasse: " + ticketKlasse);
    return false;
}
    boolean voegPersoneelToe (String rol){
    personeelRollen.add(rol);
    return true;
    }

    boolean magOpstijgen() {
    return personeelRollen.contains("Piloot") && personeelRollen.contains("Cabincrew") && !passagiers.isEmpty();
    }

    void printInfo() {
        System.out.println("Vlucht: " + code + ", Bestemming: " + bestemming);
        System.out.println("Passagiers: " + passagiers);
        System.out.println("Personeel Rollen: " + personeelRollen);
    }
    }