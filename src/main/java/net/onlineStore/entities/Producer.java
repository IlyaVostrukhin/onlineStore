package net.onlineStore.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producer")
public class Producer extends AbstractEntity<Integer> {
    private static final long serialVersionUID = -1235557212217927123L;

    @Id
    @Column
    private Integer id;

    @Column(nullable = false, length = 60)
    private String name;

    @Column(name = "product_count", nullable = false)
    private int productCount;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}
