package net.onlineStore.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "order_item")
public class OrderItem extends AbstractEntity<Long> {
    private static final long serialVersionUID = -7000569842503480638L;

    @Id
    @SequenceGenerator(name = "ORDER_ITEM_ID_GENERATOR", sequenceName = "ORDER_ITEM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ITEM_ID_GENERATOR")
    @Column
    private Long id;

    @Column(name = "id_order")
    private Long idOrder;

    @OneToOne
    @JoinColumn(name = "id_product")
    private Product product;

    @Column
    private int count;

    @Column
    private BigDecimal price;

    @Transient
    @ManyToMany(mappedBy = "items")
    private Set<Order> orders;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderItem() {
        super();
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return String.format("OrderItem [id=%s, idOrder=%s, product=%s, count=%s]",
                getId(), idOrder, product, count);
    }
}
