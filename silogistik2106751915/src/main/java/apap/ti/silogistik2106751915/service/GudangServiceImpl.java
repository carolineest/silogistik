package apap.ti.silogistik2106751915.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.repository.GudangBarangDb;
import apap.ti.silogistik2106751915.repository.GudangDb;
import apap.ti.silogistik2106751915.model.Gudang;

import java.math.BigInteger;
import java.util.List;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void createGudang(Gudang gudang) { gudangDb.save(gudang); }

    @Override
    public List<Gudang> getAllGudang() { return gudangDb.findAll(); }

    @Override
    public Gudang getGudangById(BigInteger idGudang) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getIdGudang().equals(idGudang)) {
                return gudang;
            }
        }
        return null;
    }
}
