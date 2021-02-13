package net.onlineStore.entities;

import javax.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "id_category")
    private Category category;

    @OneToOne
    @JoinColumn(name = "id_producer")
    private Producer producer;

    @Column(nullable = false)
    private Integer amount;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }
}
