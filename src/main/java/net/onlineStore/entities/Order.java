package net.onlineStore.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends AbstractEntity<Long> {
    private static final long serialVersionUID = 7339561561578606170L;

    @Id
    @SequenceGenerator(name = "ORDER_ID_GENERATOR", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_ID_GENERATOR")
    @Column
    private Long id;

    @Column(name = "id_profile")
    private Long idProfile;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<OrderItem> items;

    @Column
    private String recipient;

    @OneToOne
    @JoinColumn (name="id_order_status")
    private OrderStatus status;

    @Column
    private Timestamp created;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(Long idProfile) {
        this.idProfile = idProfile;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Integer getTotalCount() {
        int count = 0;
        if (items != null) {
            for (OrderItem item : items) {
                count += item.getCount();
            }
        }
        return count;
    }

    public BigDecimal getTotalCost() {
        BigDecimal cost = BigDecimal.ZERO;
        if (items != null) {
            for (OrderItem item : items) {
                cost = cost.add(item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getCount())));
            }
        }
        return cost;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Order [id=%s, idProfile=%s, items=%s, created=%s]",
                getId(), idProfile, items, created);
    }
}
