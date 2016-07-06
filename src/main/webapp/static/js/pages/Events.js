import React from "react";

import Event from '../components/Event';
import Category from '../components/Category'

export default class Events extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      response:[],
      categoryResponse:[],
      name: '',
      category: '',
      date: '',
    }

    this.handleChange = this.handleChange.bind(this);
    this.submit = this.submit.bind(this);
    }

  componentDidMount(){
    $.ajax({
      url: 'http://localhost:8080/category',
      context: this,
      dataType: 'json',
      type: 'GET'
    }).done(function (data){
      this.setState({categoryResponse: data});
    });

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

  handleChange(name, event){
    this.setState({[event.target.name]: event.target.value});
    console.log({[event.target.name]: event.target.value});
  }

  submit(event){
    event.preventDefault();
    console.log("Forma poslana");
    console.log(this.state);
    //ako je category 0, ocisti tu varijablu, ne salji nista

  }


  render() {
    return (
      <div>

        <form onSubmit={this.submit.bind(this)}>
           <h4><strong>Filtriraj</strong></h4>
           <div className="form-group row">
             <label className="col-sm-2 form-control-label" htmlFor="name">Naziv događaja:</label>
             <div className="col-sm-4">
               <input pattern="[A-Za-z0-9-_]{5,150}" title="Broj znakova mora biti viši od 5! Smiju se koristiti slova i brojevi te znakovi - i _" type="text" name="name" id="name" className="form-control" value={this.state.name} onChange={this.handleChange.bind(this, 'name')}/>
             </div>
           </div>
           <div className="form-group row">
             <label className="col-sm-2 form-control-label" htmlFor="category">Vrsta događaja:</label>
             <div className="col-sm-4">
             <select className="form-control" name="category" onChange={this.handleChange.bind(this, 'type')}>
               <option value="0">Odaberite</option>
                {this.state.categoryResponse.map((categoryAPI, i) => <Category key={i} category={categoryAPI} />)}
             </select>
             </div>
           </div>
           <div className="form-group row">
             <label className="col-sm-2 form-control-label" htmlFor="date">Datum:</label>
             <div className="col-sm-4">
               <input type="date" name="date" id="date" className="form-control" onChange={this.handleChange.bind(this, 'date')}/>
             </div>
           </div>
           <input type="submit" className="btn btn-primary" defaultValue="Filtriraj" />
         </form>

        <hr/>
        <div class="row events-event">
          { this.state.response.map((eventAPI, i) => <Event key={i} event={eventAPI} />)  }
        </div>
      </div>
    );
  }
}
