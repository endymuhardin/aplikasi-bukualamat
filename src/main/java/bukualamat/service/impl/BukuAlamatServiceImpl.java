/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bukualamat.service.impl;

import bukualamat.entity.Grup;
import bukualamat.entity.Kontak;
import bukualamat.service.BukuAlamatService;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author endy
 */
@Service("bukuAlamatService")
@Transactional
public class BukuAlamatServiceImpl implements BukuAlamatService {

    @Autowired private SessionFactory sessionFactory;
    
    @Override
    public void simpan(Grup grup) {
    	sessionFactory.getCurrentSession().saveOrUpdate(grup);
    }

    @Override
    public void hapus(Grup grup) {
    	sessionFactory.getCurrentSession().delete(grup);
    }

    @Override
    public Grup cariGrupById(Long id) {
    	if(id == null) return null;
    	return (Grup) sessionFactory.getCurrentSession().get(Grup.class, id);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Grup> semuaGrup() {
    	return sessionFactory.getCurrentSession()
    	.createQuery("from Grup order by kode")
    	.list();
    }

    @Override
    public void simpan(Kontak kontak) {
    	sessionFactory.getCurrentSession().saveOrUpdate(kontak);
    }

    @Override
    public void hapus(Kontak kontak) {
    	sessionFactory.getCurrentSession().delete(kontak);
    }

    @Override
    public Kontak cariKontakById(Long id) {
    	return (Kontak) sessionFactory.getCurrentSession().get(Kontak.class, id);
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Kontak> semuaKontak() {
    	return sessionFactory.getCurrentSession()
    	.createQuery("from Kontak order by nama")
    	.list();
    }

    @SuppressWarnings("unchecked")
	@Override
    public List<Kontak> cariKontak(Grup grup) {
    	if(grup == null || grup.getId() == null) { 
    		return new ArrayList<Kontak>();
    	}
    	
    	return sessionFactory.getCurrentSession()
		.createQuery("from Kontak k where k.grup.id = :grup order by nama")
		.setLong("grup", grup.getId())
		.list();
    }

	@Override
	public Grup cariGrupByKode(String kode) {
		Grup g = (Grup) sessionFactory.getCurrentSession()
		.createQuery("from Grup where kode = :kode")
		.setString("kode", kode)
		.uniqueResult();
		Hibernate.initialize(g.getDaftarKontak());
		return g;
	}
    
}
