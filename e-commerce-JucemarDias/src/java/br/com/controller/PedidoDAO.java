package br.com.controller;

import br.com.conexao.Mysql;
import br.com.modelo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Jucemar Dias
 */
public class PedidoDAO {

    Connection conexao;

    public PedidoDAO() {
        conexao = Mysql.getConexaoMySQL();
    }

    public void inserir(Pedido objeto) throws SQLException {

        PreparedStatement ps = conexao.prepareStatement(
                "INSERT INTO pedido (idusuario) VALUES (?)");
        ps.setInt(1, objeto.getIdUsuario());;
        ps.execute();
        ps.close();
    }
}
