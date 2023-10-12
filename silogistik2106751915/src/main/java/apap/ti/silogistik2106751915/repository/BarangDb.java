package apap.ti.silogistik2106751915.repository;
import apap.ti.silogistik2106751915.model.Barang;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface BarangDb extends JpaRepository<Barang, String>{
    Barang findTopBySkuStartingWithOrderBySkuDesc(String prefix);
}
