// nome: Lucca Eppinger
// nUSP: 11381406

package main;

import explorador.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public Scanner scanner;
    public List<Item> itens;
    public char[][] lab;
    public int linhaAtual;
    public int linhaDestino;
    public static final int CIMA = 1;
    public int colunaAtual;
    public int colunaDestino;
    public static final int DIREITA = 2;
    public static final int BAIXO = 3;
    public static final int ESQUERDA = 4;
    public int linhaInicial;
    public int colunaInicial;
    public int i;
    public int j;
    public List<String> pilhas;
    public String pilha = "";
    public int parametro;

    public Main() {

        scanner = new Scanner(System.in);
        itens = new ArrayList<Item>();
        pilhas = new ArrayList<String>();

    }

    public static void main(String[] args) {

        new Main().run();

    }

    public void run() {

        lerDados();
        andar(0);
        imprimirRespostas();

    }

    private void lerDados() {
        lerLabirinto();
        lerItens();
        lerPosicoes();
        parametro = scanner.nextInt();
    }

    private void lerPosicoes() {

        linhaInicial = scanner.nextInt();
        colunaInicial = scanner.nextInt();
        linhaDestino = scanner.nextInt();
        colunaDestino = scanner.nextInt();
        linhaAtual = linhaInicial;
        colunaAtual = colunaInicial;

    }

    public void lerLabirinto() {

        i = scanner.nextInt();
        j = scanner.nextInt();
        lab = new char[i + 1][j + 1];
        String valor;

        for (int linha = 0; linha < i; linha++) {
            for (int coluna = 0; coluna < j; coluna++) {
                valor = scanner.next();
                lab[linha][coluna] = valor.charAt(0);
            }
        }
    }

    public void lerItens() {

        int quantidadeItens = scanner.nextInt();
        for (int cont = 0; cont < quantidadeItens; cont++) {

            int linha = scanner.nextInt();
            int coluna = scanner.nextInt();
            int valor = scanner.nextInt();
            int peso = scanner.nextInt();
            Item item = new Item(linha, coluna, valor, peso);
            itens.add(item);

        }
    }

    public void imprimiLabirinto(char[][] lab) {

        for (int linha = 0; linha < i; linha++) {
            for (int coluna = 0; coluna < j; coluna++) {

                System.out.print(lab[linha][coluna] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public void andar(int ultimaAcao) {

        imprimiLabirinto(lab);

        if (linhaAtual == linhaDestino && colunaAtual == colunaDestino) {

            pilhas.add(pilha);
            System.out.println("cheguei no fim");
            volta();

        } else if (linhaAtual != 0 && lab[linhaAtual - 1][colunaAtual] == '.' && ultimaAcao < 1) {

            pilha += CIMA;
            lab[linhaAtual][colunaAtual] = '1';
            linhaAtual -= 1;
            andar(0);

        } else if (colunaAtual != (j - 1) && lab[linhaAtual][colunaAtual + 1] == '.' && ultimaAcao < 2) {

            pilha += DIREITA;
            lab[linhaAtual][colunaAtual] = '2';
            colunaAtual += 1;
            andar(0);

        } else if (linhaAtual != (i - 1) && lab[linhaAtual + 1][colunaAtual] == '.' && ultimaAcao < 3) {

            pilha += BAIXO;
            lab[linhaAtual][colunaAtual] = '3';
            linhaAtual += 1;
            andar(0);

        } else if (colunaAtual != 0 && lab[linhaAtual][colunaAtual - 1] == '.' && ultimaAcao < 4) {

            pilha += ESQUERDA;
            lab[linhaAtual][colunaAtual] = '4';
            colunaAtual -= 1;
            andar(0);

        } else {

            volta();

        }
    }

    private void volta() {

        System.out.println("Voltando");
        if (linhaAtual == linhaInicial && colunaInicial == colunaAtual) {
        } else {

            int ultimaAcao = pilha.charAt(pilha.length() - 1) - '0';
            pilha = pilha.substring(0, pilha.length() - 1);

            if (ultimaAcao == CIMA) {
                linhaAtual += 1;
            }

            if (ultimaAcao == DIREITA) {
                colunaAtual -= 1;
            }

            if (ultimaAcao == BAIXO) {
                linhaAtual -= 1;
            }

            if (ultimaAcao == ESQUERDA) {
                colunaAtual += 1;
            }

            lab[linhaAtual][colunaAtual] = '.';
            andar(ultimaAcao);
        }
    }

    private void imprimirRespostas() {


        for (String passo : pilhas) {
            calcularExploracao(passo);

        }

    }

    public double calculaTempo(int peso) {

        double p = peso;

        double tempo = Math.pow((1 + (p / 10)), 2);

        return tempo;
    }

    private void calcularExploracao(String passos) {

        List<String> caminho = new ArrayList<>();
        List<String> posItem = new ArrayList<>();
        int li = linhaInicial;
        int ci = colunaInicial;
        double tempo = 0;
        int peso = 0;
        int valor = 0;
        int espacos = passos.length();
        espacos++;
        char[] passo = passos.toCharArray();
        int coletados = 0;

        caminho.add((li + " " + ci));

        for (char a : passo) {

            for (Item item : itens) {

                if (item.linha == li && item.coluna == ci) {
                    valor += item.valor;
                    peso += item.peso;
                    coletados++;
                    posItem.add((li + " " + ci));

                }

            }
            tempo += calculaTempo(peso);
            if (a == '1') {
                --li;
            }
            if (a == '2') {
                ++ci;
            }
            if (a == '3') {
                ++li;
            }
            if (a == '4') {
                --ci;
            }

            caminho.add((li + " " + ci));

        }

        System.out.print(espacos + " ");
        System.out.println(String.format("%.2f", tempo));
        for (String pos : caminho) {
            System.out.println(pos);

        }
        System.out.println(coletados + " " + valor + " " + peso);
        for (String pos : posItem) {
            System.out.println(pos);

        }
        System.out.println();



    }
}