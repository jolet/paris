import React from "react";

export default class extends React.Component {

  submit(event){
    event.preventDefault();
    console.log("Forma poslana");
  }

  render() {

    return (
      <form method="GET" action="events" onSubmit={this.submit}>
         <h4><strong>Filtriraj</strong></h4>
         <div className="form-group row">
           <label className="col-sm-2 form-control-label" htmlFor="name">Naziv događaja:</label>
           <div className="col-sm-4">
             <input type="text" name="name" id="name" className="form-control" />
           </div>
         </div>
         <div className="form-group row">
           <label className="col-sm-2 form-control-label" htmlFor="category">Vrsta događaja:</label>
           <div className="col-sm-4">
           <select className="form-control">
             <option defaultValue>Odaberi</option>
             <option value="prvi">Prvi</option>
             <option value="drugi">Drugi</option>
           </select>
           </div>
         </div>
         <div className="form-group row">
           <label className="col-sm-2 form-control-label" htmlFor="date">Datum:</label>
           <div className="col-sm-4">
             <input type="date" name="date" id="date" className="form-control" />
           </div>
         </div>
         <input type="submit" className="btn btn-primary" defaultValue="Filtriraj" />
       </form>
    );
  }
}
