package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Combustivel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CombustivelDAO {

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