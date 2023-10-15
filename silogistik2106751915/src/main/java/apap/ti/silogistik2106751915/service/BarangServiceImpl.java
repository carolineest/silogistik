package apap.ti.silogistik2106751915.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.repository.BarangDb;
import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.GudangBarang;

import java.util.List;

@Service
public class BarangServiceImpl implements BarangService {
    @Autowired
    BarangDb barangDb;

    @Override
    public List<Barang> getAllBarang() { 
        for (Barang barang : barangDb.findAll()) {
            barang.setTotalStok(countStok(barang));
        }
        return barangDb.findAll(); 
    }

    @Override
    public int countStok(Barang barang) {
        int total = 0;
        for (GudangBarang gudangBarang : barang.getGudangBarang()) {
            total += gudangBarang.getStok();
        }
        return total;
    }

    @Override
    public Barang getBarangBySku(String sku) {
        for (Barang barang : barangDb.findAll()) {
            if (barang.getSku().equals(sku)) {
                return barang;
            }
        }
        return null;
    }

    @Override
    public String generateNewSku(int tipeBarang) {
        String prefix;
        switch (tipeBarang) {
            case 1: prefix = "ELEC"; break;
            case 2: prefix = "CLOT"; break;
            case 3: prefix = "FOOD"; break;
            case 4: prefix = "COSM"; break;
            case 5: prefix = "TOOL"; break;
            default: throw new IllegalArgumentException("Invalid tipeBarang");
        }

        Barang latestBarang = barangDb.findTopBySkuStartingWithOrderBySkuDesc(prefix);
        if (latestBarang == null) {
            return prefix + "001";
        } else {
            String latestSku = latestBarang.getSku();
            int latestNumber = Integer.parseInt(latestSku.substring(4));
            int newNumber = latestNumber + 1;
            return prefix + String.format("%03d", newNumber);
        }
    }

    @Override
    public Barang createBarang(Barang barang) {
        barang.setSku(generateNewSku(barang.getTipeBarang()));
        return barangDb.save(barang);
    }

   @Override
   public Barang updateBarang(Barang barangFromDTO) {
        Barang barang = getBarangBySku(barangFromDTO.getSku());

        if (barang != null) {
            barang.setMerk(barangFromDTO.getMerk());
            barang.setHargaBarang(barangFromDTO.getHargaBarang());
            barangDb.save(barang);
        }
        return barang;
   }
}
