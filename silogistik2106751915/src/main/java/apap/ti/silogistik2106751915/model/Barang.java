package apap.ti.silogistik2106751915.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "barang")
public class Barang {
    @Id
    @Column(name = "sku")
    private String sku;

    @NotNull
    @Column(name = "tipe_barang", nullable = false)
    private int tipeBarang;

    @NotNull
    @Column(name = "merk", nullable = false)
    private String merk;

    @NotNull
    @Column(name = "harga_barang", nullable = false)
    private BigInteger hargaBarang;

    @OneToMany(mappedBy = "skuBarang")
    private List<GudangBarang> gudangBarang;

    @OneToMany(mappedBy = "skuBarang")
    private List<PermintaanPengirimanBarang> permintaanPengirimanBarang;

    private int totalStok;
}
