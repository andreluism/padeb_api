/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Regiao;
import Utils.DataAccessLayerException;
import Utils.HibernateFactoryR;
import java.io.Serializable;
import java.util.ArrayList;
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
public class RegiaoDao implements Serializable {
    protected Transaction tx;
    protected Session session;

    public RegiaoDao() {
        HibernateFactoryR.buildIfNeeded();
        
    }

    public void saveOrUpdate(Regiao objeto) {
        try {
            startOperation();
            session.saveOrUpdate(objeto);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryR.close(session);
        }
    }

    public void delete(Regiao objeto) {
        try {
            startOperation();
            session.delete(objeto);
            session.flush();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryR.close(session);
        }
    }

    public Regiao find(Long id) {
        Object obj = null;
        try {
            startOperation();
            obj = session.get(Regiao.class, id);
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryR.close(session);
        }
        return (Regiao) obj;
    }

    public List<Regiao> findAll() {
        List objects = null;
        try {
            startOperation();
            Query query = session.createQuery("from Regiao");
            objects = query.list();
            tx.commit();
        } catch (HibernateException e) {
            handleException(e);
        } finally {
            HibernateFactoryR.close(session);
        }
        return objects;
    }
    
    public List<Regiao> findAllServlet(String nome, List<Long> idRegiao, List<Integer> idSerie, List<Integer> tipoRede, List<Integer> localizacao, List<Integer> capital) {
        List listinha = null;
        StringBuilder strBuilder = new StringBuilder();
        try {
            startOperation();
            Criteria c = session.createCriteria(Regiao.class);
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
            strBuilder.delete(0 , strBuilder.length());
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
            HibernateFactoryR.close(session);
        }        
        return listinha;
    }
    
    public void handleException(HibernateException e) throws DataAccessLayerException {
        HibernateFactoryR.rollback(tx);
        throw new DataAccessLayerException(e);
    }

    public void startOperation() throws HibernateException {
        session = HibernateFactoryR.openSession();
        tx = session.beginTransaction();
    }
}
