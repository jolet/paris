import React from "react";
import Router from 'react-router';

export default class Login extends React.Component {

      constructor(props){
            super(props);
            this.state = {
              username: '',
              password: '',
            }
            this.handleChange = this.handleChange.bind(this);
            this.submit = this.submit.bind(this);
          }

      submit(event){
            event.preventDefault();
            console.log("Prijava ce se izvrsiti");
            console.log(this.state);

            var headers = new Headers({"Content-type": "application/json"});
            var body = JSON.stringify({
              username: this.state.username,
              password: this.state.password,
            });

            var init = {
              method: 'POST',
              headers: headers,
              body: body,
              mode: 'cors',
            }

            var url = new Request('http://localhost:8080/login');

            fetch(url, init)
            .then((response) => response.json())
            .then((responseData) => {
              if(responseData.status == 500){
                alert(responseData.message);
              }else{
                localStorage.setItem("token", 'Bearer ' + responseData.token);
                localStorage.setItem("username", responseData.userDto.username);
                localStorage.setItem("email", responseData.userDto.email);
                localStorage.setItem("id", responseData.userDto.id);
                localStorage.setItem("role", responseData.userDto.role);
                location.href = '/#/';
              }
            });
          }


      handleChange(name, event){
        this.setState({[event.target.name]: event.target.value});
        console.log({[event.target.name]: event.target.value});
      }

 render() {
   return (

     <div>
     <h2><strong>Prijava</strong></h2>
     <form onSubmit={this.submit}>
     <div className="row">
     <div className="form-group col-sm-5">
     Korisničko ime:
     <input type="text" pattern="^(?=.{4,16}$).*$" title="Korisničko ime sadržava 4-16 znakova" name="username" className="form-control" required onChange={this.handleChange.bind(this, 'username')} />
     </div>
     </div>
     <div className="row">
     <div className="form-group col-sm-5">
     Lozinka:
     <input type="password" pattern="^(?=.{6,16}$).*$" title="Lozinka sadržava 8-16 znakova" name="password" id="pass1" className="form-control" required onChange={this.handleChange.bind(this, 'password')} />
     </div>
     </div>
     <div className="row">
     <input type="submit" id="btnUserLogin" defaultValue="Prijava" className="btn btn-primary pull-right" />
     </div>
     </form>
     </div>
   );
 }
}
