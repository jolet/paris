import React from "react";
import Router from 'react-router';
import ReactDOM from 'react-dom';
import Category from '../components/Category'

export default class NewEvent extends React.Component {

// u inputu i constructoru postaviti ista imena varijabli
  constructor(props){
    super(props);
    this.state = {
      name: '',
      location: '',
      city: '',
      date: '',
      description: '',
      picture: '',
      price: '',
      idCategory: '1',
      categoryResponse: []
    };

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
  }

  handleChange(name, event){
    this.setState({[event.target.name]: event.target.value});
    console.log({[event.target.name]: event.target.value});
  }

  submit(event){
    event.preventDefault();
    console.log("Forma poslana");
    console.log(this.state);

    var headers = new Headers({"Content-type": "application/json"});
    var body = JSON.stringify({
      name: this.state.name,
      location: this.state.location,
      city: this.state.city,
      date: this.state.date,
      description: this.state.description,
      picture: this.state.picture,
      price: this.state.price,
      idCategory: parseInt(this.state.idCategory)
    });

    var init = {
      method: 'POST',
      headers: headers,
      body: body,
      mode: 'cors',
    }

    var url = new Request('http://localhost:8080/events/save');

    fetch(url, init).then(function(response){
      // console.log(body);
      console.log(response);
      if(response.status == 200 && response.ok == true){

        location.href = '/#/events';
      }
    });
  }


  render() {
    return (
      <div>
  <h2><strong>Novi događaj</strong></h2>
  <form onSubmit={this.submit}>
    <div className="row">
      <div className="form-group col-sm-4">
        Naziv događaja:
        <input type="text" name="name" id="name" className="form-control" required onChange={this.handleChange.bind(this, 'name')}/>
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Datum početka:
        <input type="date" name="date" id="date" className="form-control" required onChange={this.handleChange.bind(this, 'date')} />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Vrijeme početka:
        <input type="time" name="startTime" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-4">
        Adresa:
        <input type="text" name="location" id="location" className="form-control"  required onChange={this.handleChange.bind(this, 'location')}/>
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Datum završetka:
        <input type="date" name="endDate" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Vrijeme završetka:
        <input type="time" name="endTime" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-4">
        Grad:
        <input type="text" name="city" id="city" className="form-control" required onChange={this.handleChange.bind(this, 'city')}/>
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Kategorija:
        <select name="idCategory" className="form-control" required onChange={this.handleChange.bind(this, 'idCategory')}>
        {this.state.categoryResponse.map((categoryAPI, i) => <Category key={i} category={categoryAPI} />)}
        </select>
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-5">
        Opis događaja:<br />
      <textarea name="description" id="description" rows={5} className="form-control" defaultValue={""}  required onChange={this.handleChange.bind(this, 'description')}/>
      </div>
      <div className="form-group col-sm-5 col-sm-offset-2">
        URL slike:
        <input type="url" name="picture" id="picture" className="form-control" required onChange={this.handleChange.bind(this, 'picture')}/>
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-3">
        Kontakt telefon:
        <input type="tel" name="telephone" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Kontakt email:
        <input type="email" name="email" className="form-control" />
      </div>
      <div className="form-group col-sm-4 col-sm-offset-1">
        Web adresa:
        <input type="url" name="website" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-3">
        Broj ulaznica:
        <input type="number" min={0} name="number" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Cijena:
        <input type="number" step="any" min={0} name="price" id="price" className="form-control" required onChange={this.handleChange.bind(this, 'price')} />
      </div>
    </div>
    <div className="row">
      <input type="submit" id="btnSaveEvent" defaultValue="Spremi događaj" className="btn btn-primary pull-right" />
    </div>
  </form>
</div>
    );
  }
}
