import React from "react";
import { Link } from "react-router";

export default class EventUDButtons extends React.Component {

    constructor(props){
        super(props);

        this.clickDelete = this.clickDelete.bind(this);
    }

    clickDelete(event){

        console.log("obrisat cu event s id " + this.props.event.id);

        var headers = new Headers({
            "Content-type": "application/json",
            "Authorization": localStorage.getItem("token")
        });

        var init = {
            method: 'GET',
            headers: headers,
            mode: 'cors',
        }

        var url = new Request(localStorage.getItem("environmentPrefix") + '/events/delete/' + this.props.event.id);

        fetch(url, init)
            .then(() => {
                location.reload();
            });
    }


    render() {

        const {event} = this.props;

        return (
            <div>
                <Link className="btn btn-warning btn-sm" to={"editEvent/" + event.id} >Izmijeni event</Link>
                <p></p>
                <button className="btn btn-danger btn-sm" onClick={this.clickDelete} to="#" >Obriši event</button>
                <p></p>
            </div>
        );
    }
}
