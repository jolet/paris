import React from "react";
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";

export default class Ticket extends React.Component {
  
	
	render() {

    const {ticket} = this.props;

        var letterStyle;
        var backgroundStyle;
        var validationMsg;
        console.log(ticket.isValidated)
        if(ticket.isValidated){
                // letterStyle = { backgroundColor: "#00FF00"};
            backgroundStyle = { backgroundColor: "rgba(120, 120, 120, 0.2)"};
            validationMsg = "Iskorištena";
        }
        else{
            // letterStyle = { backgroundColor: "#FF0000"};
            backgroundStyle = { backgroundColor: "transparent"};
            validationMsg = "Neiskorištena";
        }
        var eventDate = new Date(ticket.event.date);

        return (
            <div className="event-information" style={backgroundStyle}>
                <div className="row">
                    <div className="col-sm-2" id="eventDate">
                        <p style={{fontSize: '48px', fontWeight: '700'}}>{eventDate.getDate()}.</p>
                        <p>{getMonthName(eventDate.getMonth())}</p>
                    </div>
                    <div className="col-sm-3">
                        <img src={ticket.event.picture} />
                    </div>
                    <div className="col-sm-5">
                        <h4><strong>{ticket.event.name}</strong></h4>
                        <p>{ticket.event.description}</p>
                    </div>
                    <div className="col-sm-2" id="eventListLinks">

                        {localStorage.getItem("role") == "Administrator"
                            ?
                            <EventUDButtons event={ticket.event}/>
                            : null
                        }
                        <p>Status ulaznice: {validationMsg}</p>
                        <Link className="btn btn-success btn-sm" to={"events/" + ticket.event.id} >Više o eventu</Link>
                        <p></p>
                        <Link className="btn btn-success btn-sm" to={"ticket/" + ticket.id} >Pogledaj ulaznicu</Link>
                        <p></p>
                    </div>
                </div>
            </div>
        );
  }
}


function getMonthName(month) {
    var months = ["Siječanj", "Veljača", "Ožujak", "Travanj", "Svibanj", "Lipanj", "Srpanj", "Kolovoz", "Rujan",
        "Listopad", "Studeni", "Prosinac"];
    return months[month];
}

