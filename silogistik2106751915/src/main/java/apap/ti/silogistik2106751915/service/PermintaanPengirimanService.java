package apap.ti.silogistik2106751915.service;
import apap.ti.silogistik2106751915.model.PermintaanPengiriman;

import java.math.BigInteger;
import java.util.List;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(BigInteger idPermintaanPengiriman);

    PermintaanPengiriman createPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);
}
