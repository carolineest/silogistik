package apap.ti.silogistik2106751915.dto;

import apap.ti.silogistik2106751915.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751915.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751915.model.Barang;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BarangMapper {
    Barang CreateBarangRequestDTOToBarang(CreateBarangRequestDTO createBarangRequestDTO);
    Barang UpdateBarangRequestDTOToBarang(UpdateBarangRequestDTO updateBarangRequestDTO);
    UpdateBarangRequestDTO BarangToUpdateBarangRequestDTO(Barang barang);
}
