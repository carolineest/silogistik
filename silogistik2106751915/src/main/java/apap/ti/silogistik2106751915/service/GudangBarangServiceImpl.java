package apap.ti.silogistik2106751915.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.dto.request.RestockGudangRequestDTO;
import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.Gudang;
import apap.ti.silogistik2106751915.model.GudangBarang;
import apap.ti.silogistik2106751915.repository.BarangDb;
import apap.ti.silogistik2106751915.repository.GudangBarangDb;
import apap.ti.silogistik2106751915.repository.GudangDb;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    GudangDb gudangDb;

    @Autowired
    BarangDb barangDb;

    @Override
    public void updateGudangBarang(RestockGudangRequestDTO restockGudangRequestDTO, BigInteger idGudang) {
        for (RestockGudangRequestDTO.BarangRestockDTO barang : restockGudangRequestDTO.getListBarang()) {
            String sku = barang.getSku();
            int stok = barang.getStok();

            Optional<Gudang> optionalGudang = gudangDb.findById(idGudang);
            Optional<Barang> optionalBarang = barangDb.findBySku(sku);

            if (optionalGudang.isPresent() && optionalBarang.isPresent()) {
                Gudang gudang = optionalGudang.get();
                Barang barangTemp = optionalBarang.get();

                Optional<GudangBarang> existingGudangBarang = gudangBarangDb.getByIdGudangAndSkuBarang(gudang, barangTemp);

                if (existingGudangBarang.isPresent()) {
                    GudangBarang currGudangBarang = existingGudangBarang.get();
                    currGudangBarang.setStok(currGudangBarang.getStok() + stok);
                    gudangBarangDb.save(currGudangBarang);

                     // Tambahkan gudangBarang ke list gudangBarang di gudang dan barang
                    gudang.getGudangBarang().add(currGudangBarang);
                    barangTemp.getGudangBarang().add(currGudangBarang);
                } else {
                    GudangBarang gudangBarangTemp = new GudangBarang();
                    gudangBarangTemp.setIdGudang(gudang);
                    gudangBarangTemp.setSkuBarang(barangTemp);
                    gudangBarangTemp.setStok(stok);
                    gudangBarangDb.save(gudangBarangTemp);

                     // Tambahkan gudangBarang ke list gudangBarang di gudang dan barang
                    gudang.getGudangBarang().add(gudangBarangTemp);
                    barangTemp.getGudangBarang().add(gudangBarangTemp);
                }
            } 
        }
    }

    @Override
    public List<GudangBarang> getGudangBarangBySku(String sku){
        List<GudangBarang> listGudangBarang = new ArrayList<>();
        for (GudangBarang gudangBarang : gudangBarangDb.findAll()) {
            if (gudangBarang.getSkuBarang().getSku().equals(sku)) {
                listGudangBarang.add(gudangBarang);
            }
        }
        return listGudangBarang;
    }
}
