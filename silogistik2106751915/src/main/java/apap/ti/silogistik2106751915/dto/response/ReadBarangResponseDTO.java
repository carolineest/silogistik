package apap.ti.silogistik2106751915.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReadBarangResponseDTO {
    private BigInteger idGudang;
    private String namaGudang;
    private String alamatGudang;
    private int stok;
}
