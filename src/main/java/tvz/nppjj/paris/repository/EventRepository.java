package tvz.nppjj.paris.repository;



import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import tvz.nppjj.paris.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

	List<Event> findByName(String name);
	List<Event> findByDate(Date date);
	List<Event> findByLocation(String location);
}
