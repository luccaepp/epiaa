// nome: Lucca Eppinger
// nUSP: 11381406

package main;

import explorador.Exploracao;
import explorador.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public Scanner scanner;
    public List<Item> itens;
    public static final int CURTO = 1;
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
    public static final int LONGO = 2;
    public static final int VALIOSO = 3;
    public static final int RAPIDO = 4;
    public List<Exploracao> exploracoes;

    public Main() {

        scanner = new Scanner(System.in);
        itens = new ArrayList<Item>();
        pilhas = new ArrayList<String>();
        exploracoes = new ArrayList<Exploracao>();


    }

    public static void main(String[] args) {

        new Main().run();

    }

    public void run() {

        lerDados();
        andar(0);
        imprimirRespostas();
        calcularParametro();

    }


    private void lerDados() {
        lerLabirinto();
        lerItens();
        lerPosicoes();
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

        //imprimiLabirinto(lab);

        if (linhaAtual == linhaDestino && colunaAtual == colunaDestino) {

            pilhas.add(pilha);
            //System.out.println("cheguei no fim");
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

        //System.out.println("Voltando");
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

        Exploracao explo = new Exploracao();
        explo.li = linhaInicial;
        explo.ci = colunaInicial;
        explo.tempo = 0;
        explo.peso = 0;
        explo.valor = 0;
        explo.espacos = passos.length();
        explo.espacos++;
        explo.passo = passos.toCharArray();
        explo.coletados = 0;

        explo.caminho.add((explo.li + " " + explo.ci));

        for (char a : explo.passo) {

            for (Item item : itens) {

                if (item.linha == explo.li && item.coluna == explo.ci) {
                    explo.valor += item.valor;
                    explo.peso += item.peso;
                    explo.coletados++;
                    explo.posItem.add((explo.li + " " + explo.ci));

                }

            }
            explo.tempo += calculaTempo(explo.peso);
            if (a == '1') {
                --explo.li;
            }
            if (a == '2') {
                ++explo.ci;
            }
            if (a == '3') {
                ++explo.li;
            }
            if (a == '4') {
                --explo.ci;
            }

            explo.caminho.add((explo.li + " " + explo.ci));

        }

        //System.out.println();
        exploracoes.add(explo);

    }

    private void calcularParametro() {

        Exploracao explo = new Exploracao();

        parametro = scanner.nextInt();

        if (parametro == CURTO) {

            for (Exploracao curta : exploracoes) {

                if (explo.espacos == 0) {
                    explo = curta;
                }
                if (explo.espacos >= curta.espacos) {

                    explo = curta;

                }


            }


        } else if (parametro == LONGO) {

            for (Exploracao longa : exploracoes) {

                if (explo.espacos <= longa.espacos) {
                    explo = longa;
                }

            }


        } else if (parametro == VALIOSO) {

            for (Exploracao valiosa : exploracoes) {

                if (explo.valor <= valiosa.valor) {
                    explo = valiosa;
                }

            }


        } else if (parametro == RAPIDO) {

            for (Exploracao rapida : exploracoes) {

                if (explo.tempo == 0) {
                    explo = rapida;
                }
                if (explo.tempo >= rapida.tempo) {

                    explo = rapida;

                }
            }
        }

        imprimirExploracao(explo);
    }

    private void imprimirExploracao(Exploracao explo) {

        System.out.print(explo.espacos + " ");
        System.out.println(String.format("%.2f", explo.tempo));
        for (String pos : explo.caminho) {
            System.out.println(pos);

        }
        System.out.println(explo.coletados + " " + explo.valor + " " + explo.peso);
        for (String pos : explo.posItem) {
            System.out.println(pos);

        }
    }
}