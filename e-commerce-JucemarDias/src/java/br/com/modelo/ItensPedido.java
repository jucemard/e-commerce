package br.com.modelo;

/**
 *
 * @author Jucemar Dias
 */
public class ItensPedido {

    private int quantidade;
    private Double valorunitario;
    private Produto produto = new Produto();
    private Pedido pedido = new Pedido();

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(Double valorunitario) {
        this.valorunitario = valorunitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
