package aplikasiiasuransi;

import Service.NasabahService;
import Service.PolisService;
import GUI.Login;
import javax.swing.SwingUtilities;

public class AplikasiiAsuransi {

    
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {

            // Inisialisasi service
            NasabahService nasabahService = new NasabahService();
            PolisService polisService = new PolisService(nasabahService);

            // Tampilkan Login
            new Login(nasabahService, polisService);
        });
    }
}