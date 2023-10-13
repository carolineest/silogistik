package apap.ti.silogistik2106751915.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106751915.model.PermintaanPengiriman;

import java.math.BigInteger;
import java.util.List;

@Service
public class PermintaanPengirimanServiceImpl implements PermintaanPengirimanService {
    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;

    @Override
    public List<PermintaanPengiriman> getAllPermintaanPengiriman() { return permintaanPengirimanDb.findAll(); };

    @Override
    public PermintaanPengiriman getPermintaanPengirimanById(BigInteger idPermintaanPengiriman) {
        for (PermintaanPengiriman permintaanPengiriman : getAllPermintaanPengiriman()) {
            if (permintaanPengiriman.getIdPermintaanPengiriman().equals(idPermintaanPengiriman)) {
                return permintaanPengiriman;
            }
        }
        return null;
    } 

    @Override
    public PermintaanPengiriman createPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman) {
        return permintaanPengirimanDb.save(permintaanPengiriman);
    }
}
