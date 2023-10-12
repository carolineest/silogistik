package apap.ti.silogistik2106751915.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import apap.ti.silogistik2106751915.service.PermintaanPengirimanService;

@Controller
public class PermintaanPengirimanController {
    @Autowired
    PermintaanPengirimanService permintaanPengirimanService;
    
    @GetMapping("permintaan-pengiriman")
    public String listPermintaanPengiriman(Model model){
        var listPermintaanPengiriman = permintaanPengirimanService.getAllPermintaanPengiriman();

        model.addAttribute("listPermintaanPengiriman", listPermintaanPengiriman);

        return "viewall-permintaanpengiriman";
    }
}
