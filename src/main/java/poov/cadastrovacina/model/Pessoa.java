package poov.cadastrovacina.model;

import java.time.LocalDate;

public class Pessoa {

    private Long codigo;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private Situacao situacao = Situacao.ATIVO;

    public Pessoa() {
        nome = "Sem nome";
        cpf = "Sem cpf";
        dataNascimento = LocalDate.now();
    }

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(Long codigo, String nome, String cpf, LocalDate dataNascimento) {
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    // getters and setters
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

    // equals, hashCode and toString
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        return result;
    }

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

    @Override
    public String toString() {
        return "codigo: " + codigo + "\nnome: " + nome + "\ncpf: " + cpf + "\ndataNascimento: " + dataNascimento
                + "\nsituacao: " + situacao;
    }
}