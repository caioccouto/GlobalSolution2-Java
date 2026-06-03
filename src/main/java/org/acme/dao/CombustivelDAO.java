package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Combustivel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CombustivelDAO {

    public void inserir(Combustivel c) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO COMBUSTIVEL (NOME, FATOR_EMISSAO_CARBONO) VALUES (?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, c.getNome());
            ps.setDouble(2, c.getFatorEmissaoCarbono());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) c.setId(rs.getLong(1));
            }
        }
    }

    public void atualizar(Combustivel c) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE COMBUSTIVEL SET NOME=?, FATOR_EMISSAO_CARBONO=? WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNome());
            ps.setDouble(2, c.getFatorEmissaoCarbono());
            ps.setLong(3, c.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM COMBUSTIVEL WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Combustivel> selecionar() throws SQLException, ClassNotFoundException {
        List<Combustivel> lista = new ArrayList<>();
        String sql = "SELECT * FROM COMBUSTIVEL ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Combustivel buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM COMBUSTIVEL WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private Combustivel mapear(ResultSet rs) throws SQLException {
        Combustivel c = new Combustivel();
        c.setId(rs.getLong("ID"));
        c.setNome(rs.getString("NOME"));
        c.setFatorEmissaoCarbono(rs.getDouble("FATOR_EMISSAO_CARBONO"));
        return c;
    }
}