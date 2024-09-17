package Models;

public class Funcionario {
    /* Criando os atributos */
    String nomeFuncionario;
    int idadeFuncionario;
    int salarioFuncionario;

    /* Criando um construtor default */
    public Funcionario() {

    }

    /* Criando o construtor */
    public Funcionario(String nomeFuncionario, int idadeFuncionario, int salarioFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
        this.idadeFuncionario = idadeFuncionario;
        this.salarioFuncionario = salarioFuncionario;
    }

    /* Criação de todos os getters */

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public String getIdadeFuncionario() {
        return nomeFuncionario;
    }

    public String getREFuncionario() {
        return nomeFuncionario;
    }

    /* Criação de todos os setters */

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public void setIdadeFuncionario(int idadeFuncionario) {
        this.idadeFuncionario = idadeFuncionario;
    }

    public void setREFuncionario(int reFuncionario) {
        this.salarioFuncionario = reFuncionario;
    }

}
