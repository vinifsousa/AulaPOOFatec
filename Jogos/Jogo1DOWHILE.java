import java.util.Scanner;

public class Jogo1DOWHILE {
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numeroSecreto = (int)(Math.random() * 100) + 1;

        int tentativas = 0;
        int maxTentativas = 10;
        boolean acertou = false;

        System.out.println("Jogo da Adivinhação");

        do {
            System.out.print("Tentativa " + (tentativas + 1) + ": ");
            int palpite = sc.nextInt();

            tentativas++;

            if (palpite == numeroSecreto) {
                System.out.println("Acertou em " + tentativas + " tentativas!");
                acertou = true;
            } else if (palpite > numeroSecreto) {
                System.out.println("O número é menor!");
            } else {
                System.out.println("O número é maior!");
            }

        } while (tentativas < maxTentativas && !acertou);

        if (!acertou) {
            System.out.println("Fim de jogo! Número: " + numeroSecreto);
        }

        sc.close();
    }
}