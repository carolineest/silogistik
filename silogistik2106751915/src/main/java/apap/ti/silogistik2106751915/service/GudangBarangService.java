package apap.ti.silogistik2106751915.service;

import apap.ti.silogistik2106751915.dto.request.RestockGudangRequestDTO;
import apap.ti.silogistik2106751915.model.Gudang;
import apap.ti.silogistik2106751915.model.GudangBarang;

import java.math.BigInteger;
import java.util.List;

public interface GudangBarangService {
    void updateGudangBarang(RestockGudangRequestDTO restockGudangRequestDTO, BigInteger idGudang);

    List<GudangBarang> getGudangBarangBySku(String sku);
}
