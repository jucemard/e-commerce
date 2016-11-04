package br.com.controller;

import br.com.conexao.Mysql;
import br.com.modelo.ItensPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jucemar Dias
 */
public class ItensPedidoDAO {
     Connection conexao;
    public class PedidoDAO {
    
     public PedidoDAO() {
         conexao = Mysql.getConexaoMySQL();
     }
    }
     
      public void inserir(ItensPedido objeto) throws SQLException {
        
        PreparedStatement ps = conexao.prepareStatement(
         "INSERT INTO itens_pedido (idpedido, idproduto, quantidade, valorunitario) VALUES (?,?,?,?)");
        ps.setInt(1,objeto.getPedido().getIdPedido());
        ps.setInt(2,objeto.getProduto().getIdProduto());
        ps.setInt(3, objeto.getQuantidade());
        ps.setDouble(4, objeto.getValorunitario());
        ps.execute();
        ps.close();
    }
}
