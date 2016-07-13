package tvz.nppjj.paris.model.dto;

import java.math.BigDecimal;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class TicketCommand {

    @Range(min = 0, message = "Enter price...(0 or more)")
    private BigDecimal price;

    private boolean    isValidated;

    private Long       idUser;

    private Long       idEvent;

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the isValidated
     */
    public boolean getIsValidated() {
        return isValidated;
    }

    /**
     * @param isValidated
     *            the isValidated to set
     */
    public void setIsValidated(boolean isValidated) {
        this.isValidated = isValidated;
    }

    /**
     * @return the idUser
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * @param idUser
     *            the idUser to set
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the idEvent
     */
    public Long getIdEvent() {
        return idEvent;
    }

    /**
     * @param idEvent
     *            the idEvent to set
     */
    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }
}
