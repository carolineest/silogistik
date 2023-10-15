package apap.ti.silogistik2106751915.dto.request;

import org.springframework.format.annotation.DateTimeFormat;

import apap.ti.silogistik2106751915.model.Karyawan;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
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

    private List<BarangPermintaanDTO> listBarang;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BarangPermintaanDTO {
        private String sku;
        private int stok;
    }
}
