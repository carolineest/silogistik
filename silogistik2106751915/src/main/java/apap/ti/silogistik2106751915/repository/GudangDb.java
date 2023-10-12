package apap.ti.silogistik2106751915.repository;
import apap.ti.silogistik2106751915.model.Gudang;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GudangDb extends JpaRepository<Gudang, BigInteger> {
    
}
