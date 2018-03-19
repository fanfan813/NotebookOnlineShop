package com.amelin.shop.dao;

import com.amelin.shop.model.Notebook;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("NotebookDAO")
@Transactional
public class NotebookDAOImpl extends AbstractDAO<Integer, Notebook> implements NotebookDAO {
    public void saveNotebook(Notebook notebook) {
        persist(notebook);
    }

    public void updateNotebook(Notebook notebook) {
        getSession().update(notebook);
    }

    public void deleteNotebook(Notebook notebook) {
        delete(notebook);
    }

    public Notebook findById(int id) {
        Criteria criteria = getSession().createCriteria(Notebook.class);
        criteria.add(Restrictions.eq("notebookId", id));
        Notebook notebook = (Notebook)criteria.uniqueResult();
        return notebook;//(Notebook)criteria.uniqueResult();
    }

    public List<Notebook> findByBrand(String brand) {
        Criteria criteria = getSession().createCriteria(Notebook.class);
        criteria.add(Restrictions.eq("brand", brand));
        return (List<Notebook>)criteria.list();
    }

    public List<Notebook> findByProcessor(String processor) {
        Criteria criteria = getSession().createCriteria(Notebook.class);
        criteria.add(Restrictions.eq("processor", processor));
        return (List<Notebook>)criteria.list();
    }

    public List<Notebook> findByRAM(int ram) {
        Criteria criteria = getSession().createCriteria(Notebook.class);
        criteria.add(Restrictions.eq("ram", ram));
        return (List<Notebook>)criteria.list();
    }

    public List<Notebook> findByHDD(int hdd) {
        Criteria criteria = getSession().createCriteria(Notebook.class);
        criteria.add(Restrictions.eq("hdd", hdd));
        return (List<Notebook>)criteria.list();
    }

    public List<Notebook> findByVideo(String videocard) {
        Criteria criteria = getSession().createCriteria(Notebook.class);
        criteria.add(Restrictions.eq("video", videocard));
        return (List<Notebook>)criteria.list();
    }

    public  List<Notebook> findAll() {
        Criteria criteria = getSession().createCriteria(Notebook.class);
        return (List<Notebook>)criteria.list();
    }
}
