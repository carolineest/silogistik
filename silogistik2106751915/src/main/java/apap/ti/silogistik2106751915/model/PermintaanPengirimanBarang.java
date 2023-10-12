package apap.ti.silogistik2106751915.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permintaan_pengiriman_barang")
public class PermintaanPengirimanBarang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger idPermintaanPengirimanBarang;

    @ManyToOne
    @JoinColumn(name = "id_permintaan_pengiriman")
    private PermintaanPengiriman idPermintaanPengiriman;

    @ManyToOne
    @JoinColumn(name = "sku_barang")
    private Barang skuBarang;

    @NotNull
    @Column(name = "kuantitas_pesanan")
    private int kuantitasPesanan;
}
