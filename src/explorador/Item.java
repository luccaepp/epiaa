package explorador;

public class Item {

    public int linha;
    public int coluna;
    public int valor;
    public int peso;
    public boolean coletado;


    public Item(int linha, int coluna, int valor, int peso) {

        this.linha = linha;
        this.coluna = coluna;
        this.valor = valor;
        this.peso = peso;
        coletado = false;


    }

}
