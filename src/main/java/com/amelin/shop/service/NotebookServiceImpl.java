package com.amelin.shop.service;

import com.amelin.shop.dao.NotebookDAO;
import com.amelin.shop.model.Notebook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("NotebookService")
@Transactional
public class NotebookServiceImpl implements NotebookService {
    @Autowired
    private NotebookDAO dao;

    public void saveNotebook(Notebook notebook) {
        dao.saveNotebook(notebook);
    }

    public void updateNotebook(Notebook notebook) {
        dao.updateNotebook(notebook);
    }

    public void deleteNotebook(Notebook notebook) {
        dao.deleteNotebook(notebook);
    }

    public Notebook findById(int id) {
        return dao.findById(id);
    }

    public List<Notebook> findByBrand(String brand) {
        return dao.findByBrand(brand);
    }

    public List<Notebook> findByProcessor(String processor) {
        return dao.findByProcessor(processor);
    }

    public List<Notebook> findByRAM(int ram) {
        return dao.findByRAM(ram);
    }

    public List<Notebook> findByHDD(int hdd) {
        return dao.findByHDD(hdd);
    }

    public List<Notebook> findByVideo(String videocard) {
        return dao.findByVideo(videocard);
    }

    public List<Notebook> findAll() {
        return dao.findAll();
    }
}
