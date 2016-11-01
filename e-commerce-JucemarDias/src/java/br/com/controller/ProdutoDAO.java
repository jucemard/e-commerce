package br.com.controller;

import br.com.conexao.Mysql;
import br.com.modelo.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jucemar Dias
 */
public class ProdutoDAO {

    Connection conexao;

    public ProdutoDAO() {
        conexao = Mysql.getConexaoMySQL();
    }

    public void inserir(Produto objeto) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement(
                "INSERT INTO produto (descricao, quantidade, valor) VALUES (?, ?, ?)");
        ps.setString(1, objeto.getDescricao());
        ps.setString(2, objeto.getQuantidade());
        ps.setDouble(3, objeto.getValor());
        ps.execute();
        ps.close();
    }

     public void remover(Produto objeto) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM produto WHERE idproduto = ?");
        ps.setInt(1, objeto.getIdProduto());
        ps.execute();
        ps.close();
        
    }
    
     public void atualizar(Produto objeto) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement(" UPDATE produto SET descricao = ?, quantidade = ?, valor = ?"
                + " WHERE idproduto = ? ");

        ps.setString(1, objeto.getDescricao());
        ps.setString(2,objeto.getQuantidade());
        ps.setDouble(3,objeto.getValor());
        ps.setInt(4, objeto.getIdProduto());
        ps.execute();
        ps.close();
    }
     
     public List<Produto> ConsultaGeral() throws SQLException{
        String sql = "SELECT * FROM produto";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Produto> produtos = new ArrayList<Produto>();

        while(rs.next()){
             Produto p = new Produto();
             p.setIdProduto(rs.getInt("idproduto"));
             p.setDescricao(rs.getString("descricao"));
             p.setQuantidade(rs.getString("quantidade"));
             p.setValor(rs.getDouble("valor"));
             produtos.add(p);
        }
        rs.close();
        return produtos;
    }
     
     public Produto pesquisarproduto(Produto p) throws SQLException{
        String sql = "SELECT * FROM produto WHERE idproduto = ?";
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setInt(1, p.getIdProduto());
        ResultSet result = ps.executeQuery();
        
        result.next();
        p.setIdProduto(result.getInt("idproduto"));
        p.setDescricao(result.getString("descricao"));
        p.setQuantidade(result.getString("quantidade"));
        p.setValor(result.getDouble("valor"));
        result.close();
        return p;
    }
}
