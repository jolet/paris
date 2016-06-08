import React from "react";

import Event from '../components/Event';
import FilterForm from '../components/FilterForm';

export default class Events extends React.Component {

  constructor(props){
    super(props);
      this.state = {response:[]};
    }

  componentDidMount(){
    $.ajax({
      url: 'http://localhost:8080/events',
      context: this,
      dataType: 'json',
      type: 'GET'
    }).done(function (data){
      this.setState({response: data});
      // console.log(JSON.stringify(this.state.response));
    });
  }

  render() {
    return (
      <div>
        <FilterForm />
        <hr/>
        <div class="row events-event">
          { this.state.response.map((eventAPI, i) => <Event key={i} event={eventAPI} />)  }
        </div>
      </div>
    );
  }
}
