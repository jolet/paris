import React from "react";

import Event from '../components/Event';
import Category from '../components/Category';
import { Modal } from 'react-bootstrap';

export default class Events extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      response:[],
      categoryResponse:[],
      name: '',
      category: '',
      date: new Date(0).toJSON().slice(0,10),
        showModal: false
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
      //console.log(JSON.stringify(this.state.response));
    });
  }

  handleChange(name, event){
    this.setState({[event.target.name]: event.target.value});
    console.log({[event.target.name]: event.target.value});
  }

  submit(event){
    event.preventDefault();
    console.log("Forma poslana");

    //ako je category 0, ocisti tu varijablu, ne salji nista
      if(!this.state.category)
          this.setState({
              category: ''
          });

      console.log(this.state);

      $.ajax({
          url: 'http://localhost:8080/eventsFilter',
          context: this,
          dataType: 'json',
          type: 'GET',
          data:{
              'name': this.state.name,
              'categoryId': this.state.category,
              'date': this.state.date
          }
      }).done(function (data){
          console.log(data);
          this.setState({response: data});
      });
  }

    openModal(){
        //ako nije ulogiran, prebaci ga na login screen, spremi url da ga znas vratiti
        if(! localStorage.getItem('username')){
            localStorage.setItem('url', window.location.href);
            location.href = '/#/login';
        }
        this.setState({showModal: true});
    }
    closeModal(){
        this.setState({showModal: false});
    }

    handleModalChange(event){
        this.setState({totalCost: event.target.value * 180})

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
        <Modal show={this.state.showModal}
                 onHide={this.closeModal}
                 bsSize="small">
              <Modal.Header>
                  <Modal.Title>Kupovina karte</Modal.Title>
              </Modal.Header>
              <Modal.Body>
                  <table>
                      <tbody>
                      <tr>
                          <td>
                              <label for="ticketCategory">Tip karte:</label>
                          </td>
                          <td>
                              <select id="ticketCategory">
                                  <option value="reg">Regular</option>
                                  <option value="vip">Vip</option>
                              </select>
                          </td>
                      </tr>
                      <tr>
                          <td>
                              <label for="ticketAmount">Količina:</label>
                          </td>
                          <td>
                              <input type="number" id="ticketAmount" defaultValue="1" onChange={this.handleModalChange}/>
                          </td>
                      </tr>
                      <tr>
                          <td>Sveukupno:</td>
                          <td id="ticketTotalBillAmount">{this.state.totalCost}kn</td>
                      </tr>
                      </tbody>
                  </table>
              </Modal.Body>
              <Modal.Footer>
                  <button>Potvrdi kupnju</button>
                  <button onClick={this.closeModal}>Odustani</button>
              </Modal.Footer>
        </Modal>
      </div>
    );
  }
}
