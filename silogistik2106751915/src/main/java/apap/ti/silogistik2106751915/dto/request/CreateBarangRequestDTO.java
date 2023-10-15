package apap.ti.silogistik2106751915.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateBarangRequestDTO {
    @NotBlank(message = "Merk harus diisi.")
    private String merk;
    
    @NotNull(message = "Tipe barang harus diisi.")
    @Positive(message = "Tipe barang harus bernilai positif.")
    private int tipeBarang;

    @NotNull(message = "Harga Barang harus diisi.")
    @Positive(message = "Harga Barang harus bernilai positif.")
    private BigInteger hargaBarang;
}
