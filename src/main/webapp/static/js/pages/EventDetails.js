import React from "react";
import ReactDOM from 'react-dom';

export default class extends React.Component {

  constructor(props){
    super(props);
    this.state = {response:[]}
  }

  // componentDidMount(){
  //   $.ajax({
  //     url: 'http://localhost:8080/event/' + this.props.id,
  //     context: this,
  //     dataType: 'json',
  //     type: 'GET'
  //   }).done(function (data) {
  //     this.setState({response: data});
  //     console.log(JSON.stringify(this.state.response));
  //   })
  // }

  render() {

    return (
        <div class="event-details row">
          <img class="col span_6_of_12" src="http://placehold.it/480x480"/>
          <div class="event-details-text col span_6_of_12">
            <h1>Lorem Ipsum</h1>
            <p><i class="icon-clock"></i>20/04/2016, 16:00 h</p>
            <p><i class="icon-location"></i>Trg bana Josipa Jelačića 4, Zagreb</p>
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
            </table>
            <button id="btnBuy"><i class="icon-tags"></i>Kupi</button>
          </div>
        </div>
    );
  }
}
