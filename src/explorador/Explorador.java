package explorador;

public class Explorador {

    int peso;
    int tempo;


    public double passo(int peso) {

        double tempo = Math.pow((1 + (peso / 10)), 2);

        return tempo;
    }

}
