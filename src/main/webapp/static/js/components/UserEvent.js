import React from "react";
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";

export default class UserEvent extends React.Component {

    constructor(props){
        super(props);

        this.clickDelete = this.clickDelete.bind(this);
    }

    clickDelete(){

        console.log("obrisat cu event s id " + this.props.userevent.id);

        var headers = new Headers({
            "Content-type": "application/json",
            "Authorization": localStorage.getItem("token")
        });

        var init = {
            method: 'GET',
            headers: headers,
            mode: 'cors',
        }

        var url = new Request(localStorage.getItem("environmentPrefix") + '/events/delete/' + this.props.userevent.id);

        fetch(url, init)
            .then(() => {
                location.reload();
            });
    }

    render() {

        const {userevent} = this.props;
        var eventDate = new Date(userevent.date);

        return (
            <div className="event-information">
                <div className="row">
                    <div className="col-sm-2" id="eventDate">
                        <p style={{fontSize: '48px', fontWeight: '700'}}>{eventDate.getDate()}.</p>
                        <p>{getMonthName(eventDate.getMonth())}</p>
                    </div>
                    <div className="col-sm-3">
                        <img src={userevent.picture} />
                    </div>
                    <div className="col-sm-5">
                        <Link to={"events/" + userevent.id} ><h4><strong>{userevent.name}</strong></h4></Link>
                        <p>{userevent.description}</p>
                    </div>
                    <div className="col-sm-2" id="eventListLinks">
                        <Link className="btn btn-success btn-sm" to={"editEvent/" + userevent.id} >Promijeni</Link>
                        <p></p>
                        <button className="btn btn-danger btn-sm" onClick={this.clickDelete} to="#" >Obriši event</button>

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