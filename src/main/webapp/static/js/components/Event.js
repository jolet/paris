import React from "react";
import { IndexLink, Link } from "react-router";

export default class Event extends React.Component {
  render() {

    const {event} = this.props;

    return (
      <div className="event-information">
        <div className="row">
          <div className="col-sm-2">
            <h3>{event.date}</h3>
          </div>
          <div className="col-sm-3">
            <img src={event.picture} />
          </div>
          <div className="col-sm-5">
            <h4><strong>{event.name}</strong></h4>
            <p>{event.description}</p>
            <Link className="pull-right" to={"event/" + event.id } >Više informacija</Link>
          </div>
          <div className="col-sm-2">
            <h4>Cijena ulaznice:<br />{event.price} kn</h4>
            <Link className="btn btn-success btn-sm" to={"buyTicket/" + event.id} >Kupi ulaznicu</Link>
          </div>
        </div>
      </div>
    );
  }
}
