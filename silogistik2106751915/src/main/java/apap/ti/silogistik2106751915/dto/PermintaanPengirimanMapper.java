package apap.ti.silogistik2106751915.dto;

import org.mapstruct.Mapper;

import apap.ti.silogistik2106751915.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751915.model.PermintaanPengiriman;

@Mapper(componentModel = "spring")
public interface PermintaanPengirimanMapper {
    PermintaanPengiriman CreatePermintaanPengirimanRequestDTOToPermintaanPengiriman(CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO);
}
