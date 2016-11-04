package br.com.controller;

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
public abstract class DAO<T> {

    // conexão protected para acessar nas heranças
    protected Connection conexao;

    public DAO(Connection conexao) {
        this.conexao = conexao;
    }

    public abstract void inserir(T objeto) throws SQLException;

    public abstract void remover(T objeto) throws SQLException;

    public abstract void atualizar(T objeto) throws SQLException;

    public abstract T consultaId(int... ids) throws SQLException;

    /**
     * Método genérico para executar comandos no banco de dados. (INSERT,
     * DELETE, UPDATE, ALTER TABLE, CREATE TABLE,... )
     *
     * @param sql
     * @param parametros
     * @throws SQLException
     */
    public void executaSql(String sql, Object... parametros) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement(sql);
        if (parametros != null) {
            for (int i = 0, j = 1; i < parametros.length; i++, j++) {
                Object obj = parametros[i];
                ps.setObject(j, obj);
            }
        }
        ps.execute();
        ps.close();
    }

    
    public List<T> consultaSql(String sql, Object... parametros) throws SQLException {
        PreparedStatement ps = conexao.prepareStatement(sql);
        if (parametros != null) {
            for (int i = 0, j = 1; i < parametros.length; i++, j++) {
                Object obj = parametros[i];
                ps.setObject(j, obj);
            }
        }
        ResultSet resultado = ps.executeQuery();
        List<T> objetos = new ArrayList<T>();

        while (resultado.next()) {
            T objeto = montaObjeto(resultado);
            objetos.add(objeto);
        }

        return objetos;
    }

    public abstract T montaObjeto(ResultSet resultado) throws SQLException;
}
