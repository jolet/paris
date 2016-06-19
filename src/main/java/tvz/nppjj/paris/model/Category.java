package tvz.nppjj.paris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY", schema = "PARIS")
public class Category {

    @Id
    @Column(name = "ID_CATEGORY")
    @GeneratedValue
    private Long   id;

    @Column(name = "NAME")
    private String name;

    // getters & setters

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

}
