import React from "react";

export default class NewEvent extends React.Component {

  submit(event){
    event.preventDefault();
    console.log("Forma poslana");
  }

  render() {
    return (
      <div>
  <h2><strong>Novi događaj</strong></h2>
  <form method="POST" action="events"  onSubmit={this.submit}>
    <div className="row">
      <div className="form-group col-sm-4">
        Naziv događaja:
        <input type="text" name="name" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Datum početka:
        <input type="date" name="startDate" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Vrijeme početka:
        <input type="time" name="startTime" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-4">
        Adresa:
        <input type="text" name="address" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Datum završetka:
        <input type="date" name="endDate" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Vrijeme završetka:
        <input type="time" name="endTime" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-4">
        Grad:
        <input type="text" name="city" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-5">
        Opis događaja:<br />
        <textarea name="description" rows={5} className="form-control" defaultValue={""} />
      </div>
      <div className="form-group col-sm-5 col-sm-offset-2">
        URL slike:
        <input type="url" name="picture" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-3">
        Kontakt telefon:
        <input type="tel" name="telephone" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Kontakt email:
        <input type="email" name="email" className="form-control" />
      </div>
      <div className="form-group col-sm-4 col-sm-offset-1">
        Web adresa:
        <input type="url" name="website" className="form-control" />
      </div>
    </div>
    <div className="row">
      <div className="form-group col-sm-3">
        Broj ulaznica:
        <input type="number" min={0} name="number" className="form-control" />
      </div>
      <div className="form-group col-sm-3 col-sm-offset-1">
        Cijena:
        <input type="number" step="any" min={0} name="price" className="form-control" />
      </div>
    </div>
    <div className="row">
      <input type="submit" id="btnSaveEvent" defaultValue="Spremi događaj" className="btn btn-primary pull-right" />
    </div>
  </form>
</div>
    );
  }
}
