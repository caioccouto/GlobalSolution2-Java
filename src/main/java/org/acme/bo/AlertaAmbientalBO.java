package org.acme.bo;

import org.acme.dao.AlertaAmbientalDAO;
import org.acme.domain.AlertaAmbiental;

import java.sql.SQLException;
import java.util.List;

public class AlertaAmbientalBO {

    private final AlertaAmbientalDAO alertaDAO = new AlertaAmbientalDAO();

    public List<AlertaAmbiental> listarBo() throws SQLException, ClassNotFoundException {
        return alertaDAO.selecionar();
    }

    public AlertaAmbiental buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return alertaDAO.buscarPorId(id);
    }

    public void inserirBo(AlertaAmbiental a) throws SQLException, ClassNotFoundException {
        if (a.getTipo() == null || a.getTipo().trim().isEmpty()) {
            throw new IllegalArgumentException("Tipo do alerta não pode ser nulo!");
        }
        if (a.getDescricao() == null || a.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do alerta não pode ser nula!");
        }
        if (a.getNivel() == null || a.getNivel().trim().isEmpty()) {
            throw new IllegalArgumentException("Nível do alerta não pode ser nulo!");
        }
        alertaDAO.inserir(a);
    }
}