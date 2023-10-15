package apap.ti.silogistik2106751915.controller;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

import apap.ti.silogistik2106751915.dto.PermintaanPengirimanMapper;
import apap.ti.silogistik2106751915.dto.request.CreatePermintaanPengirimanRequestDTO;
import apap.ti.silogistik2106751915.model.Barang;
import apap.ti.silogistik2106751915.model.Karyawan;
import apap.ti.silogistik2106751915.model.PermintaanPengiriman;
import apap.ti.silogistik2106751915.model.PermintaanPengirimanBarang;
import apap.ti.silogistik2106751915.service.BarangService;
import apap.ti.silogistik2106751915.service.KaryawanService;
import apap.ti.silogistik2106751915.service.PermintaanPengirimanService;
import jakarta.validation.Valid;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;

    @Autowired
    KaryawanService karyawanService;

    @Autowired
    PermintaanPengirimanMapper permintaanPengirimanMapper;

    @Autowired
    BarangService barangService;
    
    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model){
        var listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
        DateTimeFormatter tanggalFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (PermintaanPengiriman permintaan : listPermintaanPengiriman) {
            String formattedDateTime = permintaan.getWaktuPermintaan().format(formatter);
            permintaan.setFormattedWaktuPermintaan(formattedDateTime);

            LocalDateTime localDateTime = permintaan.getTanggalPengiriman().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            String formattedDate = localDateTime.format(tanggalFormatter);
            permintaan.setFormattedTanggalPengiriman(formattedDate);
        }

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);

        return "viewall-permintaanpengiriman";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}")
    public String detailPermintaanPengiriman(@PathVariable("idPermintaanPengiriman") BigInteger idPermintaanPengiriman, Model model) {
        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm");
        DateTimeFormatter tanggalFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String formattedDateTime = permintaanPengiriman.getWaktuPermintaan().format(formatter);
        permintaanPengiriman.setFormattedWaktuPermintaan(formattedDateTime);

        LocalDateTime localDateTime = permintaanPengiriman.getTanggalPengiriman().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String formattedDate = localDateTime.format(tanggalFormatter);
        permintaanPengiriman.setFormattedTanggalPengiriman(formattedDate);

        BigInteger totalHarga = BigInteger.ZERO; // Total harga permintaan pengiriman

        // Iterasi melalui setiap PermintaanPengirimanBarang
        for (PermintaanPengirimanBarang barang : permintaanPengiriman.getPermintaanPengirimanBarang()) {
            Barang skuBarang = barang.getSkuBarang();
            BigInteger hargaBarang = skuBarang.getHargaBarang();
            int kuantitasPesanan = barang.getKuantitasPesanan();

            BigInteger totalHargaBarang = hargaBarang.multiply(BigInteger.valueOf(kuantitasPesanan));
            barang.setTotalHarga(totalHargaBarang); // Set total harga per barang

            totalHarga = totalHarga.add(totalHargaBarang);
        }

        model.addAttribute("permintaanPengiriman", permintaanPengiriman);

        return "view-permintaan-pengiriman";
    }

    @GetMapping("permintaan-pengiriman/tambah")
    public String formAddPermintaanPengiriman(Model model) {
        List<Barang> listBarangExisting = barangService.getAllBarang();

        var createPermintaanPengirimanRequestDTO = new CreatePermintaanPengirimanRequestDTO();
        model.addAttribute("createPermintaanPengirimanRequestDTO", createPermintaanPengirimanRequestDTO);
        model.addAttribute("listBarangExisting", listBarangExisting);

        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);

        return "form-create-permintaanpengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah", params = {"addRow"})
    public String addRowPermintaanPengiriman(@ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, Model model) {
        if (createPermintaanPengirimanRequestDTO.getListBarang() == null || createPermintaanPengirimanRequestDTO.getListBarang().size() == 0) {
               createPermintaanPengirimanRequestDTO.setListBarang(new ArrayList<>());         
            }

        createPermintaanPengirimanRequestDTO.getListBarang().add(new CreatePermintaanPengirimanRequestDTO.BarangPermintaanDTO());

        List<Karyawan> listKaryawan = karyawanService.getAllKaryawan();
        model.addAttribute("listKaryawan", listKaryawan);

        model.addAttribute("listBarangExisting", barangService.getAllBarang());
        model.addAttribute("listPermintaanPengiriman", createPermintaanPengirimanRequestDTO.getListBarang());
        model.addAttribute("createPermintaanPengirimanRequestDTO", createPermintaanPengirimanRequestDTO);

        return "form-create-permintaanpengiriman";
    }

    @PostMapping(value = "permintaan-pengiriman/tambah")
    public String addPermintaanPengiriman(@Valid
        @ModelAttribute CreatePermintaanPengirimanRequestDTO createPermintaanPengirimanRequestDTO, BindingResult bindingResult,
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

        var permintaanPengiriman = permintaanPengirimanMapper.CreatePermintaanPengirimanRequestDTOToPermintaanPengiriman(createPermintaanPengirimanRequestDTO);
        permintaanPengiriman = permintaanPengirimanService.createPermintaanPengiriman(permintaanPengiriman);
        model.addAttribute("permintaanPengiriman", createPermintaanPengirimanRequestDTO);

        permintaanPengirimanService.updatePermintaanPengiriman(permintaanPengiriman, createPermintaanPengirimanRequestDTO);

        model.addAttribute("permintaanPengiriman", permintaanPengirimanService);

        return "success-create-permintaan";
    }

    @GetMapping("permintaan-pengiriman/{idPermintaanPengiriman}/cancel")
    public String cancelPermintaan(@PathVariable("idPermintaanPengiriman") BigInteger idPermintaanPengiriman, Model model) {

        var permintaanPengiriman = permintaanPengirimanService.getPermintaanPengirimanById(idPermintaanPengiriman);
        LocalDateTime waktuPermintaan = permintaanPengiriman.getWaktuPermintaan();
        LocalDateTime waktuSekarang = LocalDateTime.now();

        Duration durasi = Duration.between(waktuPermintaan, waktuSekarang);

        if(durasi.toHours() < 24) {
            permintaanPengirimanService.cancelPermintaan(permintaanPengiriman);
            model.addAttribute("permintaanPengiriman", permintaanPengiriman);
            return "success-cancel-permintaan";
        } else {
            model.addAttribute("permintaanPengiriman", permintaanPengiriman);
            return "fail-cancel-permintaan";
        }
    }
}
