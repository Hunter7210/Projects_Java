import java.util.Scanner;

public class App {
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Codigo codigo = new Codigo();
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Notas");
            // System.out.println("2. Calcular e Exibir Média");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
                sc.next(); // Limpa o input inválido
            }
            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    codigo.adicionarNota();
                    break;
              /*   case 2:
                    codigo.calcularMediaNota();
                    break; */
                case 2:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 3);

        sc.close();
    }
}
