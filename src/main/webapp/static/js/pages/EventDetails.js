import React from "react";
import ReactDOM from 'react-dom';
import { Modal } from 'react-bootstrap';

export default class extends React.Component {

  constructor(props){
    super(props);
    this.state = {response:[]};
    this.state = {showModal: false};
    this.closeModal = this.closeModal.bind(this);
    this.openModal = this.openModal.bind(this);
    this.handleChange = this.handleChange.bind(this);
    this.state = {totalCost: 0};
  }

  componentDidMount(){
    this.setState({showModal: false});
    var urlId = window.location.href.substr(window.location.href.lastIndexOf("/")+1);
    urlId = urlId.substr(0, urlId.indexOf("?"));
    console.log(urlId);
    $.ajax({
      url: 'http://localhost:8080/event/' + urlId,
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

    return (
        <div class="event-details row">
          <img class="col span_6_of_12" src="http://placehold.it/480x480"/>
          <div class="event-details-text col span_6_of_12">
            <h1>Lorem Ipsum</h1>
            <p><i class="icon-clock"></i>20/04/2016, 16:00 h</p>
            <p><i class="icon-location"></i>Trg bana Josipa Jelačića 8, Zagreb</p>
            <pre>
              Lorem ipsum dolor sit amet, consectetur adipiscing elit.
              Sed ased turpis id quam lobortis tincidunt a in urna.
              Nunc eu sagittis justo, nec dignissim mauris.
              Pellentesque lacinia sit amet nulla quis eleifend.
              Etiam neque dui, placerat eget pellentesque sit amet, blandit id nibh.
              Vestibulum ante ipsum primis in faucibus orci luctus et ultrices osuere cubilia Curae;
              Vestibulum tempus nulla et purus sagittis euismod.
              Donec justo ex, aliquam eget dui eu, tempor iaculis neque.
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

