package apap.ti.silogistik2106751915.dto;

import apap.ti.silogistik2106751915.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751915.dto.request.RestockGudangRequestDTO;
import apap.ti.silogistik2106751915.model.Gudang;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GudangMapper {
    Gudang createGudangRequestDTOToGudang(CreateGudangRequestDTO createGudangRequestDTO);
    RestockGudangRequestDTO GudangTocreateGudangRequestDTO(Gudang gudang);
}