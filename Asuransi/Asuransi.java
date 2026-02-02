package Asuransi;

import Enum.StatusAsuransi;

public abstract class Asuransi {
     // ===== ATRIBUT =====
    private String idAsuransi;
    private String namaAsuransi;
    private Double premi;
    private StatusAsuransi status;

    // ===== CONSTRUCTOR =====
    public Asuransi(String idAsuransi, String namaAsuransi,
                    Double premi, StatusAsuransi status) {
        this.idAsuransi = idAsuransi;
        this.namaAsuransi = namaAsuransi;
        this.premi = premi;
        this.status = status;
    }

    // ===== GETTER & SETTER =====
    public String getIdAsuransi() {
        return idAsuransi;
    }

    public void setIdAsuransi(String idAsuransi) {
        this.idAsuransi = idAsuransi;
    }

    public String getNamaAsuransi() {
        return namaAsuransi;
    }

    public void setNamaAsuransi(String namaAsuransi) {
        this.namaAsuransi = namaAsuransi;
    }

    public Double getPremi() {
        return premi;
    }

    public void setPremi(Double premi) {
        this.premi = premi;
    }

    public StatusAsuransi getStatus() {
        return status;
    }

    public void setStatus(StatusAsuransi status) {
        this.status = status;
    }

    // ===== ABSTRACT METHOD =====
    public abstract String getDetailAsuransi();

    // ===== METHOD TAMBAHAN =====
    public void aktifkan() {
        this.status = StatusAsuransi.AKTIF;
    }

    public void nonaktifkan() {
        this.status = StatusAsuransi.NONAKTIF;
    }
}
