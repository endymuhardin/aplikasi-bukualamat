/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bukualamat.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author endy
 */
@Entity 
@Table(name="t_grup")
public class Grup {
    @Id @GeneratedValue
    private Long id;
    
    @NotNull
    @Size(min=3, max=5)
    @Column(unique=true, nullable=false)
    private String kode;
    
    @Column(nullable=false)
    private String nama;
    
    @OneToMany(mappedBy="grup", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Kontak> daftarKontak = new ArrayList<Kontak>();
    
    public void addKontak(Kontak k){
    	daftarKontak.add(k);
    	k.setGrup(this);
    }
    
    public void removeKontak(Kontak k){
    	daftarKontak.remove(k);
    	k.setGrup(null);
    }
    
	public List<Kontak> getDaftarKontak() {
		return daftarKontak;
	}

	public void setDaftarKontak(List<Kontak> daftarKontak) {
		this.daftarKontak = daftarKontak;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
    
}
