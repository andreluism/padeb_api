/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.UF;
import Utils.DataAccessLayerException;
import Utils.HibernateFactoryUF;
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
public class UFDao implements Serializable {
    protected Transaction tx;
    protected Session session;

    public UFDao() {
        HibernateFactoryUF.buildIfNeeded();
        
    }

    public void saveOrUpdate(UF objeto) {
        try {
            startOperation();
            session.saveOrUpdate(objeto);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryUF.close(session);
        }
    }

    public void delete(UF objeto) {
        try {
            startOperation();
            session.delete(objeto);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryUF.close(session);
        }
    }

    public UF find(Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.get(UF.class, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryUF.close(session);
        }
        return (UF) obj;
    }

    public List<UF> findAll() {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from UF");
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryUF.close(session);
        }
        return objects;
    }
    
    public List<UF> findAllServlet(String nome, List<Long> idUF, List<Long> idRegiao, List<Integer> idSerie, List<Integer> tipoRede, List<Integer> localizacao, List<Integer> capital) {
        List listinha = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            startOperation();
            Criteria c = session.createCriteria(UF.class);
            if (idUF.get(0)>=0)
                c.add(Restrictions.in("uf", idUF));
            
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
            
            if (idRegiao.get(0)>=0)
                c.add(Restrictions.in("regiao", idRegiao));
            if (idSerie.get(0)>=0)
                c.add(Restrictions.in("serie", idSerie));
            if (tipoRede.get(0)>=0)
                c.add(Restrictions.in("tipoRede", tipoRede));
            if (localizacao.get(0)>=0)
                c.add(Restrictions.in("localizacao", localizacao));
            if (capital.get(0)>=0)
                c.add(Restrictions.in("capital", capital));
            listinha = c.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryUF.close(session);
        }        
        return listinha;
    }

    public void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactoryUF.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    public void startOperation() throws HibernateException {
        session = HibernateFactoryUF.openSession();
        tx = session.beginTransaction();
    }
}