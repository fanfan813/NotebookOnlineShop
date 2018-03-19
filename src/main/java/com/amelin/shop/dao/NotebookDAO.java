package com.amelin.shop.dao;

import com.amelin.shop.model.Notebook;

import java.util.List;

public interface NotebookDAO {
    void saveNotebook(Notebook notebook);
    void updateNotebook(Notebook notebook);
    void deleteNotebook(Notebook notebook);
    Notebook findById(int id);
    List<Notebook> findByBrand(String brand);
    List<Notebook> findByProcessor(String processor);
    List<Notebook> findByRAM(int ram);
    List<Notebook> findByHDD(int hdd);
    List<Notebook> findByVideo(String videocard);
    List<Notebook> findAll();
}
