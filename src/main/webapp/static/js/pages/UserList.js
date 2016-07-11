import React from "react";

import User from '../components/User';

export default class UserList extends React.Component {

  constructor(props){
    super(props);
      this.state = {response:[]};
    }

  componentDidMount(){
    // var self = this;
    //
    // var header = new Headers();
    //
    //   var init = {
    //     headers: header,
    //     method: 'GET',
    //     mode: 'cors',
    //   }
    //
    //   var url = new Request('http://localhost:8080/users');
    //
    //   fetch(url, init)
    //   .then((response) => response.json())
    //   .then((responseData) => {
    //     var jsonResponse = JSON.stringify(responseData);
    //     self.setState({response: jsonResponse});
    //   });

    $.ajax({
      url: localStorage.getItem("environmentPrefix") + '/users',
      context: this,
      dataType: 'json',
      type: 'GET',
      beforeSend: function(request){
        request.setRequestHeader("Authorization", localStorage.getItem("token"));
      }
    }).done(function (data){
      this.setState({response: data});
    });
  }

  render() {
    return (
      <table class="table table-striped">
        <thead>
          <tr>
            <td>id</td>
            <td>Korisničko ime</td>
            <td>Email</td>
            <td>Rola</td>
            <td>Tel. broj</td>
            <td>Uredi</td>
            <td>Obriši</td>
          </tr>
        </thead>
        <tbody>
          { this.state.response.map((usersAPI, i) => <User key={i} user={usersAPI} />)  }
        </tbody>
      </table>
    );
  }
}
