package poov.cadastrovacina.model.filter;

public class VacinaFilter {
    /*
     * Filtro para a classe Vacina
     */
    private Long codigo; // Código da vacina para filtrar
    private String nome; // Nome da vacina para filtrar
    private String descricao; // Descrição da vacina para filtrar

    // Construtor padrão
    public VacinaFilter() {
    }

    // Construtor com parâmetros de código, nome e descrição
    public VacinaFilter(Long codigo, String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    // Getters e setters para os atributos
    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    // Método toString para representar o objeto como uma string
    @Override
    public String toString() {
        return "VacinaFilter{" + "codigo=" + codigo + ", nome=" + nome + ", descricao=" + descricao + '}';
    }

}