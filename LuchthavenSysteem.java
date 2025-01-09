import java.util.*;
public class LuchthavenSysteem {
    static final double MAX_BAGAGE_GEWICHT = 20.0;
    static class Persoon {
        String naam;
        String adres;
        int leeftijd;
        Persoon(String naam, String adres, int leeftijd) {
            this.naam = naam;
            this.adres = adres;
            this.leeftijd = leeftijd;
        }
    }
    static class Passagier extends Persoon {
        double bagageGewicht;
        String ticket;
        Passagier(String naam, int leeftijd, String adres, double bagageGewicht) {
            super(naam, adres, leeftijd);
            this.bagageGewicht =bagageGewicht;
        }
    }
    static class Vlucht {
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
        } else if ("business".equals(ticketKlasse) && businessStoelen > 0){
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
    public static void main(String[] args) {
        Scanner fetch = new Scanner(System.in);
        Map<String, Passagier> passagiers = new HashMap<>();
        Map<String, Vlucht> vluchten = new HashMap<>();
        Random random = new Random();

        while (true) {
            try {
                System.out.println("\n1. Voeg Passagier toe: ");
                System.out.println("2. Voeg Vlucht toe: ");
                System.out.println("3. Maak Ticket: ");
                System.out.println("4. Passagier Boarden: ");
                System.out.println("5. Personeel toewijzen: ");
                System.out.println("6. Print Vlucht info: ");
                System.out.println("7. Afsluiten");
                System.out.println("Maak een keuze: ");
                int keuze = Integer.parseInt(fetch.nextLine());

                switch (keuze) {
                    case 1: {
                        System.out.println("Naam: ");
                        String naam = fetch.nextLine();
                        System.out.println("Leeftijd: ");
                        int leeftijd = Integer.parseInt(fetch.nextLine());
                        System.out.println("Adres: ");
                        String adres = fetch.nextLine();
                        System.out.println("Bagage Gewicht (Maximaal " + MAX_BAGAGE_GEWICHT + "kg): ");
                        double gewicht = Double.parseDouble(fetch.nextLine());
                        if (gewicht > MAX_BAGAGE_GEWICHT) System.out.println("Bagage te zwaar!");
                        else passagiers.put(naam, new Passagier(naam, leeftijd, adres, gewicht));
                    }
                    case 2: {
                        String code = "VL" + (1000 + random.nextInt(9000));
                        System.out.println("Gegenereerde Vlucht Code: " + code);
                        System.out.println("Bestemming: ");
                        String bestemming = fetch.nextLine();
                        System.out.println("Economy Stoelen: ");
                        int econ = Integer.parseInt(fetch.nextLine());
                        System.out.println("Business Stoelen: ");
                        int bus = Integer.parseInt(fetch.nextLine());
                        vluchten.put(code, new Vlucht(code, bestemming, econ, bus));
                    }
                    case 3: {
                        System.out.println("Passagier Naam: ");
                        String naam = fetch.nextLine();
                        if (passagiers.containsKey(naam)) {
                            System.out.println("Passagier niet gevonden. Controleer de naam.");
                            break;
                        }
                        System.out.println("Vlucht Code: ");
                        String code = fetch.nextLine();
                        if (!vluchten.containsKey(code)) {
                            System.out.println("Vlucht niet gevonden. Controleer de code.");
                            break;
                        }
                        System.out.println("Klasse (economy/business): ");
                        String klasse = fetch.nextLine();
                        if (!klasse.equals("economy") && !klasse.equals("business")) {
                            System.out.println("Passagier niet gevonden. Controleer de naam.");
                            break;
                        }
                        passagiers.get(naam).ticket = code + ":" + klasse;
                    }
                    case 4: {
                        System.out.println("Vlucht Code: ");
                        String code = fetch.nextLine();
                        if (!vluchten.containsKey(code)) {
                            System.out.println("Vlucht niet gevonden. Controleer de code.");
                            break;
                        }
                        System.out.println("Passagier Naam: ");
                        String naam = fetch.nextLine();
                        Passagier p = passagiers.get(naam);
                        if (p == null || p.ticket == null || !p.ticket.startsWith(code)) {
                            System.out.println("Ongeldig ticket of passagiers. Controleer gegevens.");
                            break;
                        }
                        String ticketKlasse = p.ticket.split(":")[1];
                        vluchten.get(code).voegPassagierToe(naam, ticketKlasse);
                    }
                    case 5: {
                        System.out.println("Vlucht Code: ");
                        String code = fetch.nextLine();
                        if (!vluchten.containsKey(code)) {
                            System.out.println("Vlucht niet gevonden. Controleer de code.");
                            break;
                        }
                        System.out.println("Personeel Rol: ");
                        String rol = fetch.nextLine();
                        vluchten.get(code).voegPersoneelToe(rol);
                    }
                    case 6: {
                        System.out.println("Vlucht Code: ");
                        String code = fetch.nextLine();
                        if (!vluchten.containsKey(code)) {
                            System.out.println("Vlucht niet gevonden. Controleer de code.");
                            break;
                        }
                        vluchten.get(code).printInfo();
                    }
                    case 7: {
                        System.out.println("Programma afgesloten.");
                        return;
                    }
                    default:
                        System.out.println("Ongeldige keuze. Probeer opnieuw.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Foutieve invoer bij een numeriek veld. Gebruik een geldig getal");
            }
        catch(Exception e){
                    System.out.println("Er is een fout opgetreden: " + e.getMessage());
                }
            }
        }
    }