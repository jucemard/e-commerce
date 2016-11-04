package br.com.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jucemar Dias
 */
public class Pedido {
    private int idPedido;
    private int idUsuario;
    private List<ItensPedido> itens;

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
        
    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }
      
 public void adicionarProduto(ItensPedido pItem){
        if(this.itens==null){
            this.itens = new ArrayList<ItensPedido>();
        }
        this.itens.add(pItem);
    }
    
    public void removerProduto(ItensPedido pedidoItemRemover){
        for(Iterator it = itens.iterator(); it.hasNext();){
            ItensPedido pItem = (ItensPedido) it.next();
            if (pItem.getProduto().getIdProduto()== pedidoItemRemover.getProduto().getIdProduto()) {
                it.remove();
            }
        }
    }
  
}
