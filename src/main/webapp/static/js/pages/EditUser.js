import React from "react";


export default class EditUser extends React.Component {

	constructor(props){
	    super(props);
	    this.state = {response:[]};

      this.handleChange = this.handleChange.bind(this);
      this.handlePassCheck = this.handlePassCheck.bind(this);
      this.submit = this.submit.bind(this);
	  }

	  componentDidMount(){
      console.log('comp did mount');
		  var url = $(location).attr('href');
      console.log(url);
		  var id = url.substring(url.lastIndexOf('/') + 1);
		  var putanja='http://localhost:8080/users/'+id;
		    $.ajax({
		      url: putanja,
		      context: this,
		      dataType: 'json',
		      type: 'GET',
                beforeSend: function(request){
                    request.setRequestHeader("Authorization", localStorage.getItem("token"));
                }
		    }).done(function (data){
		      this.setState({response: data});
          console.log("podaci o korisniku");
		      console.log(JSON.stringify(this.state.response));
		    });
		  }

    handleChange(name, event){
       this.setState({[event.target.name]: event.target.value});
       console.log({[event.target.name]: event.target.value});
     }

    handlePassCheck(event){
      var pass1 = document.getElementById("pass1").value;
      var pass2 = event.target.value;
      if (pass1 != pass2) {
          document.getElementById("pass1").style.borderColor = "#E34234 ";
          document.getElementById("pass2").style.borderColor = "#E34234 ";
          $('#btnSaveRegistration').prop('disabled', true);
      }
      else {
           document.getElementById("pass1").style.borderColor = "#0000FF ";
          document.getElementById("pass2").style.borderColor = "#0000FF ";
          $('#btnSaveRegistration').prop('disabled', false);
      }
    }

    submit(event){
          event.preventDefault();
          console.log("Forma poslana");
          console.log(this.state);

          // var headers = new Headers({
          //    "Content-type": "application/json",
          //    "Authorization": localStorage.getItem("token")
          //    });
          // var body = JSON.stringify({
          //   email: this.state.email,
          //   password: this.state.password,
          //   phoneNumber: this.state.phoneNumber,
          // });
          //
          // var init = {
          //   method: 'POST',
          //   headers: headers,
          //   body: body,
          //   mode: 'cors',
          // }
          //
          // var url = new Request('http://localhost:8080/register');
          //
          // fetch(url, init).then(function(response){
          //   console.log(response);
          //   if(response.status == 200 && response.ok == true){
          //
          //     location.href = '/#/login';
          //   }
          // });
        }

  render() {
    return (
      <div>
        <h2><strong>Uređivanje profila korisnika: {this.state.response.username}</strong></h2>
        <form onSubmit={this.submit}>
          <div className="row">
              <div className="form-group col-sm-5">
                Korisničko ime:
                <input type="text" pattern="^(?=.{4,16}$).*$" title="Korisničko ime mora sadržavati raspon znakova 4-16" name="username" className="form-control" onChange={this.handleChange.bind(this, 'username')} placeholder={this.state.response.username}/>
              </div>
          </div>
          <div className="row">
              <div className="form-group col-sm-5">
                E-Mail:
                <input type="email" name="email" className="form-control" onChange={this.handleChange.bind(this, 'email')} placeholder={this.state.response.email}/>
          </div>
          </div>
          <div className="row">
              <div className="form-group col-sm-5">
                Broj mobitela:
                <input type="tel" name="phoneNumber" pattern="[0-9]{3}/[0-9]{7}" title="Format broja mora biti tipa ***/******* " className="form-control" onChange={this.handleChange.bind(this, 'phoneNumber')} placeholder={this.state.response.phone_number}/>
              </div>
          </div>
          <div className="row">
              <div className="form-group col-sm-5">
              Lozinka:
                  <input type="text" pattern="^(?=.{8,16}$)(?=.*[0-9]+.*)(?=.*[A-Z]+.*)[A-Za-z0-9.-_]*$" title="Lozinka mora imati barem jedno veliko slovo i broj te smije sadržavati posebne znakove [-_​.] u rasponu znakova 8-16" name="password" id="pass1" className="form-control" onChange={this.handleChange.bind(this, 'password')} placeholder={this.state.response.password}/>
          </div>
            </div>
         <div className="row">
            <div className="form-group col-sm-5">
            Potvrdi lozinku:
                <input onChange={this.handlePassCheck} type="text" name="password_confirm" className="form-control" id="pass2" oninput="check(this)" placeholder={this.state.response.password}/>
              </div>
         </div>

    <div className="row">
      <input type="submit" id="btnSaveEdit" defaultValue="Spremi podatke" className="btn btn-primary pull-right" />
    </div>
  </form>
 </div>

    );
  }
}
