package apap.ti.silogistik2106751915.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.Gudang;
import apap.ti.silogistik2106751915.repository.BarangDb;
import apap.ti.silogistik2106751915.repository.KaryawanDb;
import apap.ti.silogistik2106751915.repository.GudangDb;
import apap.ti.silogistik2106751915.repository.PermintaanPengirimanDb;
import apap.ti.silogistik2106751915.service.BarangService;
import apap.ti.silogistik2106751915.service.GudangBarangService;
import apap.ti.silogistik2106751915.service.GudangService;
import jakarta.validation.Valid;
import apap.ti.silogistik2106751915.dto.GudangMapper;
import apap.ti.silogistik2106751915.dto.request.RestockGudangRequestDTO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

    @Autowired
    BarangDb barangDb;

    @Autowired
    GudangDb gudangDb;

    @Autowired
    KaryawanDb karyawanDb;

    @Autowired
    PermintaanPengirimanDb permintaanPengirimanDb;
    
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("jumlahGudang", gudangDb.count());
        model.addAttribute("jumlahBarang", barangDb.count());
        model.addAttribute("jumlahKaryawan", karyawanDb.count());
        model.addAttribute("jumlahPermintaanPengiriman", permintaanPengirimanDb.count());
        return "home";
    }

    @GetMapping("/gudang")
    public String listGudang(Model model){
        List<Gudang> listGudang = gudangService.getAllGudang();

        model.addAttribute("listGudang", listGudang);

        return "viewall-gudang";
    }

    @GetMapping("/gudang/{idGudang}")
    public String detailGudang(@PathVariable("idGudang") BigInteger idGudang, Model model) {
        var gudang = gudangService.getGudangById(idGudang);

        model.addAttribute("gudang", gudang);

        return "view-gudang";
    }

    @GetMapping("/gudang/{idGudang}/restock-barang")
    public String restockGudang(@PathVariable("idGudang") BigInteger idGudang, Model model) {
        List<Barang> listBarangExisting = barangService.getAllBarang();

        var restockGudangRequestDTO = new RestockGudangRequestDTO();
        restockGudangRequestDTO.setIdGudang(idGudang);

        model.addAttribute("restockGudangRequestDTO", restockGudangRequestDTO);
        model.addAttribute("listGudangBarang", restockGudangRequestDTO.getListBarang());

        Gudang gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);

        model.addAttribute("listBarangExisting", listBarangExisting);

        return "form-restock-barang";
    }

    @PostMapping(value = "/gudang/{idGudang}/restock-barang", params = {"addRow"})
    public String addRowGudangBarang(@PathVariable("idGudang") BigInteger idGudang, @ModelAttribute RestockGudangRequestDTO restockGudangRequestDTO, Model model) {
         if (restockGudangRequestDTO.getListBarang() == null || restockGudangRequestDTO.getListBarang().size() == 0) {
               restockGudangRequestDTO.setListBarang(new ArrayList<>());         
            }

        restockGudangRequestDTO.getListBarang().add(new RestockGudangRequestDTO.BarangRestockDTO());
        
        Gudang gudang = gudangService.getGudangById(idGudang);
        model.addAttribute("gudang", gudang);

        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("listGudangBarang", restockGudangRequestDTO.getListBarang());
        model.addAttribute("restockGudangRequestDTO", restockGudangRequestDTO);

        return "form-restock-barang";
    }

    @PostMapping(value = "/gudang/{idGudang}/restock-barang")
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

        gudangBarangService.updateGudangBarang(restockGudangRequestDTO, idGudang);

        model.addAttribute("gudang", gudangService.getGudangById(idGudang));

        return "success-restock-gudang";
    }

    @GetMapping("/gudang/cari-barang")
    public String cariBarang(@RequestParam(value = "barang", required = false) String skuBarang, Model model) {
        var listBarang = barangService.getAllBarang();

        Collections.sort(listBarang, Comparator.comparing(Barang::getMerk));

        model.addAttribute("listBarang", listBarang);

        if (skuBarang != null){
            var gudangBarang = gudangBarangService.getGudangBarangBySku(skuBarang);
            model.addAttribute("gudangBarang", gudangBarang);
        }

        return "view-cari-barang";
    }
}
