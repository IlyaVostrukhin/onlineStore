package net.onlineStore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product extends AbstractEntity<Long> {
    private static final long serialVersionUID = 5325378435705376554L;

    @Id
    @Column
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column
    private String description;

    @Column(name = "image_link", nullable = false)
    private String imageLink;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "id_category")
    private String category;

    @Column(name = "id_producer")
    private String producer;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
