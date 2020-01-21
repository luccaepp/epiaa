// nome: Lucca Eppinger
// nUSP: 11381406


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
    public int colunaAtual;
    public int colunaDestino;

    public List pilha;


    public Main() {

        scanner = new Scanner(System.in);
        itens = new ArrayList<Item>();
        pilha = new ArrayList<Integer>();

    }

    public static void main(String[] args) {

        new Main().run();

    }


    public void run() {

        lerLabirinto();
        lerItens();
        lerPosicoes();
        imprimiLabirinto(lab);

    }

    private void lerPosicoes() {

        linhaAtual = scanner.nextInt();
        colunaAtual = scanner.nextInt();
        linhaDestino = scanner.nextInt();
        colunaDestino = scanner.nextInt();

        lab[linhaAtual][colunaAtual] = 'E';
        lab[linhaDestino][colunaDestino] = 'S';


    }

    public void lerLabirinto() {

        int i = scanner.nextInt();
        int j = scanner.nextInt();
        lab = new char[i][j];
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


        for (int linha = 0; linha < lab.length; linha++) {
            for (int coluna = 0; coluna < lab[0].length; coluna++) {

                System.out.print(lab[linha][coluna] + " ");

            }
            System.out.println();

        }

    }

}