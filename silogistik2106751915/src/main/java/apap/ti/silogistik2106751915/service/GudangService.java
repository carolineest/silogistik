package apap.ti.silogistik2106751915.service;
import apap.ti.silogistik2106751915.model.Gudang;

import java.math.BigInteger;
import java.util.List;

public interface GudangService {
    void createGudang(Gudang gudang);

    List<Gudang> getAllGudang();

    Gudang getGudangById(BigInteger idGudang);
}
