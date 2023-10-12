package apap.ti.silogistik2106751915;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import jakarta.transaction.Transactional;

import com.github.javafaker.Faker;

import apap.ti.silogistik2106751915.dto.GudangMapper;
import apap.ti.silogistik2106751915.dto.KaryawanMapper;
import apap.ti.silogistik2106751915.dto.request.CreateGudangRequestDTO;
import apap.ti.silogistik2106751915.dto.request.CreateKaryawanRequestDTO;
import apap.ti.silogistik2106751915.service.GudangService;
import apap.ti.silogistik2106751915.service.KaryawanService;

@SpringBootApplication
public class Silogistik2106751915Application {

	public static void main(String[] args) {
		SpringApplication.run(Silogistik2106751915Application.class, args);
	}

	// CommandLineRunner digunakan untuk execute code saat spring pertama kali start up
 	@Bean
 	@Transactional
 	public CommandLineRunner run( GudangService gudangService, KaryawanService karyawanService, GudangMapper gudangMapper, KaryawanMapper karyawanMapper) {
  		return args -> {
			var faker = new Faker(new Locale("in-ID"));

			for (int i=0; i<10; i++){
				// Membuat fake data memanfaatkan Java Faker
				var gudangDTO = new CreateGudangRequestDTO();
				gudangDTO.setNamaGudang("Gudang " + faker.company().name());
				gudangDTO.setAlamatGudang(faker.address().fullAddress());

				var karyawanDTO = new CreateKaryawanRequestDTO();
				karyawanDTO.setNamaKaryawan(faker.name().fullName());
				karyawanDTO.setJenisKelamin(faker.options().option(1, 2));
				karyawanDTO.setTanggalLahir(faker.date().birthday());

				// Mapping gudangDTO ke gudang lalu save gudang ke database
				var gudang = gudangMapper.createGudangRequestDTOToGudang(gudangDTO);
				gudangService.createGudang(gudang);

				// Mapping karyawanDTO ke karyawan lalu save karyawan ke database
				var karyawan = karyawanMapper.createKaryawanRequestDTOToKaryawan(karyawanDTO);
				karyawanService.createKaryawan(karyawan);
			}
  		};
 	}
}
