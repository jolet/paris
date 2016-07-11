import React from "react";
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";

export default class Ticket extends React.Component {
  
	
	render() {

    const {ticket} = this.props;

    var letterStyle;
    console.log(ticket.isValidated)
    if(ticket.isValidated){
    		letterStyle = { backgroundColor: "#00FF00"};
    }
    else{
    	letterStyle = { backgroundColor: "#FF0000"};
    }
    
    return (
      <div className="ticket-information">
        
      <br/>
      
      <div className="row">
      <div className="form-group col-sm-1"></div>
      <div style={letterStyle} className="form-group col-sm-1">
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      </div>
          
      <div className="form-group col-sm-5">
	     <img className="homepage-imgs" src="http://crooksandliars.com/files/primary_image/15/05/carlos-santana.jpg"/>
	     </div> 
	     <div className="form-group col-sm-4">
	     <h2>{ticket.event.name}</h2>
	     <b>Lokacija: </b>{ticket.event.city}, {ticket.event.location}<br/>
	     <b>Cijena: </b>{ticket.price}<br/>
	     <b>{ticket.event.date}</b>
	     </div> 
	     
	     
        </div>
     
        
        </div>
    );
  }
}
