import React from "react";
import { Link } from "react-router";

export default class HomepageEvent extends React.Component {


    render() {

        const {event} = this.props;

        return (
            <div className="col-sm-2 main-page-events">
                <img className="homepage-imgs" src={event.picture}/>
                <div className="text-center"><h4><Link to={"/events/" + event.id}> {event.name} </Link></h4></div>
                <div className="homepage-event-about"> {event.date} </div>
                <div className="homepage-event-about"> {event.description} </div>
            </div>
        );
    }
}
