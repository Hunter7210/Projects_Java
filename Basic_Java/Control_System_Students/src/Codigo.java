import java.util.Scanner;

public class Codigo {

    Scanner sc = new Scanner(System.in);
    String[] disciplinas = new String[]{"Geografia", "Matematica", "Física", "Portugues"};
    int[] notas = new int[4];

    // Adiciona as notas ao array 'notas'
    public void adicionarNota() {
        for (int i = 0; i < disciplinas.length; i++) {
            System.out.print("Digite a nota da disciplina " + disciplinas[i] + ": ");
            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.next(); // Limpa o input inválido
            }
            notas[i] = sc.nextInt();
        }
        calcularMediaNota();
    }

    // Calcula e exibe a média das notas
    public void calcularMediaNota() {
        int somaNota = 0;
        for (int nota : notas) {
            somaNota += nota;
        }
        double media = (double) somaNota / notas.length;
        System.out.println("A média das notas do aluno é: " + media);
        classificarAluno(media);
    }

    public void classificarAluno(double medianota) {

        if (medianota >= 7) {
            System.out.println("Usuario aprovado");
        } else if (medianota >= 5 && medianota <= 6.9) {
            System.out.println("Usuario em Recuperação");

        } else if (medianota < 5) {
            System.out.println("Usuario reprovado");
        }

    }
}
