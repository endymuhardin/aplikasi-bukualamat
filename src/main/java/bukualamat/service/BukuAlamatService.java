/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bukualamat.service;

import bukualamat.entity.Grup;
import bukualamat.entity.Kontak;
import java.util.List;

/**
 *
 * @author endy
 */
public interface BukuAlamatService {
    public void simpan(Grup grup);
    public void hapus(Grup grup);
    public Grup cariGrupById(Long id);
    public Grup cariGrupByKode(String kode);
    public List<Grup> semuaGrup();
    
    
    public void simpan(Kontak kontak);
    public void hapus(Kontak kontak);
    public Kontak cariKontakById(Long id);
    public List<Kontak> semuaKontak();
    public List<Kontak> cariKontak(Grup grup);
}
