package org.acme.dao;

import org.acme.conexao.ConexaoFactory;
import org.acme.domain.Rota;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RotaDAO {

    public void inserir(Rota r) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO ROTA (NOME, ORIGEM, DESTINO, DISTANCIA_KM, REGIAO, ORIGEM_LAT, ORIGEM_LON, DESTINO_LAT, DESTINO_LON) VALUES (?,?,?,?,?,?,?,?,?)";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, new String[]{"ID"})) {
            ps.setString(1, r.getNome());
            ps.setString(2, r.getOrigem());
            ps.setString(3, r.getDestino());
            ps.setDouble(4, r.getDistanciaKm());
            ps.setString(5, r.getRegiao());
            ps.setDouble(6, r.getOrigemLat());
            ps.setDouble(7, r.getOrigemLon());
            ps.setDouble(8, r.getDestinoLat());
            ps.setDouble(9, r.getDestinoLon());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getLong(1));
            }
        }
    }

    public void atualizar(Rota r) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE ROTA SET NOME=?, ORIGEM=?, DESTINO=?, DISTANCIA_KM=?, REGIAO=?, ORIGEM_LAT=?, ORIGEM_LON=?, DESTINO_LAT=?, DESTINO_LON=? WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getNome());
            ps.setString(2, r.getOrigem());
            ps.setString(3, r.getDestino());
            ps.setDouble(4, r.getDistanciaKm());
            ps.setString(5, r.getRegiao());
            ps.setDouble(6, r.getOrigemLat());
            ps.setDouble(7, r.getOrigemLon());
            ps.setDouble(8, r.getDestinoLat());
            ps.setDouble(9, r.getDestinoLon());
            ps.setLong(10, r.getId());
            ps.executeUpdate();
        }
    }

    public void deletar(Long id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM ROTA WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    public List<Rota> selecionar() throws SQLException, ClassNotFoundException {
        List<Rota> lista = new ArrayList<>();
        String sql = "SELECT * FROM ROTA ORDER BY ID";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        }
        return lista;
    }

    public Rota buscarPorId(Long id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM ROTA WHERE ID=?";

        try (Connection conn = ConexaoFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        }
        return null;
    }

    private Rota mapear(ResultSet rs) throws SQLException {
        Rota r = new Rota();
        r.setId(rs.getLong("ID"));
        r.setNome(rs.getString("NOME"));
        r.setOrigem(rs.getString("ORIGEM"));
        r.setDestino(rs.getString("DESTINO"));
        r.setDistanciaKm(rs.getDouble("DISTANCIA_KM"));
        r.setRegiao(rs.getString("REGIAO"));
        r.setOrigemLat(rs.getDouble("ORIGEM_LAT"));
        r.setOrigemLon(rs.getDouble("ORIGEM_LON"));
        r.setDestinoLat(rs.getDouble("DESTINO_LAT"));
        r.setDestinoLon(rs.getDouble("DESTINO_LON"));
        return r;
    }
}