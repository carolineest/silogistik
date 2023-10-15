package apap.ti.silogistik2106751915.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman")
public class PermintaanPengiriman {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_permintaan_pengiriman")
    private BigInteger idPermintaanPengiriman;

    @Column(name = "nomor_pengiriman")
    private String nomorPengiriman;

    @Column(name = "is_cancelled")
    private Boolean isCancelled;

    @NotNull
    @Column(name = "nama_penerima", nullable = false)
    private String namaPenerima;

    @NotNull
    @Column(name = "alamat_penerima", nullable = false)
    private String alamatPenerima;

    @NotNull
    @Column(name = "tanggal_pengiriman", nullable = false)
    private Date tanggalPengiriman;

    @NotNull
    @Column(name = "biaya_pengiriman", nullable = false)
    private int biayaPengiriman;

    @NotNull
    @Column(name = "jenis_layanan", nullable = false)
    private int jenisLayanan;

    @Column(name = "waktu_permintaan")
    private LocalDateTime waktuPermintaan;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_karyawan", referencedColumnName = "idKaryawan")
    private Karyawan karyawan;

    @OneToMany(mappedBy = "idPermintaanPengiriman")
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarang;

    // Properti yang tidak akan disimpan ke database
    @Transient
    private String formattedWaktuPermintaan;

    @Transient
    private String formattedTanggalPengiriman;
}
