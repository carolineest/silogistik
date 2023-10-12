package apap.ti.silogistik2106751915.dto.request;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestockGudangRequestDTO {
    private BigInteger idGudang;
    private List<BarangRestockDTO> listBarang;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BarangRestockDTO {
        private String sku;
        private int stok;
    }
}
