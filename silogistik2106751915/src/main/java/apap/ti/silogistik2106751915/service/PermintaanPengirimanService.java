package apap.ti.silogistik2106751915.service;
import apap.ti.silogistik2106751915.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751915.model.PermintaanPengiriman;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

public interface PermintaanPengirimanService {
    List<PermintaanPengiriman> getAllPermintaanPengiriman();

    PermintaanPengiriman getPermintaanPengirimanById(BigInteger idPermintaanPengiriman);

    PermintaanPengiriman createPermintaanPengiriman(PermintaanPengiriman permintaanPengiriman);

    String generateNomorPengiriman(int jumlahBarang, int jenisLayanan, LocalDateTime waktuPermintaan);

    void updatePermintaanPengiriman(PermintaanPengiriman existingPermintaanPengiriman, CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
}
