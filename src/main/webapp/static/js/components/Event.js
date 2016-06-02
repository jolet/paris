import React from "react";

export default class extends React.Component {
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
            <a className="pull-right" href={"event/" + event.id}>Vise informacija</a>
          </div>
          <div className="col-sm-2">
            <h4>Cijena ulaznice:<br />{event.price} kn</h4>
            <a className="btn btn-success btn-sm" href="#">Kupi ulaznicu</a>
          </div>
        </div>
      </div>
    );
  }
}
