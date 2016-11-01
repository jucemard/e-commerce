package br.com.modelo;

/**
 *
 * @author Jucemar Dias
 */
public class ItensPedido {
    private int idProduto;
    private int idPedido;
    private int quantidade;
    private Double valorunitario;

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.idProduto;
        hash = 89 * hash + this.idPedido;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItensPedido other = (ItensPedido) obj;
        if (this.idProduto != other.idProduto) {
            return false;
        }
        if (this.idPedido != other.idPedido) {
            return false;
        }
        return true;
    }
    
    
    
}
