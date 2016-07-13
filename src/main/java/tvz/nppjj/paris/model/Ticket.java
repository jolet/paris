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
@Table(name = "TICKET", schema = "PARIS")
public class Ticket {

    @Id
    @Column(name = "ID_TICKET")
    @GeneratedValue
    private Long       id;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "IS_VALIDATED")
    private Boolean    isValidated;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User       user;

    @ManyToOne
    @JoinColumn(name = "ID_EVENT")
    private Event      event;

    // getters&setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getIsValidated() {
        return isValidated;
    }

    public void setIsValidated(Boolean isValidated) {
        this.isValidated = isValidated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
