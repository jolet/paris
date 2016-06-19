package tvz.nppjj.paris.model;

import java.time.LocalDate;

/**
 * 
 * Example event entity mock.
 * 
 * @author josip.kovacek
 *
 */
public class EventMock {

    private Long      id;

    private String    name;

    private String    location;

    private LocalDate date;

    // getters and setters
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
