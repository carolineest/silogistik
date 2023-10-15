package apap.ti.silogistik2106751915.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import apap.ti.silogistik2106751915.dto.BarangMapper;
import apap.ti.silogistik2106751915.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751915.dto.request.UpdateBarangRequestDTO;
import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.service.BarangService;
import jakarta.validation.Valid;

@Controller
public class BarangController {
    @Autowired
    BarangService barangService;

    @Autowired
    BarangMapper barangMapper;
    
    @GetMapping("barang")
    public String listBarang(Model model){
        var listBarang = barangService.getAllBarang();

        model.addAttribute("listBarang", listBarang);

        return "viewall-barang";
    }

    @GetMapping("/barang/{sku}")
    public String detailBarang(@PathVariable("sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);

        List<Integer> totalStok = new ArrayList<>();
        for (Barang barangElem : barangService.getAllBarang()) {
            totalStok.add(barangService.countStok(barangElem));
        }
        int totalAkhir = 0;
        for (Integer totalElem : totalStok) {
            totalAkhir += totalElem;
        }
        model.addAttribute("totalStok", totalAkhir);
        model.addAttribute("barang", barang);
        return "view-barang";
    }

    @GetMapping("barang/tambah")
    public String formAddBarang(Model model) {

        var barangDTO = new CreateBarangRequestDTO();

        model.addAttribute("barangDTO", barangDTO);
        
        System.out.println(barangDTO);

        return "form-create-barang";
    }

    @PostMapping("barang/tambah")
    public String addBarang(@ModelAttribute CreateBarangRequestDTO createBarangRequestDTO, Model model) {
        var barang = barangMapper.CreateBarangRequestDTOToBarang(createBarangRequestDTO);

        barang = barangService.createBarang(barang);

        model.addAttribute("barang", createBarangRequestDTO);

        return "success-create-barang";
    }

    @GetMapping("barang/{sku}/ubah")
    public String formUpdateBarang(@PathVariable("sku") String sku, Model model) {
        var barang = barangService.getBarangBySku(sku);

        var barangDTO = barangMapper.BarangToUpdateBarangRequestDTO(barang);

        model.addAttribute("barangDTO", barangDTO);

        return "form-update-barang";
    }

    @PostMapping("barang/{sku}/ubah")
    public String updateBarang(@Valid @ModelAttribute UpdateBarangRequestDTO barangDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : errors) {
                errorMessage.append(error.getDefaultMessage()).append("\n");
            }
            model.addAttribute("errorMessage", errorMessage.toString());
            return "error-view";
        }

        var barangFromDTO = barangMapper.UpdateBarangRequestDTOToBarang(barangDTO);

        var barang = barangService.updateBarang(barangFromDTO);

        model.addAttribute("sku", barang.getSku());

        model.addAttribute("merk", barang.getMerk());

        return "success-update-barang";
    }
}
