import java.util.Scanner;

public class Jogo2 {

    private static final int N = 3;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char[][] tab = new char[N][N];

        limpar(tab);
        char jogador = 'X';

        System.out.println(" Jogo da Velha ");
        imprimeGuia();

        while (true) {
            imprime(tab);

            int[] pos = lerJogadaValida(tab, jogador);
            tab[pos[0]][pos[1]] = jogador;

            if (venceu(tab, jogador)) {
                imprime(tab);
                System.out.println("Jogador " + jogador + " venceu!");
                break;
            }

            if (empate(tab)) {
                imprime(tab);
                System.out.println("Empate!");
                break;
            }

            jogador = (jogador == 'X') ? 'O' : 'X';
        }

        sc.close();
    }

    private static void limpar(char[][] t) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                t[i][j] = ' ';
    }

    private static void imprimeGuia() {
        System.out.println("Informe LINHA e COLUNA (1 a 3). Ex: 2 3");
        System.out.println();
    }

    private static void imprime(char[][] t) {
        for (int i = 0; i < N; i++) {
            System.out.print(" ");
            for (int j = 0; j < N; j++) {
                System.out.print(t[i][j]);
                if (j < N - 1) System.out.print(" | ");
            }
            System.out.println();
            if (i < N - 1) System.out.println("---+---+---");
        }
    }

    private static int[] lerJogadaValida(char[][] t, char jogador) {
        while (true) {
            System.out.print("Jogador " + jogador + " (linha coluna): ");

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Linha inválida.");
                continue;
            }
            int lin = sc.nextInt();

            if (!sc.hasNextInt()) {
                sc.next();
                System.out.println("Coluna inválida.");
                continue;
            }
            int col = sc.nextInt();

            if (lin < 1 || lin > 3 || col < 1 || col > 3) {
                System.out.println("Fora do intervalo.");
                continue;
            }

            int i = lin - 1, j = col - 1;

            if (t[i][j] != ' ') {
                System.out.println("Casa ocupada.");
                continue;
            }

            return new int[]{i, j};
        }
    }

    private static boolean venceu(char[][] t, char p) {

        // linhas
        for (int i = 0; i < N; i++) {
            boolean ok = true;
            for (int j = 0; j < N; j++) {
                if (t[i][j] != p) {
                    ok = false;
                    break;
                }
            }
            if (ok) return true;
        }

        // colunas
        for (int j = 0; j < N; j++) {
            boolean ok = true;
            for (int i = 0; i < N; i++) {
                if (t[i][j] != p) {
                    ok = false;
                    break;
                }
            }
            if (ok) return true;
        }

        // diagonal principal
        boolean ok = true;
        for (int k = 0; k < N; k++) {
            if (t[k][k] != p) {
                ok = false;
                break;
            }
        }
        if (ok) return true;

        // diagonal secundária
        ok = true;
        for (int k = 0; k < N; k++) {
            if (t[k][N - 1 - k] != p) {
                ok = false;
                break;
            }
        }

        return ok;
    }

    private static boolean empate(char[][] t) {
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (t[i][j] == ' ')
                    return false;

        return true;
    }
}