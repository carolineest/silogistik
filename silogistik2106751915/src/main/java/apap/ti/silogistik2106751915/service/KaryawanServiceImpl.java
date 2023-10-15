package apap.ti.silogistik2106751915.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.repository.KaryawanDb;
import apap.ti.silogistik2106751915.model.Karyawan;

import java.math.BigInteger;
import java.util.List;

@Service
public class KaryawanServiceImpl implements KaryawanService {
    @Autowired
    KaryawanDb karyawanDb;

    @Override
    public void createKaryawan(Karyawan karyawan) { karyawanDb.save(karyawan); }

    @Override
    public List<Karyawan> getAllKaryawan() { return karyawanDb.findAll(); };

    @Override
    public Karyawan getKaryawanById(BigInteger idKaryawan) {
        for (Karyawan karyawan : getAllKaryawan()) {
            if (karyawan.getIdKaryawan().equals(idKaryawan)) {
                return karyawan;
            }
        }
        return null;
    }
}
