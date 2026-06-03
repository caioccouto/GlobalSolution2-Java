package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Emissao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmissaoDAO {

    public void inserir(Emissao e) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO EMISSAO (VIAGEM_ID, CONSUMO_ESTIMADO_LITROS, CO2_EMITIDO_KG, INDICE_IMPACTO_AMBIENTAL) VALUES (?,?,?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setLong(1, e.getViagemId());
            ps.setDouble(2, e.getConsumoEstimadoLitros());
            ps.setDouble(3, e.getCo2EmitidoKg());
            ps.setString(4, e.getIndiceImpactoAmbiental());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) e.setId(rs.getLong(1));
            }
        }
    }

    public List<Emissao> selecionar() throws SQLException, ClassNotFoundException {
        List<Emissao> lista = new ArrayList<>();
        String sql = "SELECT * FROM EMISSAO ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Emissao buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM EMISSAO WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    public Emissao buscarPorViagemId(Long viagemId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM EMISSAO WHERE VIAGEM_ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, viagemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private Emissao mapear(ResultSet rs) throws SQLException {
        Emissao e = new Emissao();
        e.setId(rs.getLong("ID"));
        e.setViagemId(rs.getLong("VIAGEM_ID"));
        e.setConsumoEstimadoLitros(rs.getDouble("CONSUMO_ESTIMADO_LITROS"));
        e.setCo2EmitidoKg(rs.getDouble("CO2_EMITIDO_KG"));
        e.setIndiceImpactoAmbiental(rs.getString("INDICE_IMPACTO_AMBIENTAL"));
        return e;
    }
}