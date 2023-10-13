package apap.ti.silogistik2106751915.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import apap.ti.silogistik2106751915.dto.BarangMapper;
import apap.ti.silogistik2106751915.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751915.dto.request.CreateBarangRequestDTO;
import apap.ti.silogistik2106751915.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751915.model.Karyawan;
import apap.ti.silogistik2106751915.model.PermintaanPengiriman;
import apap.ti.silogistik2106751915.service.KaryawanService;
import apap.ti.silogistik2106751915.service.PermintaanPengirimanService;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;
    
    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model){
        var listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);

        return "viewall-permintaanpengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") BigInteger idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);

        model.addAttribute("permintaanPengiriman", permintaanPengiriman);

        return "view-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model) {

        var permintaanPengirimanDTO = new CreatePermintaanPengirimanRequestDTO();
        model.addAttribute("permintaanPengirimanDTO", permintaanPengirimanDTO);

        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);

        return "form-create-permintaanpengiriman";
    }

    @PostMapping("permintaan-pengiriman/tambah")
    public String addPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model) {
        var permintaanPengiriman = permintaanPengirimanMapper.CreatePermintaanPengirimanRequestDTOToPermintaanPengiriman(createPermintaanPengirimanRequestDTO);

        //Memanggil Service createPenerbit
        permintaanPengiriman = permintaanPengirimanService.createPermintaanPengiriman(permintaanPengiriman);

        //Menambah penerbit ke model thymeleaf
        model.addAttribute("permintaanPengiriman", createPermintaanPengirimanRequestDTO);

        return "success-create-permintaan";
    }
}
