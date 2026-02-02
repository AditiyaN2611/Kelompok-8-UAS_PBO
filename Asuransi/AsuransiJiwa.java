package Asuransi;

import Enum.StatusAsuransi;



public class AsuransiJiwa extends Asuransi  {
     // ===== ATRIBUT KHUSUS =====
    private Double santunan;

    // ===== CONSTRUCTOR =====
    public AsuransiJiwa(String idAsuransi, String namaAsuransi,
                        Double premi, StatusAsuransi status,
                        Double santunan) {
        super(idAsuransi, namaAsuransi, premi, status);
        this.santunan = santunan;
    }

    // ===== GETTER & SETTER =====
    public Double getSantunan() {
        return santunan;
    }

    public void setSantunan(Double santunan) {
        this.santunan = santunan;
    }

    // ===== OVERRIDE ABSTRACT METHOD =====
    @Override
    public String getDetailAsuransi() {
        return "Santunan Jiwa: Rp " + santunan;
    }

}
