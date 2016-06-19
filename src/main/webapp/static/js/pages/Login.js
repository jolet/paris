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

            fetch(url, init).then(function(response){
              console.log(response);
              if(response.status == 200 && response.ok == true){
                console.log("tu prihvati id od korisnika i spremi i localstorage");
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
     <input type="text" name="username" className="form-control" required onChange={this.handleChange.bind(this, 'username')} />
     </div>
     </div>
     <div className="row">
     <div className="form-group col-sm-5">
     Lozinka:
     <input type="password" name="password" id="pass1" className="form-control" required onChange={this.handleChange.bind(this, 'password')}/>
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
