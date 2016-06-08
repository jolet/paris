import React from "react";

export default class Registration extends React.Component {

  submit(event){
    event.preventDefault();
    console.log("Forma poslana");
  }

  handleChange(event){ 
  var pass1 = document.getElementById("pass1").value;
  var pass2 = event.target.value;
  if (pass1 != pass2) {
      document.getElementById("pass1").style.borderColor = "#E34234";
      document.getElementById("pass2").style.borderColor = "#E34234";
      $('#btnSaveRegistration').prop('disabled', true);
  }
  else {
	  document.getElementById("pass1").style.borderColor = "#0000FF";
      document.getElementById("pass2").style.borderColor = "#0000FF";
      $('#btnSaveRegistration').prop('disabled', false);
  }
  }
  
  render() {
    return (
    		
      <div>
  <h2><strong>Registracija</strong></h2>
  <form method="POST" action="events"  onSubmit={this.submit}>
    <div className="row">
      <div className="form-group col-sm-5">
        Korisničko ime:
        <input type="text" pattern="^(?=.{4,16}$).*$" title="Korisničko ime mora sadržavati raspon znakova 4-16" name="name" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-5">
        E-Mail:
        <input type="email" name="email" className="form-control" />
      </div> 
    </div>
    <div className="row">
      <div className="form-group col-sm-5">
        Broj mobitela:
        <input type="tel" pattern="[0-9]{3}/[0-9]{7}" title="Format broja mora biti tipa ***/******* " name="phone" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-5">
      Lozinka:
          <input type="password" pattern="^(?=.{8,16}$)(?=.*[0-9]+.*)(?=.*[A-Z]+.*)[A-Za-z0-9.-_]*$" title="Lozinka mora imati barem jedno veliko slovo i broj te smije sadržavati posebne znakove [-_.] u rasponu znakova 8-16" name="password" id="pass1" className="form-control" />
      </div>
    </div>
 <div className="row">
    <div className="form-group col-sm-5">
    Potvrdi lozinku:
        <input onChange={this.handleChange} type="password" name="password_confirm" className="form-control" id="pass2" oninput="check(this)" />
    </div>
  </div>

  
   
    <div className="row">
      <input type="submit" id="btnSaveRegistration" defaultValue="Registriraj se" className="btn btn-primary pull-right" />
    </div>
  </form>
</div>
    );
  }
}
