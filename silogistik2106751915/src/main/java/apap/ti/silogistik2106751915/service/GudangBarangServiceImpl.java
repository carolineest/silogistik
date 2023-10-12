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
import java.util.Optional;

@Service
public class GudangBarangServiceImpl implements GudangBarangService {
    @Autowired
    GudangBarangDb gudangBarangDb;

    @Autowired
    GudangDb gudangDb;

    @Autowired
    BarangDb barangDb;

    // @Override
    // public void updateGudangBarang(RestockGudangRequestDTO restockGudangRequestDTO, BigInteger idGudang) {
    //     for (RestockGudangRequestDTO.BarangRestockDTO barang : restockGudangRequestDTO.getListBarang()) {
    //         String sku = barang.getSku();
    //         int stok = barang.getStok();

    //         Gudang gudangTemp = new Gudang();
    //         Barang barangTemp = new Barang();
            
    //         for (Gudang elemGudang : gudangDb.findAll()) {
    //             if (elemGudang.getIdGudang().equals(idGudang)) {
    //                 gudangTemp = elemGudang;
    //                 break;
    //             } 
    //         }

    //         for (Barang elemBarang : barangDb.findAll()) {
    //             if (elemBarang.getSku().equals(sku)) {
    //                 barangTemp = elemBarang;
    //                 break;
    //             } 
    //         }
                
    //         Optional<GudangBarang> existingGudangBarang = gudangBarangDb.getByIdGudangAndSkuBarang(gudangTemp, barangTemp);

    //         if (existingGudangBarang.isPresent()) {
    //             GudangBarang currGudangBarang = existingGudangBarang.get();
    //             currGudangBarang.setStok(currGudangBarang.getStok() + stok);
    //             gudangBarangDb.save(currGudangBarang);
    //         } else {
    //             Optional<Gudang> optionalGudang = gudangDb.findById(idGudang);
    //             Optional<Barang> optionalBarang = barangDb.findBySku(sku);

    //             Gudang gudangSem = optionalGudang.get();
    //             Barang barangSem = optionalBarang.get();

    //             GudangBarang gudangBarangTemp = new GudangBarang();
    //             gudangBarangTemp.setIdGudang(idGudang);
    //             gudangBarangTemp.setSku(sku);
    //             gudangBarangTemp.setStok(stok);
    //             gudangBarangDb.save(gudangBarangTemp);
    //         }
    //     }
    // }

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
                } else {
                    GudangBarang gudangBarangTemp = new GudangBarang();
                    gudangBarangTemp.setIdGudang(gudang);
                    gudangBarangTemp.setSkuBarang(barangTemp);
                    gudangBarangTemp.setStok(stok);
                    gudangBarangDb.save(gudangBarangTemp);
                }
            } else {
                // Handle kasus di mana Gudang atau Barang tidak ditemukan
                // Misalnya, menampilkan pesan error atau tindakan lain yang sesuai
            }
        }
    }
}
