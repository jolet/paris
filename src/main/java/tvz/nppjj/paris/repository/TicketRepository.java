package tvz.nppjj.paris.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.nppjj.paris.model.Ticket;



public interface TicketRepository extends JpaRepository<Ticket, Long> {

	List<Ticket> findTicketByUserId(Long idUser);
}
