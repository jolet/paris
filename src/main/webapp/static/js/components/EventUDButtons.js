import React from "react";
import { Link } from "react-router";

export default class EventUDButtons extends React.Component {

    constructor(props){
        super(props);

        this.clickDelete = this.clickDelete.bind(this);
    }

    clickDelete(event){
        console.log(this.state);
        console.log("obrisat cu event s id " + this.props.event.id);

        var headers = new Headers({
            "Content-type": "application/json",
            "Authorization": localStorage.getItem("token")
        });

        var body = JSON.stringify({
            id: this.props.event.id
        });

        var init = {
            method: 'DELETE',
            headers: headers,
            body: body,
            mode: 'cors',
        }

        var url = new Request('http://localhost:8080/events');

        fetch(url, init)
            .then((response) => response.json())
            .then((responseData) => {
                if(responseData.status == 500){
                    alert(responseData.message);
                }else{
                    location.href = '/#/events';
                }
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
