package apap.ti.silogistik2106751915.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import apap.ti.silogistik2106751915.repository.GudangBarangDb;
import apap.ti.silogistik2106751915.repository.GudangDb;
import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.Gudang;
import apap.ti.silogistik2106751915.model.GudangBarang;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class GudangServiceImpl implements GudangService {
    @Autowired
    GudangDb gudangDb;

    @Autowired
    GudangBarangDb gudangBarangDb;

    @Override
    public void createGudang(Gudang gudang) { gudangDb.save(gudang); }

    @Override
    public List<Gudang> getAllGudang() { return gudangDb.findAll(); }

    // @Override
    // public Gudang getGudangById(BigInteger idGudang) {
    //     return gudangDb.findById(idGudang).get();
    // }

    @Override
    public Gudang getGudangById(BigInteger idGudang) {
        for (Gudang gudang : getAllGudang()) {
            if (gudang.getIdGudang().equals(idGudang)) {
                return gudang;
            }
        }
        return null;
    }

    // @Override
    // public Gudang updateGudangBarang(Gudang gudangFromDTO) {
    //     // Retrieve the existing Gudang entity from the database
    //     // System.out.println("haLO*********************************" + gudangFromDTO.getIdGudang());
    //     // System.out.println("mye*********************************" + gudangFromDTO.getGudangBarang().get(0));
    //     Gudang existingGudang = getGudangById(gudangFromDTO.getIdGudang());
    //     // System.out.println("haLO" + existingGudang.getIdGudang());

    //     // Get the existing Set of GudangBarang
    //     var existingGudangBarang = existingGudang.getGudangBarang();

    //     if (existingGudangBarang == null) {
    //         existingGudangBarang = new ArrayList<>();
    //         existingGudang.setGudangBarang(existingGudangBarang);
    //     }

    //     // Iterate over each GudangBarang in gudangFromDTO
    //     for (GudangBarang newGudangBarang : gudangFromDTO.getGudangBarang()) {
    //         boolean found = false;
            
    //         // Check if GudangBarang exists in existingGudang
    //         for (GudangBarang existingElem : existingGudangBarang) {
    //             if (existingElem.getIdGudangBarang().equals(newGudangBarang.getIdGudangBarang())) {
    //                 found = true;
                    
    //                 // Update the stock
    //                 existingElem.setStok(existingElem.getStok() + newGudangBarang.getStok());
    //                 gudangBarangDb.save(existingElem);
    //                 break;
    //             }
    //         }
            
    //         if (!found) {
    //             // If the GudangBarang is not found, add it to the existing Set
    //             existingGudangBarang.add(newGudangBarang);
    //             // You might also need to set the Gudang reference in newGudangBarang
    //             newGudangBarang.setIdGudang(existingGudang);
    //         }
    //     }

    //     // Update the existing Gudang with the new Set of GudangBarang
    //     existingGudang.setGudangBarang(existingGudangBarang);
        
    //     // Save or update the Gudang entity (Implementation would depend on your repository setup)
    //     // gudangRepository.save(existingGudang);

    //     return existingGudang;
    // }

}
