import React from "react";
import ReactDOM from 'react-dom';
import { Modal } from 'react-bootstrap';

export default class extends React.Component {

  constructor(props){
    super(props);
    this.state = {response:[],
      showModal: false,
      totalCost: 0};

    this.closeModal = this.closeModal.bind(this);
    this.openModal = this.openModal.bind(this);
    this.handleChange = this.handleChange.bind(this);
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
      this.setState({response: data});
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
  }
  closeModal(){
    this.setState({showModal: false});
  }

  handleChange(event){
    this.setState({totalCost: event.target.value * 180})

  }
  
  render() {
    const event = this.state.response;
    console.log(Object.getOwnPropertyNames(event).length);
    const loadingMsg = "Loading...";

    if (Object.getOwnPropertyNames(event).length > 1){
      eventTitle = event.name;
      eventLoc = event.location;
      eventDesc = event.description;
      eventDate = new Date(event.date);
    }else{
      var eventTitle = loadingMsg;
      var eventDesc = loadingMsg;
      var eventDate = new Date();
      var eventLoc = loadingMsg;
    }


    return (
        <div class="event-details row">
          <img class="col span_6_of_12" src="http://placehold.it/480x480"/>
          <div class="event-details-text col span_6_of_12">
            <h1>{eventTitle}</h1>
            <p><i class="icon-clock"></i>{eventDate.toLocaleDateString()}, {showLeadingZero(eventDate.getHours())}:{showLeadingZero(eventDate.getMinutes())}</p>
            <p><i class="icon-location"></i>{eventLoc}</p>
            <pre>
              {eventDesc}
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
                  <td>280 kn</td>
                </tr>
                <tr>
                  <td>Regularna ulaznica</td>
                  <td>120 kn</td>
                </tr>
              </tbody>
            </table>
            <button id="btnBuy" onClick={this.openModal}><i class="icon-tags"></i>Kupi</button>
          </div>
          <Modal show={this.state.showModal}
                 onHide={this.closeModal}>
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
                    <input type="number" id="ticketAmount" defaultValue="1" onChange={this.handleChange}/>
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


function showLeadingZero (number) {
  return number > 9 ? number.toString() : ("0") + number;
}