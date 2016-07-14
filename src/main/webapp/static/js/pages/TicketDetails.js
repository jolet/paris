import React from "react";
import ReactDOM from 'react-dom';

export default class extends React.Component {



    constructor(props){
        super(props);
        const loadingMsg = "Loading...";
        var data = {name: loadingMsg,
            date: 0,
            location: loadingMsg,
            code: "",
            price: 0,
            ticketType: ""
        };

        this.state = {data: data};
    }

    componentDidMount(){
        var urlId = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
        urlId = urlId.substr(0, urlId.indexOf("?"));

        $.ajax({
            url: localStorage.getItem("environmentPrefix") + '/tickets/' + urlId,
            context: this,
            dataType: 'json',
            type: 'GET'
        }).done(function (data) {
            this.setState({response: data});
            console.log(JSON.stringify(data));
            var ticketType = data.price == data.event.price ? "Regular" : "VIP";

            this.setState({
                name: data.event.name,
                location: data.event.location,
                code: data.code,
                price: data.price,
                ticketType: ticketType,
                date: data.event.date
            });


        })
    }

    render() {

        var eventDate = new Date(this.state.date);

        return (
            <div class="row" id="ticketDetails">
                <div class="col span_8_of_12" id="ticketDetailsText">
                    <h1>{this.state.name}</h1>
                    <p>{this.state.location}</p>
                    <p>{eventDate.toLocaleDateString()}, {showLeadingZero(eventDate.getHours())}:{showLeadingZero(eventDate.getMinutes())}</p>
                    <p>Tip ulaznice: {this.state.ticketType}</p>
                    <p>Cijena: {this.state.price}</p>
                </div>
                <div class="col span_4_of_12">
                    <img src={"data:image/png;base64," + this.state.code}/>
                </div>
            </div>

        );
    }
}


function showLeadingZero (number) {
    return number > 9 ? number.toString() : ("0") + number;
}