package apap.ti.silogistik2106751915.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    private String namaPenerima;

    private String alamatPenerima;

    private int jenisLayanan;

    private int biayaPengiriman;

    private LocalDateTime waktuPermintaan;

    // karyawan & barang?
}
