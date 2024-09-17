package Controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Models.Funcionario;

public class FuncionarioController {
    Scanner sc = new Scanner(System.in);
    private List<Funcionario> funcionarios = new ArrayList<>();

    // Criação do CRUD

    // Método para listar todos os funcionários
    public void listarFuncionarios() {
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }
    }

    // Criação do metodo create
    public void adicionarFuncionario(String nomeFunc, int idadeFunc, int salarioFunc) {

        System.out.println("Digite o nome do funcionario : ");
        String nome = sc.next();
        System.out.println("Digite a idade do funcionario : ");
        int idade = Integer.parseInt(sc.next());
        System.out.println("Digite o salario do funcionario : ");
        int salario = Integer.parseInt(sc.next());
        Funcionario newfuncionario = new Funcionario(nome, idade, salario);

        funcionarios.add(newfuncionario);
    }

    // Método para deletar um funcionário pelo nome
    public void deletarFuncionario(String nomeFunc) {
        Iterator<Funcionario> iterator = funcionarios.iterator();
        while (iterator.hasNext()) {
            Funcionario funcionario = iterator.next();
            if (funcionario.getNomeFuncionario().equals(nomeFunc)) {
                iterator.remove();
                System.out.println("Funcionário " + nomeFunc + " removido com sucesso!");
                return; // Saia do método após encontrar e remover o funcionário
            }
        }
        System.out.println("Funcionário " + nomeFunc + " não encontrado.");
    }

}
