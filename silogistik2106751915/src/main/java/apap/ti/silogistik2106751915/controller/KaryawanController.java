package apap.ti.silogistik2106751915.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import apap.ti.silogistik2106751915.service.KaryawanService;

@Controller
public class KaryawanController {
    @Autowired
    KaryawanService karyawanService;
}
