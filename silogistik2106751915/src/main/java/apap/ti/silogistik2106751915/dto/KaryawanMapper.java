package apap.ti.silogistik2106751915.dto;

import apap.ti.silogistik2106751915.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751915.model.Karyawan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KaryawanMapper {
    Karyawan createKaryawanRequestDTOToKaryawan(CreateKaryawanRequestDTO createKaryawanRequestDTO);
}
