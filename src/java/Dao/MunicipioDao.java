/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Municipio;
import Utils.DataAccessLayerException;
import Utils.HibernateFactoryMun;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Andre
 */
public class MunicipioDao implements Serializable {
    protected Transaction tx;
    protected Session session;

    public MunicipioDao() {
        HibernateFactoryMun.buildIfNeeded();
        
    }

    public void saveOrUpdate(Municipio objeto) {
        try {
            startOperation();
            session.saveOrUpdate(objeto);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryMun.close(session);
        }
    }

    public void delete(Municipio objeto) {
        try {
            startOperation();
            session.delete(objeto);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryMun.close(session);
        }
    }

    public Municipio find(Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.get(Municipio.class, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryMun.close(session);
        }
        return (Municipio) obj;
    }

    public List<Municipio> findAll() {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from Municipio");
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryMun.close(session);
        }
        return objects;
    }
    
    public List<Municipio> findAllServlet(String nome, List<Long> idMunicipio, List<Long> idUF, List<Long> idRegiao, List<Integer> idSerie, List<Integer> tipoRede, List<Integer> localizacao) {
        List listinha = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            startOperation();
            Criteria c = session.createCriteria(Municipio.class);
            if (idMunicipio.get(0)>=0)
                c.add(Restrictions.in("municipio", idMunicipio));
            if (idUF.get(0)>=0)
                c.add(Restrictions.in("uf", idUF));
            if (idRegiao.get(0)>=0)
                c.add(Restrictions.in("regiao", idRegiao));
            
            if (nome != null && nome != "") {
                    String nomeSplit[] = nome.split(" ");
                    for (String _nome : nomeSplit) {
                        strBuilder.append("%");
                        strBuilder.append(_nome);
                        strBuilder.append("%");
                        _nome = strBuilder.toString();
                        strBuilder.delete(0 , strBuilder.length());
                        c.add(Restrictions.ilike("nome", _nome));
                    }
                }
            
            if (idSerie.get(0)>=0)
                c.add(Restrictions.in("serie", idSerie));
            if (tipoRede.get(0)>=0)
                c.add(Restrictions.in("tipoRede", tipoRede));
            if (localizacao.get(0)>=0)
                c.add(Restrictions.in("localizacao", localizacao));
            listinha = c.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryMun.close(session);
        }        
        return listinha;
    }

    public void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactoryMun.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    public void startOperation() throws HibernateException {
        session = HibernateFactoryMun.openSession();
        tx = session.beginTransaction();
    }
}