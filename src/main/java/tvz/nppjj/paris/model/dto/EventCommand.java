package tvz.nppjj.paris.model.dto;

import java.math.BigDecimal;
import java.sql.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class EventCommand {

    private Long       id;

    @NotBlank
    @Size(min = 5, max = 150, message = "Must be at least 5 characters")
    private String     name;

    @NotBlank(message = "We need location!")
    private String     location;

    @NotBlank(message = "Which city? Ati?(Chad)")
    private String     city;

    private Date       date;

    private String     description;

    private String     picture;

    private BigDecimal price;

    private Long       idCategory;
    
    private Long       idUser;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
