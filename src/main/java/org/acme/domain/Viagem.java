package org.acme.domain;

import java.time.LocalDate;

public class Viagem {
    private Long id;
    private LocalDate dtViagem;
    private Double cargaTransportadaKg;
    private Double distanciaPercorridaKm;
    private Long caminhaoId;
    private Long motoristaId;
    private Long rotaId;
    private Long combustivelId;

    public Viagem() {}

    public Viagem(LocalDate dtViagem, Double cargaTransportadaKg, Double distanciaPercorridaKm,
                  Long caminhaoId, Long motoristaId, Long rotaId, Long combustivelId) {
        this.dtViagem = dtViagem;
        this.cargaTransportadaKg = cargaTransportadaKg;
        this.distanciaPercorridaKm = distanciaPercorridaKm;
        this.caminhaoId = caminhaoId;
        this.motoristaId = motoristaId;
        this.rotaId = rotaId;
        this.combustivelId = combustivelId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDtViagem() { return dtViagem; }
    public void setDtViagem(LocalDate dtViagem) { this.dtViagem = dtViagem; }

    public Double getCargaTransportadaKg() { return cargaTransportadaKg; }
    public void setCargaTransportadaKg(Double cargaTransportadaKg) { this.cargaTransportadaKg = cargaTransportadaKg; }

    public Double getDistanciaPercorridaKm() { return distanciaPercorridaKm; }
    public void setDistanciaPercorridaKm(Double distanciaPercorridaKm) { this.distanciaPercorridaKm = distanciaPercorridaKm; }

    public Long getCaminhaoId() { return caminhaoId; }
    public void setCaminhaoId(Long caminhaoId) { this.caminhaoId = caminhaoId; }

    public Long getMotoristaId() { return motoristaId; }
    public void setMotoristaId(Long motoristaId) { this.motoristaId = motoristaId; }

    public Long getRotaId() { return rotaId; }
    public void setRotaId(Long rotaId) { this.rotaId = rotaId; }

    public Long getCombustivelId() { return combustivelId; }
    public void setCombustivelId(Long combustivelId) { this.combustivelId = combustivelId; }
}