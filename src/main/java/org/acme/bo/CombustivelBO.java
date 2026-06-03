package org.acme.bo;

import org.acme.dao.CombustivelDAO;
import org.acme.domain.Combustivel;

import java.sql.SQLException;
import java.util.List;

public class CombustivelBO {

    private final CombustivelDAO combustivelDAO = new CombustivelDAO();

    public List<Combustivel> listarBo() throws SQLException, ClassNotFoundException {
        return combustivelDAO.selecionar();
    }

    public Combustivel buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return combustivelDAO.buscarPorId(id);
    }
}