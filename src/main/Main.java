// nome: Lucca Eppinger
// nUSP: 11381406

//7 5
//        . . . . .
//        . X . X .
//        . X . X .
//        . . . . .
//        . X . X .
//        . X . X .
//        . . . . .
//        0
//        6 2
//        0 2

package main;

import explorador.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public Scanner scanner;
    public List itens;
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
    public List pilhas;
    public String pilha = "";


    public Main() {

        scanner = new Scanner(System.in);
        itens = new ArrayList<Item>();
        pilhas = new ArrayList<String>();

    }

    public static void main(String[] args) {

        new Main().run();

    }


    public void run() {

        lerLabirinto();
        lerItens();
        lerPosicoes();
        andar(0);
        imprimirRespostas();

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

            //lab[linha][coluna] = 'I';

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


        pilhas.forEach(passos -> System.out.println(passos));

    }
}


