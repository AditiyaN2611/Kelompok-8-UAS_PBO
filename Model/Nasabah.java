package Model;

import Enum.JenisKelamin;

public class Nasabah {

    // ===== ATRIBUT =====
    private String idNasabah;
    private String nama;
    private Integer umur; // Wrapper Class
    private JenisKelamin jenisKelamin;
    private String nomorHp;
    private String alamat;

    // ===== CONSTRUCTOR 1  =====
    public Nasabah(
            String idNasabah,
            String nama,
            Integer umur,
            JenisKelamin jenisKelamin,
            String nomorHp,
            String alamat
    ) {
        this.idNasabah = idNasabah;
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.nomorHp = nomorHp;
        this.alamat = alamat;
    }

    // ===== CONSTRUCTOR 2 ) =====
    public Nasabah(String nama, JenisKelamin jk, String noHp, String alamat) {
        this.idNasabah = "TEMP";
        this.nama = nama;
        this.umur = 0;
        this.jenisKelamin = jk;
        this.nomorHp = noHp;
        this.alamat = alamat;
    }

    // ===== GETTER & SETTER =====
    public String getIdNasabah() { return idNasabah; }
    public String getNama() { return nama; }
    public Integer getUmur() { return umur; }
    public JenisKelamin getJenisKelamin() { return jenisKelamin; }
    public String getNomorHp() { return nomorHp; }
    public String getAlamat() { return alamat; }

    // ===== METHOD UPDATE =====
    public void updateData(
            String nama,
            Integer umur,
            JenisKelamin jenisKelamin,
            String nomorHp,
            String alamat
    ) {
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.nomorHp = nomorHp;
        this.alamat = alamat;
    }

    @Override
    public String toString() {
        return idNasabah + " | " + nama + " | " + umur + " | "
                + jenisKelamin + " | " + nomorHp + " | " + alamat;
    }
}
