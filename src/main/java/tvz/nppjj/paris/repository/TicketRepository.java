package tvz.nppjj.paris.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.nppjj.paris.model.Ticket;



public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
