package br.com.modelo;

/**
 *
 * @author Jucemar Dias
 */
public class Produto {

    private int idProduto;
    private String descricao;
    private Double quantidade;
    private Double valor;

    public Produto() {

    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return idProduto + descricao + quantidade + valor;
    }

}
