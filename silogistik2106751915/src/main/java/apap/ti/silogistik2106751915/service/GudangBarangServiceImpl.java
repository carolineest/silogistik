package apap.ti.silogistik2106751915.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.dto.request.RestockGudangRequestDTO;
import apap.ti.silogistik2106751915.model.Gudang;
import apap.ti.silogistik2106751915.model.GudangBarang;
import apap.ti.silogistik2106751915.repository.GudangBarangDb;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void updateGudangBarang(RestockGudangRequestDTO restockGudangRequestDTO, BigInteger idGudang) {
        for (RestockGudangRequestDTO.BarangRestockDTO barang : restockGudangRequestDTO.getListBarang()) {
            String sku = barang.getSku();
            int stok = barang.getStok();

            Optional<GudangBarang> existingGudangBarang = gudangBarangDb.getByIdGudangAndSkuBarang(idGudang, sku);

            if (existingGudangBarang.isPresent()) {
                GudangBarang currGudangBarang = existingGudangBarang.get();
                currGudangBarang.setStok(currGudangBarang.getStok() + stok);
                gudangBarangDb.save(currGudangBarang);
            } else {
                GudangBarang gudangBarangTemp = new GudangBarang();
                gudangBarangTemp.getIdGudang().setIdGudang(idGudang);
                gudangBarangTemp.getSkuBarang().setSku(sku);
                gudangBarangTemp.setStok(stok);
                gudangBarangDb.save(gudangBarangTemp);
            }
        }
    }
}
