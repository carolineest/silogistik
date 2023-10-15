package apap.ti.silogistik2106751915.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.repository.BarangDb;
import apap.ti.silogistik2106751915.repository.PermintaanPengirimanBarangDb;
import apap.ti.silogistik2106751915.repository.PermintaanPengirimanDb;
import jakarta.transaction.Transactional;
import apap.ti.silogistik2106751915.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.PermintaanPengiriman;
import apap.ti.silogistik2106751915.model.PermintaanPengirimanBarang;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Autowired
    PermintaanPengirimanBarangDb permintaanPengirimanBarangDb;

    @Autowired
    BarangDb barangDb;

    @Autowired
    KaryawanService karyawanService;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findAll(); };

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(BigInteger idPermintaanPengiriman) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getIdPermintaanPengiriman().equals(idPermintaanPengiriman)) {
                return permintaanPengiriman;
            }
        }
        return null;
    } 

    @Override
    public PermintaanPengiriman createPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        return permintaanPengirimanDb.save(permintaanPengiriman);
    }

    @Override
    public String generateNomorPengiriman(int jumlahBarang, int jenisLayanan, LocalDateTime waktuPermintaan) {
        StringBuilder nomorPengiriman = new StringBuilder("REQ");

        String jumlahBarangStr = String.valueOf(jumlahBarang % 100); 
        if (jumlahBarangStr.length() == 1) {
            nomorPengiriman.append("0");
        }
        nomorPengiriman.append(jumlahBarangStr);

        switch (jenisLayanan) {
            case 1:
                nomorPengiriman.append("SAM");
                break;
            case 2:
                nomorPengiriman.append("KIL");
                break;
            case 3:
                nomorPengiriman.append("REG");
                break;
            case 4:
                nomorPengiriman.append("HEM");
                break;
            default:
                throw new IllegalArgumentException("Invalid jenis layanan");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String timeString = waktuPermintaan.format(formatter);
        nomorPengiriman.append(timeString.substring(0, Math.min(timeString.length(), 8)));

        return nomorPengiriman.toString();
    }

    @Transactional
    @Override
    public void updatePermintaanPengiriman(PermintaanPengiriman existingPermintaanPengiriman, CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO){
        existingPermintaanPengiriman.setNamaPenerima(createPermintaanPengirimanRequestDTO.getNamaPenerima());
        existingPermintaanPengiriman.setAlamatPenerima(createPermintaanPengirimanRequestDTO.getAlamatPenerima());
        existingPermintaanPengiriman.setJenisLayanan(createPermintaanPengirimanRequestDTO.getJenisLayanan());
        existingPermintaanPengiriman.setBiayaPengiriman(createPermintaanPengirimanRequestDTO.getBiayaPengiriman());
        existingPermintaanPengiriman.setTanggalPengiriman(createPermintaanPengirimanRequestDTO.getTanggalPengiriman());
        existingPermintaanPengiriman.setWaktuPermintaan(LocalDateTime.now());
        existingPermintaanPengiriman.setKaryawan(createPermintaanPengirimanRequestDTO.getKaryawan());

        if (existingPermintaanPengiriman.getPermintaanPengirimanBarang() == null) {
            existingPermintaanPengiriman.setPermintaanPengirimanBarang(new ArrayList<>());
        }

        int totalBarang = 0; 
    
        for (CreatePermintaanPengirimanRequestDTO.BarangPermintaanDTO elem : createPermintaanPengirimanRequestDTO.getListBarang()) {
            Optional<Barang> optBarang = barangDb.findById(elem.getSku());
            Barang barang = optBarang.get();
            PermintaanPengirimanBarang permintaanPengirimanBarang = new PermintaanPengirimanBarang();
            permintaanPengirimanBarang.setIdPermintaanPengiriman(existingPermintaanPengiriman);
            permintaanPengirimanBarang.setSkuBarang(barang);
            permintaanPengirimanBarang.setKuantitasPesanan(elem.getStok());
            barang.getPermintaanPengirimanBarang().add(permintaanPengirimanBarang);
            existingPermintaanPengiriman.getPermintaanPengirimanBarang().add(permintaanPengirimanBarang);
            permintaanPengirimanBarangDb.save(permintaanPengirimanBarang);
            totalBarang += elem.getStok(); 
        }

        existingPermintaanPengiriman.setNomorPengiriman(generateNomorPengiriman(totalBarang, existingPermintaanPengiriman.getJenisLayanan(), existingPermintaanPengiriman.getWaktuPermintaan()));

        permintaanPengirimanDb.save(existingPermintaanPengiriman);
    }

    @Transactional
    @Override
    public void cancelPermintaan(PermintaanPengiriman permintaanPengiriman) {
        permintaanPengiriman.setIsCancelled(true);
    }
}
