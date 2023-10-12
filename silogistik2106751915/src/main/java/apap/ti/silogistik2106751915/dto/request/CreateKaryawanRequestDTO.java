package apap.ti.silogistik2106751915.dto.request;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateKaryawanRequestDTO {
    private String namaKaryawan;

    private int jenisKelamin;

    private Date tanggalLahir;
}
