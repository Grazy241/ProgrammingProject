public class Passagier extends Persoon {
    double bagageGewicht;
    String ticket;

    Passagier(String naam, int leeftijd, String adres, double bagageGewicht) {
        super(naam, adres, leeftijd);
    this.bagageGewicht =bagageGewicht;
}
}
