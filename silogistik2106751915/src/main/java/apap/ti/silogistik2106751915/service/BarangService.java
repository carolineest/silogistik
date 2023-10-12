package apap.ti.silogistik2106751915.service;
import apap.ti.silogistik2106751915.model.Barang;

import java.util.List;

public interface BarangService {
    Barang createBarang(Barang barang);

    List<Barang> getAllBarang();

    Barang getBarangBySku(String sku);

    String generateNewSku(int tipeBarang);

    Barang updateBarang(Barang barang);
}
