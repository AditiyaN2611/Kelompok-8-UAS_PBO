package Service;

import Model.Nasabah;
import Enum.JenisKelamin;

import java.io.*;
import java.util.ArrayList;

public class NasabahService implements FileHandler {

    private ArrayList<Nasabah> dataNasabah;
    private final String FILE_NAME = "nasabah.txt";

    // ===== CONSTRUCTOR =====
    public NasabahService() {
        dataNasabah = new ArrayList<>();
        loadData();
    }

    // ===== TAMBAH NASABAH =====
    public void tambahNasabah(
            String nama,
            int umur,
            JenisKelamin jenisKelamin,
            String nomorHp,
            String alamat
    ) {
        String id = generateId();

        Nasabah nasabah = new Nasabah(
                id,
                nama,
                umur,
                jenisKelamin,
                nomorHp,
                alamat
        );

        dataNasabah.add(nasabah);
        saveData();
    }

    public ArrayList<Nasabah> cariByNama(String nama) {
    ArrayList<Nasabah> hasil = new ArrayList<>();

        for (Nasabah n : dataNasabah) {
        if (n.getNama().toLowerCase().contains(nama.toLowerCase())) {
            hasil.add(n);
        }
    }
    return hasil;
    }
    
    // ===== AMBIL SEMUA DATA =====
    public ArrayList<Nasabah> getAllNasabah() {
        return dataNasabah;
    }

    // ===== CARI =====
    public Nasabah cariNasabahById(String id) {
        for (Nasabah n : dataNasabah) {
            if (n.getIdNasabah().equalsIgnoreCase(id)) {
                return n;
            }
        }
        return null;
    }

    // ===== UPDATE =====
    public void updateNasabah(String id, Nasabah dataBaru) {
        Nasabah n = cariNasabahById(id);
        if (n != null) {
            n.updateData(
                    dataBaru.getNama(),
                    dataBaru.getUmur(),
                    dataBaru.getJenisKelamin(),
                    dataBaru.getNomorHp(),
                    dataBaru.getAlamat()
            );
            saveData();
        }
    }

    // ===== HAPUS =====
    public void hapusNasabah(String id) {
        Nasabah n = cariNasabahById(id);
        if (n != null) {
            dataNasabah.remove(n);
            saveData();
        }
    }
    
    public Nasabah cariById(String id) {
    for (Nasabah n : dataNasabah) {
        if (n.getIdNasabah().equalsIgnoreCase(id)) {
            return n;
        }
    }
    return null;
    }
    
    

    // ===== GENERATE ID =====
    private String generateId() {
        if (dataNasabah.isEmpty()) {
            return "NSB1";
        }

        Nasabah last = dataNasabah.get(dataNasabah.size() - 1);
        String lastId = last.getIdNasabah().replace("NSB", "");
        int nextId = Integer.parseInt(lastId) + 1;

        return "NSB" + nextId;
    }

    // ===== LOAD FILE =====
    @Override
    public void loadData() {
        dataNasabah.clear();

        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");

                Nasabah n = new Nasabah(
                        data[0],
                        data[1],
                        Integer.parseInt(data[2]),
                        JenisKelamin.valueOf(data[3]),
                        data[4],
                        data[5]
                );

                dataNasabah.add(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ===== SAVE FILE =====
    @Override
    public void saveData() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Nasabah n : dataNasabah) {
                bw.write(
                        n.getIdNasabah() + "|" +
                        n.getNama() + "|" +
                        n.getUmur() + "|" +
                        n.getJenisKelamin() + "|" +
                        n.getNomorHp() + "|" +
                        n.getAlamat()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
