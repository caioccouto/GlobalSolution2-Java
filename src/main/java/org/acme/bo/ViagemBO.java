package org.acme.bo;

import org.acme.dao.CombustivelDAO;
import org.acme.dao.ViagemDAO;
import org.acme.domain.AlertaAmbiental;
import org.acme.domain.Combustivel;
import org.acme.domain.Emissao;
import org.acme.domain.Viagem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ViagemBO {

    // Média de km por litro adotada para cálculo acadêmico
    private static final double MEDIA_KM_POR_LITRO = 8.0;

    // Limites de CO2 (kg) para classificação de impacto
    private static final double LIMITE_BAIXO_IMPACTO = 50.0;
    private static final double LIMITE_MEDIO_IMPACTO = 200.0;

    // Limite de emissão que gera alerta por viagem (kg CO2)
    private static final double LIMITE_ALERTA_VIAGEM = 300.0;

    private final ViagemDAO viagemDAO = new ViagemDAO();
    private final CombustivelDAO combustivelDAO = new CombustivelDAO();
    private final EmissaoBO emissaoBO = new EmissaoBO();
    private final AlertaAmbientalBO alertaBO = new AlertaAmbientalBO();

    public List<Viagem> listarBo() throws SQLException, ClassNotFoundException {
        return viagemDAO.selecionar();
    }

    public Viagem buscarPorIdBo(Long id) throws SQLException, ClassNotFoundException {
        return viagemDAO.buscarPorId(id);
    }

    public void inserirBo(Viagem v) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarDataViagem(v.getDataViagem());
        ValidacaoBO.validarCarga(v.getCargaTransportadaKg());
        ValidacaoBO.validarDistancia(v.getDistanciaPercorridaKm());

        viagemDAO.inserir(v);

        // Busca fator de emissão do combustível usado
        Combustivel combustivel = combustivelDAO.buscarPorId(v.getCombustivelId());
        if (combustivel == null) throw new IllegalArgumentException("Combustível não encontrado!");

        // RN01 — Cálculo de consumo estimado
        double consumoLitros = v.getDistanciaPercorridaKm() / MEDIA_KM_POR_LITRO;

        // RN02 — Cálculo de CO2 emitido
        double co2Emitido = consumoLitros * combustivel.getFatorEmissaoCarbono();

        // RN03 — Índice de impacto ambiental
        String indice;
        if (co2Emitido <= LIMITE_BAIXO_IMPACTO) {
            indice = "Baixo Impacto";
        } else if (co2Emitido <= LIMITE_MEDIO_IMPACTO) {
            indice = "Médio Impacto";
        } else {
            indice = "Alto Impacto";
        }

        // Passa pela EmissaoBO antes de persistir
        Emissao emissao = new Emissao(v.getId(), consumoLitros, co2Emitido, indice);
        emissaoBO.inserirBo(emissao);

        // RN04 — Geração de alertas automáticos, passando pelo AlertaAmbientalBO
        gerarAlertaSeNecessario(v, co2Emitido);
    }

    public void atualizarBo(Viagem v) throws SQLException, ClassNotFoundException {
        ValidacaoBO.validarDataViagem(v.getDataViagem());
        ValidacaoBO.validarCarga(v.getCargaTransportadaKg());
        ValidacaoBO.validarDistancia(v.getDistanciaPercorridaKm());
        viagemDAO.atualizar(v);
    }

    public void deletarBo(Long id) throws SQLException, ClassNotFoundException {
        viagemDAO.deletar(id);
    }

    private void gerarAlertaSeNecessario(Viagem v, double co2Emitido) throws SQLException, ClassNotFoundException {
        if (co2Emitido > LIMITE_ALERTA_VIAGEM) {
            AlertaAmbiental alerta = new AlertaAmbiental(
                    "EMISSAO_ELEVADA",
                    String.format("Viagem ID %d gerou %.2f kg de CO2, acima do limite de %.0f kg.",
                            v.getId(), co2Emitido, LIMITE_ALERTA_VIAGEM),
                    "ALTO",
                    0L
            );
            alertaBO.inserirBo(alerta);
        }
    }
}