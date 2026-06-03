package org.acme.bo;

import org.acme.dao.CaminhaoDAO;
import org.acme.domain.Caminhao;

import java.sql.SQLException;
import java.util.List;

public class CaminhaoBO {

    private final CaminhaoDAO caminhaoDAO = new CaminhaoDAO();

    public List<Caminhao> listarBo() throws SQLException, ClassNotFoundException {
        return caminhaoDAO.selecionar();
    }

    public Caminhao buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return caminhaoDAO.buscarPorId(id);
    }

    public void inserirBo(Caminhao c) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarPlaca(c.getPlaca());
        ValidacaoBO.validarNome(c.getModelo());
        ValidacaoBO.validarAnoFabricacao(c.getAnoFabricacao());
        ValidacaoBO.validarCapacidadeCarga(c.getCapacidadeCarga());
        caminhaoDAO.inserir(c);
    }

    public void atualizarBo(Caminhao c) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarPlaca(c.getPlaca());
        ValidacaoBO.validarNome(c.getModelo());
        ValidacaoBO.validarAnoFabricacao(c.getAnoFabricacao());
        ValidacaoBO.validarCapacidadeCarga(c.getCapacidadeCarga());
        caminhaoDAO.atualizar(c);
    }

    public void deletarBo(Long id) throws SQLException, ClassNotFoundException {
        caminhaoDAO.deletar(id);
    }
}