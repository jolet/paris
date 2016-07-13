import React from "react";
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";


export default class User extends React.Component {

  constructor(props){
    super(props);

    this.click = this.click.bind(this);
  }

  click(){
    console.log("obrisat cu usera s id " + this.props.user.id);

    var headers = new Headers({
      "Content-type": "application/json",
      "Authorization": localStorage.getItem("token")
    });

    var body = JSON.stringify({
      id: this.props.user.id,
    });

    var init = {
      method: 'DELETE',
      headers: headers,
      body: body,
      mode: 'cors',
    }

    var url = new Request('http://localhost:8080/users');

    fetch(url, init)
    .then((response) => response.json())
    .then((responseData) => {
      if(responseData.status == 500){
        alert(responseData.message);
      }else{
        location.href = '/#/users';
      }
    });
  }

  render() {

    const {user} = this.props;

    return (
      <tr>
        <td>{user.id}</td>
        <td>{user.username}</td>
        <td>{user.email}</td>
        <td>{user.role}</td>
        <td>{user.phoneNumber}</td>
        <td><Link to={'editUser/' + user.id} class="btn btn-warning">Uredi</Link></td>
        <td><button onClick={this.click} to="#" class="btn btn-danger">Obriši</button></td>
      </tr>
    );
  }
}
