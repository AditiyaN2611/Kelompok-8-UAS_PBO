/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Asuransi;

import Enum.StatusAsuransi;

public class AsuransiKesehatan extends Asuransi {
    // ===== ATRIBUT KHUSUS =====
    private String jenisLayanan;

    // ===== CONSTRUCTOR =====
    public AsuransiKesehatan(String idAsuransi, String namaAsuransi,
                             Double premi, StatusAsuransi status,
                             String jenisLayanan) {
        super(idAsuransi, namaAsuransi, premi, status);
        this.jenisLayanan = jenisLayanan;
    }

    // ===== GETTER & SETTER =====
    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    // ===== OVERRIDE ABSTRACT METHOD =====
    @Override
    public String getDetailAsuransi() {
        return "Layanan Kesehatan: " + jenisLayanan;
    }
}
