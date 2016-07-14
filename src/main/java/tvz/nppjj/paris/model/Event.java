package tvz.nppjj.paris.model;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT", schema = "PARIS")
public class Event {

    @Id
    @Column(name = "ID_EVENT")
    @GeneratedValue
    private Long       id;

    @Column(name = "NAME")
    private String     name;

    @Column(name = "ADDRESS")
    private String     location;

    @Column(name = "CITY")
    private String     city;

    @Column(name = "DATE")
    private Date       date;

    @Column(name = "DESCRIPTION")
    private String     description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "PICTURE")
    private String     picture;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORY")
    private Category   category;

    @ManyToOne
    @JoinColumn(name = "ID_USER")
    private User       user;

    @Column(name = "NUMBER_OF_VIEWS")
    private long       numberOfViews;

    @Column(name = "NUMBER_OF_TICKETS_BOUGHT")
    private long       numberOfTicketsBought;

    @Column(name = "ACTIVE")
    private boolean    active;

    // getters and setters

    public void setNumberOfViews(long numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    public void setNumberOfTicketsBought(long numberOfTicketsBought) {
        this.numberOfTicketsBought = numberOfTicketsBought;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getNumberOfViews() {
        return numberOfViews;
    }

    public void incrementNumberOfViews() {
        this.numberOfViews++;
    }

    public Long getNumberOfTicketsBought() {
        return numberOfTicketsBought;
    }

    public void incrementNumberOfTicketsBought() {
        this.numberOfTicketsBought++;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
