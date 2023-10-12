package apap.ti.silogistik2106751915.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.Gudang;
import apap.ti.silogistik2106751915.model.GudangBarang;
import apap.ti.silogistik2106751915.service.BarangService;
import apap.ti.silogistik2106751915.service.GudangBarangService;
import apap.ti.silogistik2106751915.service.GudangService;
import jakarta.validation.Valid;
import apap.ti.silogistik2106751915.dto.BarangMapper;
import apap.ti.silogistik2106751915.dto.GudangMapper;
import apap.ti.silogistik2106751915.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751915.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751915.dto.request.RestockGudangRequestDTO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GudangController {
    @Autowired
    GudangService gudangService;

    @Autowired
    GudangBarangService gudangBarangService;

    @Autowired
    BarangService barangService;

    @Autowired
    GudangMapper gudangMapper;
    
    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/gudang")
    public String listGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);

        // model.addAttribute("activePage", "gudang");

        return "viewall-gudang";
    }

    @GetMapping("/gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") BigInteger idGudang, Model model) {
        var gudang = gudangService.getGudangById(idGudang);

        model.addAttribute("gudang", gudang);

        // model.addAttribute("activePage", "gudang");

        return "view-gudang";
    }

    // @GetMapping("/gudang/{idGudang}/restock-barang")
    // public String restockGudang(@PathVariable("idGudang") BigInteger idGudang, Model model) {
    //     var gudang = gudangService.getGudangById(idGudang);
    //     var gudangDTO = gudangMapper.GudangTocreateGudangRequestDTO(gudang);

    //     System.out.println(gudang);
    //     model.addAttribute("gudang", gudang);
    //     model.addAttribute("gudangDTO", gudangDTO);

    //     // model.addAttribute("activePage", "gudang");

    //     return "form-restock-barang";
    // }

    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String restockGudang(@PathVariable("idGudang") BigInteger idGudang, Model model) {
        List<Barang> listBarangExisting = barangService.getAllBarang();

        var restockGudangRequestDTO = new RestockGudangRequestDTO();
        model.addAttribute("restockGudangRequestDTO", restockGudangRequestDTO);

        Gudang gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);

        model.addAttribute("listBarangExisting", listBarangExisting);

        return "form-restock-barang";
    }

    // @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = { "addRow" })
    // public String addRowGudangBarang(
    //     @ModelAttribute CreateGudangRequestDTO createGudangRequestDTO,
    //     Model model
    //     ) {
    //         if (createGudangRequestDTO.getGudangBarang() == null || createGudangRequestDTO.getGudangBarang().size() == 0) {
    //             createGudangRequestDTO.setGudangBarang(new ArrayList<>());
    //         }

    //         // Memasukkan Penulis baru (kosong) ke list, untuk dirender sebagai row baru.
    //         // createBarangRequestDTO.getListGudangBarang().add(new GudangBarang());

    //         GudangBarang gudangBarangTemp = new GudangBarang();

    //         Gudang gudangTemp = gudangService.getGudangById(createGudangRequestDTO.getIdGudang());

    //         gudangBarangTemp.setIdGudang(gudangTemp);

    //         createGudangRequestDTO.getGudangBarang().add(gudangBarangTemp);

    //         // Kirim list penerbit penulis untuk menjadi pilihan pada dropdown.
            
    //         model.addAttribute("listBarangExisting", barangService.getAllBarang());

    //         model.addAttribute("gudangDTO", createGudangRequestDTO);

    //         return "form-restock-barang";
    // }

    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = { "addRow" })
    public String addGudangBarang(@PathVariable("idGudang") BigInteger idGudang, @Valid
        @ModelAttribute RestockGudangRequestDTO restockGudangRequestDTO, BindingResult bindingResult,
        Model model
        ) {
              if (bindingResult.hasErrors()) {
                List<ObjectError> errors = bindingResult.getAllErrors();
                StringBuilder errorMessage = new StringBuilder();
                for (ObjectError error : errors) {
                    errorMessage.append(error.getDefaultMessage()).append("\n");
                }
                model.addAttribute("errorMessage", errorMessage.toString());
                return "error-view";
            }

            if (restockGudangRequestDTO.getListBarang() == null || restockGudangRequestDTO.getListBarang().isEmpty()) {
                model.addAttribute("errorMessage", "Tidak ada barang yang di restock");
                return "error-view";            
            }

            gudangBarangService.updateGudangBarang(restockGudangRequestDTO, idGudang);

            model.addAttribute("gudang", gudangService.getGudangById(idGudang));

            return "success-restock-gudang";
    }

//     @PostMapping("/gudang/{idGudang}/restock-barang", params = "{addRow}")
//     public String addRowGudangBarang(@PathVariable("idGudang") BigInteger idGudang, @ModelAttribute CreateGudangRequestDTO createGudangRequestDTO, Model model) {
//         var gudang = gudangMapper.createGudangRequestDTOToGudang(createGudangRequestDTO);

//         //Memanggil Service createPenerbit
//         gudangService.updateGudangBarang(gudang);

//         //Menambah penerbit ke model thymeleaf
//         model.addAttribute("gudang", createGudangRequestDTO);

//         return "success-restock-gudang";
//     }
// }

@PostMapping(value = "/gudang/{idGudang}/restock-barang", params = "{addRow}")
    public String addRowGudangBarang(@PathVariable("idGudang") BigInteger idGudang, @ModelAttribute RestockGudangRequestDTO restockGudangRequestDTO, Model model) {
         if (restockGudangRequestDTO.getListBarang() == null || restockGudangRequestDTO.getListBarang().size() == 0) {
               restockGudangRequestDTO.setListBarang(new ArrayList<>());         
            }

        restockGudangRequestDTO.getListBarang().add(new RestockGudangRequestDTO.BarangRestockDTO());
        Gudang gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);

        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("restockGudangRequestDTO", restockGudangRequestDTO);

        return "form-restock-barang";
    }
}
