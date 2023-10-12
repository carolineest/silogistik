package apap.ti.silogistik2106751915.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import apap.ti.silogistik2106751915.service.KaryawanService;

@Controller
public class KaryawanController {
    @Autowired
    KaryawanService karyawanService;
    
    // @GetMapping()
    // public String listKaryawan(Model model){
    //     var listKaryawan = karyawanService.getAllKaryawan();

    //     model.addAttribute("listKaryawan", listKaryawan);

    //     return "viewall-karyawan";
    // }
}
