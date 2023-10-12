package apap.ti.silogistik2106751915.dto.request;

import java.math.BigInteger;
import java.util.List;

import apap.ti.silogistik2106751915.model.GudangBarang;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateGudangRequestDTO {
    private BigInteger idGudang;

    private String namaGudang;

    private String alamatGudang;

    private List<GudangBarang> GudangBarang;
}
