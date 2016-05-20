package tvz.nppjj.paris.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLE", schema = "PARIS")
public class Role {
    
    @Id
    @Column(name = "ID_ROLE")
    @GeneratedValue
    private Long id;
    
    @Column(name = "NAME")
    private String name;
    
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
