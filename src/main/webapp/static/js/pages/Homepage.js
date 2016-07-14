import React from "react";
import {Link} from "react-router";

import HomepageEvent from '../components/HomepageEvent';

export default class Homepage extends React.Component {

    constructor(){
        super();

        this.state = {
            response: []
        }
    }

    componentDidMount(){
        var ovaKlasa = this;

        var headers = new Headers({"Content-type": "application/json"});

        var init = {
            method: 'GET',
            headers: headers,
            mode: 'cors',
        }

        var url = new Request(localStorage.getItem("environmentPrefix") + '/eventspage');

        fetch(url, init)
            .then((response) => response.json())
            .then((responseData) => {
                console.log(responseData);
                ovaKlasa.setState({
                    response: responseData.paginatedList
                });
                console.log(ovaKlasa.state);
            });
    }

    render() {
        return (
            <div>
                <h1 className="text-center">Izdvojeni događaji</h1>
                <hr /><br />
                <div className="row">

                    <div className="col-sm-1">
                    </div>

                    {this.state.response.map((eventsAPI, i) => <HomepageEvent key={i} event={eventsAPI} />)}

                </div>
                <br /><hr />
                <div className="text-center"><h2 className="btn btn-default"><Link to="events">POGLEDAJ SVE DOGAĐAJE</Link></h2></div>
            </div>
        );
    }
}
