package apap.ti.silogistik2106751915.repository;
import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.Gudang;
import apap.ti.silogistik2106751915.model.GudangBarang;

import java.math.BigInteger;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GudangBarangDb extends JpaRepository<GudangBarang, BigInteger> {
    Optional<GudangBarang> getByIdGudangAndSkuBarang(Gudang idGudang, Barang skuBarang);

    List<GudangBarang> getBySkuBarang(Barang skuBarang);

    // Optional<GudangBarang> getByIdGudangAndSkuBarang(BigInteger idGudang, String sku);
}
