import React from "react";
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";
import { Modal } from 'react-bootstrap';

export default class Event extends React.Component {

  constructor(props){
    super(props);
    this.state = {showModal: false};
    this.openModal = this.openModal.bind(this);
  }

  openModal(){
    //ako nije ulogiran, prebaci ga na login screen, spremi url da ga znas vratiti
    if(! localStorage.getItem('username')){
      localStorage.setItem('url', window.location.href);
      location.href = '/#/login';
    }
    this.setState({showModal: true});
  }

  render() {

    const {event} = this.props;
    var eventDate = new Date(event.date);

    return (
      <div className="event-information">
        <div className="row">
          <div className="col-sm-2" id="eventDate">
            <p style={{fontSize: '48px', fontWeight: '700'}}>{eventDate.getDate()}.</p>
            <p>{getMonthName(eventDate.getMonth())}</p>
          </div>
          <div className="col-sm-3">
            <img src={event.picture} />
          </div>
          <div className="col-sm-5">
            <h4><strong>{event.name}</strong></h4>
            <p>{event.description}</p>
          </div>
          <div className="col-sm-2" id="eventListLinks">
            <Link className="btn btn-success btn-sm" to={"events/" + event.id} >Više o eventu</Link>
            <button className="btn btn-success btn-sm" onClick={this.openModal}>Kupi ulaznic</button>
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

