package poov.cadastrovacina.model;

import java.time.LocalDate;

public class Pessoa {

    private Long codigo; // Identificador único para a pessoa
    private String nome; // Nome da pessoa
    private String cpf; // CPF da pessoa
    private LocalDate dataNascimento; // Data de nascimento da pessoa
    private Situacao situacao = Situacao.ATIVO; // Situação da pessoa, padrão é ATIVO

    // Construtor padrão
    public Pessoa() {
        nome = "Sem nome"; // Nome padrão
        cpf = "Sem cpf"; // CPF padrão
        dataNascimento = LocalDate.now(); // Data de nascimento padrão é a data atual
    }

    // Construtor com parâmetros de nome, cpf e data de nascimento
    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Construtor com parâmetros de código, nome, cpf e data de nascimento
    public Pessoa(Long codigo, String nome, String cpf, LocalDate dataNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // Getters e setters para os atributos
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Situacao getSituacao() {
        return this.situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    // Método hashCode para fazer o hash do objeto
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

    // Método equals para comparar dois objetos
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pessoa other = (Pessoa) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "codigo: " + codigo + "\nnome: " + nome + "\ncpf: " + cpf + "\ndataNascimento: " + dataNascimento
                + "\nsituacao: " + situacao;
    }
}