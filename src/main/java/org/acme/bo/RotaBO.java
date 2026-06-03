package org.acme.bo;

import org.acme.dao.RotaDAO;
import org.acme.domain.Rota;

import java.sql.SQLException;
import java.util.List;

public class RotaBO {

    private final RotaDAO rotaDAO = new RotaDAO();

    public List<Rota> listarBo() throws SQLException, ClassNotFoundException {
        return rotaDAO.selecionar();
    }

    public Rota buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return rotaDAO.buscarPorId(id);
    }

    public void inserirBo(Rota r) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarNome(r.getNome());
        ValidacaoBO.validarNome(r.getOrigem());
        ValidacaoBO.validarNome(r.getDestino());
        ValidacaoBO.validarDistancia(r.getDistanciaKm());
        ValidacaoBO.validarCoordenadas(r.getOrigemLat(), r.getOrigemLon(), "origem");
        ValidacaoBO.validarCoordenadas(r.getDestinoLat(), r.getDestinoLon(), "destino");
        rotaDAO.inserir(r);
    }

    public void atualizarBo(Rota r) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarNome(r.getNome());
        ValidacaoBO.validarNome(r.getOrigem());
        ValidacaoBO.validarNome(r.getDestino());
        ValidacaoBO.validarDistancia(r.getDistanciaKm());
        ValidacaoBO.validarCoordenadas(r.getOrigemLat(), r.getOrigemLon(), "origem");
        ValidacaoBO.validarCoordenadas(r.getDestinoLat(), r.getDestinoLon(), "destino");
        rotaDAO.atualizar(r);
    }

    public void deletarBo(Long id) throws SQLException, ClassNotFoundException {
        rotaDAO.deletar(id);
    }
}