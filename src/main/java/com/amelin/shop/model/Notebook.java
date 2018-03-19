package com.amelin.shop.model;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by amelin on 4/7/17.
 */
@Entity
@Table(name = "notebook", schema = "public", catalog = "shop")
@Transactional
public class Notebook {
    private int notebookId;
    private String brand;
    private String model;
    private String processor;
    private Integer ram;
    private Integer hdd;
    private String video;
    private BigDecimal screen;
    private BigInteger price;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<Order>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notebook_id")
    public int getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(int notebookId) {
        this.notebookId = notebookId;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "processor")
    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    @Basic
    @Column(name = "ram")
    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    @Basic
    @Column(name = "hdd")
    public Integer getHdd() {
        return hdd;
    }

    public void setHdd(Integer hdd) {
        this.hdd = hdd;
    }

    @Basic
    @Column(name = "video")
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Basic
    @Column(name = "screen")
    public BigDecimal getScreen() {
        return screen;
    }

    public void setScreen(BigDecimal screen) {
        this.screen = screen;
    }

    @Basic
    @Column(name = "price")
    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }
}
