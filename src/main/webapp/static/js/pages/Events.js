import React from "react";

import Event from '../components/Event';
import FilterForm from '../components/FilterForm';

export default class Events extends React.Component {

  constructor(props){
    super(props);
      this.response = [];
  };

  componentDidMount(){
    $.ajax({
      url: 'http://localhost:8080/events',
      context: this,
      dataType: 'json',
      type: 'GET'
    }).done(function (data){
      this.setState({response: data});
    });
  }

  render() {
    const AllEvents =
    [
    {"id":1,
    "name":"Euro2016",
    "location":"Stade de France",
    "city":"Paris",
    "date":"2016-06-10",
    "description":"Utakmica Eura 2016.",
    "price":51.55,
    "picture":"//"
    ,"category":{"id":1,"name":"Nogomet"}},

    {"id":2,
    "name":"Humanitarni koncert",
    "location":"Slavonska avenija 7",
    "city":"Zagreb",
    "date":"2016-06-21",
    "description":"Koncert u svrhu skupljanja sredstava za siromašne",
    "price":11.51,
    "picture":"//",
    "category":{"id":2,"name":"Glazba"}},

    {"id":3,
    "name":"Inception",
    "location":"Branimirova",
    "city":"Zagreb",
    "date":"2016-06-10",
    "description":"Prikazivanje filma s glavnom ulogom Leonarda Di Capria",
    "price":111.11,
    "picture":"//",
    "category":{"id":3,"name":"Filmovi"}}
  ].map((eventAPI, i) => <Event key={i} event={eventAPI} />)

    return (
      <div>
        <FilterForm />
        <hr/>
        <div class="row events-event">
          {AllEvents}
        </div>
      </div>
    );
  }
}
