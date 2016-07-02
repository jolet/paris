import React from "react";
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";


export default class User extends React.Component {
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
        <td><Link to="#" class="btn btn-danger">Obriši</Link></td>
      </tr>
    );
  }
}
