package apap.ti.silogistik2106751915.service;

import apap.ti.silogistik2106751915.dto.request.RestockGudangRequestDTO;
import apap.ti.silogistik2106751915.model.Gudang;

import java.math.BigInteger;

public interface GudangBarangService {
    void updateGudangBarang(RestockGudangRequestDTO restockGudangRequestDTO, BigInteger idGudang);
}
