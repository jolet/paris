package tvz.nppjj.paris.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TICKETTYPE", schema = "PARIS")
public class TicketType {

    @Id
    @Column(name = "ID_TICKETTYPE")
    @GeneratedValue
    private Long       id;

    @Column(name = "NAME")
    private String     name;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "AMOUNT")
    private Long       amount;

    @ManyToOne
    @JoinColumn(name = "ID_EVENT")
    private Event      event;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
