import React from "react";
import ReactDOM from 'react-dom';

export default class extends React.Component {



    constructor(props){
        super(props);
        const loadingMsg = "Loading...";
        var res = {name: loadingMsg,
            description: loadingMsg,
            date: 0,
            location: loadingMsg,
            picture: "",
            price: 0
        };

        this.state = {response: res,
            totalCost: 0,
            ticketVIP: 0,
            price: 0,
            amount: 1
        };
    }

    componentDidMount(){
        var urlId = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
        urlId = urlId.substr(0, urlId.indexOf("?"));

        // $.ajax({
        //     url: localStorage.getItem("environmentPrefix") + '/events/' + urlId,
        //     context: this,
        //     dataType: 'json',
        //     type: 'GET'
        // }).done(function (data) {
        //     this.setState({response: data, totalCost: data.price, ticketVIP: (data.price + data.price * 0.2)});
        //     console.log(JSON.stringify(this.state.response));
        //
        //
        // })
    }




    render() {

        return (
            <p>Hello</p>
        );
    }
}


function showLeadingZero (number) {
    return number > 9 ? number.toString() : ("0") + number;
}