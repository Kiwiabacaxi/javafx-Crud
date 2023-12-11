package poov.cadastrovacina.model;

public class Vacina {

    // Atributos
    private Long codigo; // Identificador único para a vacina
    private String nome; // Nome da vacina
    private String descricao; // Descrição da vacina
    private Situacao situacao = Situacao.ATIVO; // Status da vacina, padrão é ATIVO

    // Construtor padrão
    public Vacina() {
        nome = "Sem nome"; // Nome padrão
        descricao = "Sem descricao"; // Descrição padrão
    }

    // Construtor com parâmetros de nome e descrição
    public Vacina(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    // Construtor com parâmetros de código, nome e descrição
    public Vacina(Long codigo, String nome, String descricao) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
    }

    // Getters e setters para os atributos
    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "codigo: " + codigo + "\nnome: " + nome + "\ndescricao: " + descricao + "\nsituacao: " + situacao;
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
        Vacina other = (Vacina) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

}