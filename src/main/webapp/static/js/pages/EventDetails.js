import React from "react";
import ReactDOM from 'react-dom';
import { Modal } from 'react-bootstrap';

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
      showModal: false,
      totalCost: 0,
      ticketVIP: 0,
      price: 0,
      amount: 1
    };



    this.closeModal = this.closeModal.bind(this);
    this.openModal = this.openModal.bind(this);
    this.handleAmountChange = this.handleAmountChange.bind(this);
    this.handleTicketTypeChange = this.handleTicketTypeChange.bind(this);
    this.submit = this.submit.bind(this);
  }

  componentDidMount(){
    this.setState({showModal: false});
    var urlId = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
    urlId = urlId.substr(0, urlId.indexOf("?"));

    $.ajax({
      url: localStorage.getItem("environmentPrefix") + '/events/' + urlId,
      context: this,
      dataType: 'json',
      type: 'GET'
    }).done(function (data) {
      this.setState({response: data, totalCost: data.price, ticketVIP: (data.price + data.price * 0.2)});
      console.log(JSON.stringify(this.state.response));


    })
  }
  openModal(){
    //ako nije ulogiran, prebaci ga na login screen, spremi url da ga znas vratiti
    if(! localStorage.getItem('username')){
      localStorage.setItem('url', window.location.href);
      location.href = '/#/login';
    }
    this.setState({showModal: true});
    this.setState({price: this.state.response.price});

  }
  closeModal(){
    this.setState({showModal: false});
  }

  handleAmountChange(event){
    if(event.target.value < 1){
      event.target.value = 1;
    }
    this.setState({amount: event.target.value, totalCost: (event.target.value * this.state.price)});
  }
  handleTicketTypeChange(event){
    this.setState({price: event.target.value, totalCost: (event.target.value * this.state.amount)});
  }

  submit(){
    event.preventDefault();

    var data = {
      'idEvent': this.state.response.id,
      'price': this.state.price,
      'idUser': parseInt(localStorage.getItem("id")),
    };

    $.ajax({
      url: localStorage.getItem("environmentPrefix") + '/tickets/save',
      context: this,
      dataType: 'json',
      type: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json; charset=utf-8'
      },
      data: JSON.stringify(data)
    }).done(function (data) {
      console.log(data);
      this.closeModal();

    })

  }

  render() {
    const event = this.state.response;

    // console.log(event);
    var eventDate = event.date != 0 ? new Date(event.date) : new Date();


    return (
        <div class="event-details row">
          <img class="col span_6_of_12" src="http://placehold.it/480x480"/>
          <div class="event-details-text col span_6_of_12">
            <h1>{event.name}</h1>
            <p><i class="icon-clock"></i>{eventDate.toLocaleDateString()}, {showLeadingZero(eventDate.getHours())}:{showLeadingZero(eventDate.getMinutes())}</p>
            <p><i class="icon-location"></i>{event.location}</p>
            <pre>
              {event.description}
            </pre>
            <hr/>
            <h2>Ulaznice</h2>
            <table>
              <tbody>
              <tr>
                <th>Tip ulaznice</th>
                <th>Cijena</th>
              </tr>
              <tr>
                <td>VIP</td>
                <td>{this.state.ticketVIP} kn</td>
              </tr>
              <tr>
                <td>Regularna ulaznica</td>
                <td>{event.price} kn</td>
              </tr>
              </tbody>
            </table>
            <button id="btnBuy" onClick={this.openModal}><i class="icon-tags"></i>Kupii</button>
          </div>
          <Modal show={this.state.showModal}
                 onHide={this.closeModal}>
            <Modal.Header>
              <Modal.Title>Kupovina karte</Modal.Title>
            </Modal.Header>
            <form>
              <Modal.Body>

                <table>
                  <tbody>
                  <tr>
                    <td>
                      <label for="ticketCategory">Tip karte:</label>
                    </td>
                    <td>
                      <select id="ticketCategory" onChange={this.handleTicketTypeChange}>
                        <option value={event.price}>Regular</option>
                        <option value={this.state.ticketVIP}>Vip</option>
                      </select>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <label for="ticketAmount">Količina:</label>
                    </td>
                    <td>
                      <input type="number" id="ticketAmount" defaultValue="1" onChange={this.handleAmountChange}/>
                    </td>
                  </tr>
                  <tr>
                    <td>Sveukupno:</td>
                    <td id="ticketTotalBillAmount">{this.state.totalCost.toFixed(2)}kn</td>
                  </tr>
                  </tbody>
                </table>
              </Modal.Body>
              <Modal.Footer>
                <button onClick={this.submit}>Potvrdi kupnju</button>
                <button onClick={this.closeModal}>Odustani</button>
              </Modal.Footer>
            </form>
          </Modal>
        </div>
    );
  }
}


function showLeadingZero (number) {
  return number > 9 ? number.toString() : ("0") + number;
}