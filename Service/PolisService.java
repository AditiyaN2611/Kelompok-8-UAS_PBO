package Service;

import Model.Nasabah;
import Asuransi.Asuransi;
import Asuransi.AsuransiJiwa;
import Enum.StatusAsuransi;
import Model.Polis;
import Asuransi.AsuransiKesehatan;
        

import java.io.*;
import java.util.ArrayList;


public class PolisService implements FileHandler {

    private ArrayList<Polis> dataPolis;
    private NasabahService nasabahService;
    private final String FILE_NAME = "polis.txt";

    // ===== CONSTRUCTOR =====
    public PolisService(NasabahService nasabahService) {
        this.nasabahService = nasabahService;
        dataPolis = new ArrayList<>();
        loadData();
    }

    // ===== TAMBAH POLIS JIWA =====
    public void tambahPolisJiwa(String idPolis, String idNasabah,
                                String idAsuransi, String namaAsuransi,
                                Double premi, Double santunan,
                                String tglMulai, String tglAkhir) {

        Nasabah nasabah = nasabahService.cariNasabahById(idNasabah);
        if (nasabah == null) return;

        Asuransi asuransi = new AsuransiJiwa(
                idAsuransi,
                namaAsuransi,
                premi,
                StatusAsuransi.AKTIF,
                santunan
        );

        Polis polis = new Polis(idPolis, nasabah, asuransi, tglMulai, tglAkhir);
        dataPolis.add(polis);
        saveData();
    }

    // ===== TAMBAH POLIS KESEHATAN =====
    public void tambahPolisKesehatan(String idPolis, String idNasabah,
                                     String idAsuransi, String namaAsuransi,
                                     Double premi, String jenisLayanan,
                                     String tglMulai, String tglAkhir) {

        Nasabah nasabah = nasabahService.cariNasabahById(idNasabah);
        if (nasabah == null) return;

        Asuransi asuransi = new AsuransiKesehatan(
                idAsuransi,
                namaAsuransi,
                premi,
                StatusAsuransi.AKTIF,
                jenisLayanan
        );

        Polis polis = new Polis(idPolis, nasabah, asuransi, tglMulai, tglAkhir);
        dataPolis.add(polis);
        saveData();
    }

    // ===== GET DATA =====
    public ArrayList<Polis> getAllPolis() {
        return dataPolis;
    }

    public Polis cariPolisById(String id) {
        for (Polis p : dataPolis) {
            if (p.getIdPolis().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    // ===== FILE HANDLING =====
    @Override
    public void loadData() {
       dataPolis.clear();

    File file = new File(FILE_NAME);
    if (!file.exists()) {
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;

        while ((line = br.readLine()) != null) {

            // Skip baris kosong
            if (line.trim().isEmpty()) {
                continue;
            }

            String[] d = line.split(";");

            // Validasi jumlah kolom
            if (d.length < 10) {
                System.out.println("Data polis tidak lengkap: " + line);
                continue;
            }

            // ===== AMBIL NASABAH =====
            Nasabah nasabah = nasabahService.cariNasabahById(d[1]);
            if (nasabah == null) {
                System.out.println("Nasabah tidak ditemukan: " + d[1]);
                continue;
            }

            // ===== BUAT ASURANSI =====
            Asuransi asuransi;

            if (d[2].equalsIgnoreCase("JIWA")) {

                double santunan = 0;
                try {
                    santunan = Double.parseDouble(d[6]);
                } catch (NumberFormatException e) {
                    System.out.println("Santunan invalid: " + d[6]);
                }

                asuransi = new AsuransiJiwa(
                        d[3],                 // idAsuransi
                        d[4],                 // namaAsuransi
                        Double.parseDouble(d[5]), // premi
                        StatusAsuransi.valueOf(d[7]),
                        santunan
                );

            } else if (d[2].equalsIgnoreCase("KESEHATAN")) {

                String jenisLayanan =
                        (d[6] == null || d[6].trim().isEmpty())
                                ? "Tidak Diketahui"
                                : d[6];

                asuransi = new AsuransiKesehatan(
                        d[3],
                        d[4],
                        Double.parseDouble(d[5]),
                        StatusAsuransi.valueOf(d[7]),
                        jenisLayanan
                );

            } else {
                System.out.println("Jenis asuransi tidak dikenal: " + d[2]);
                continue;
            }

            // ===== BUAT POLIS =====
            Polis polis = new Polis(
                    d[0],       // idPolis
                    nasabah,
                    asuransi,
                    d[8],       // tanggalMulai
                    d[9]        // tanggalAkhir
            );

            dataPolis.add(polis);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Polis p : dataPolis) {
                Asuransi a = p.getAsuransi();

                String jenisAsuransi = (a instanceof AsuransiJiwa)
                        ? "JIWA"
                        : "KESEHATAN";

                String detail = (a instanceof AsuransiJiwa)
                        ? ((AsuransiJiwa) a).getSantunan().toString()
                        : ((AsuransiKesehatan) a).getJenisLayanan();

                bw.write(
                        p.getIdPolis() + "|" +
                        p.getNasabah().getIdNasabah() + "|" +
                        jenisAsuransi + "|" +
                        a.getIdAsuransi() + "|" +
                        a.getNamaAsuransi() + "|" +
                        a.getPremi() + "|" +
                        detail + "|" +
                        a.getStatus() + "|" +
                        p.getTanggalMulai() + "|" +
                        p.getTanggalAkhir()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
