/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Hype
 */
public class Polis {
     // ===== ATRIBUT =====
    private String idPolis;
    private Nasabah nasabah;      // Relasi ke Nasabah
    private Asuransi.Asuransi asuransi;    // Relasi ke Asuransi
    private String tanggalMulai;
    private String tanggalAkhir;

    // ===== CONSTRUCTOR =====
    public Polis(String idPolis, Nasabah nasabah, Asuransi.Asuransi asuransi,
                 String tanggalMulai, String tanggalAkhir) {
        this.idPolis = idPolis;
        this.nasabah = nasabah;
        this.asuransi = asuransi;
        this.tanggalMulai = tanggalMulai;
        this.tanggalAkhir = tanggalAkhir;
    }

    // ===== GETTER & SETTER =====
    public String getIdPolis() {
        return idPolis;
    }

    public void setIdPolis(String idPolis) {
        this.idPolis = idPolis;
    }

    public Nasabah getNasabah() {
        return nasabah;
    }

    public void setNasabah(Nasabah nasabah) {
        this.nasabah = nasabah;
    }

    public Asuransi.Asuransi getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(Asuransi.Asuransi asuransi) {
        this.asuransi = asuransi;
    }

    public String getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(String tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public String getTanggalAkhir() {
        return tanggalAkhir;
    }

    public void setTanggalAkhir(String tanggalAkhir) {
        this.tanggalAkhir = tanggalAkhir;
    }

    // ===== METHOD TAMBAHAN =====
    public boolean isAktif() {
        return asuransi.getStatus().toString().equals("AKTIF");
    }

    @Override
    public String toString() {
        return idPolis + " | " +
               nasabah.getNama() + " | " +
               asuransi.getNamaAsuransi() + " | " +
               asuransi.getDetailAsuransi();
    }
}
