package apap.ti.silogistik2106751915.service;
import apap.ti.silogistik2106751915.model.Karyawan;

import java.math.BigInteger;
import java.util.List;

public interface KaryawanService {
    void createKaryawan(Karyawan karyawan);

    List<Karyawan> getAllKaryawan();

    Karyawan getKaryawanById(BigInteger idKaryawan);
}
