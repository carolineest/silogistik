package apap.ti.silogistik2106751915.dto.request;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import apap.ti.silogistik2106751915.model.Karyawan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreatePermintaanPengirimanRequestDTO {
    @NotBlank(message = "Nama penerima harus diisi.")
    private String namaPenerima;

    @NotBlank(message = "Alamat penerima harus diisi.")
    private String alamatPenerima;

    @NotNull(message = "Jenis layanan harus diisi.")
    @Positive(message = "Jenis layanan harus bernilai positif.")
    private int jenisLayanan;

    @NotNull(message = "Biaya pengiriman harus diisi.")
    @Positive(message = "Biaya pengiriman harus bernilai positif.")
    private int biayaPengiriman;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tanggalPengiriman;

    private Karyawan karyawan;

    // karyawan & barang?
}
