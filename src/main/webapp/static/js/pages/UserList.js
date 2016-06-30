import React from "react";

import User from '../components/User';

export default class UserList extends React.Component {

  constructor(props){
    super(props);
      this.state = {response:[]};
    }

  componentDidMount(){
    $.ajax({
      url: 'http://localhost:8080/users',
      context: this,
      dataType: 'json',
      type: 'GET'
    }).done(function (data){
      this.setState({response: data});
      console.log(JSON.stringify(this.state.response));
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
