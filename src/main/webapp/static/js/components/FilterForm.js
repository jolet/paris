import React from "react";
import ReactDOM from 'react-dom';

export default class extends React.Component {

  constructor(props){
    super(props);
    this.state = {
      name: '',
      type: '',
      date: '',
    }

    this.handleChange = this.handleChange.bind(this);
    this.submit = this.submit.bind(this);
  }

  handleChange(name, event){
    this.setState({[event.target.name]: event.target.value});
    console.log({[event.target.name]: event.target.value});
  }

  submit(event){
    event.preventDefault();
    console.log("Forma poslana");
    console.log(this.state);
  }


  render() {

    return (
      <form onSubmit={this.submit.bind(this)}>
         <h4><strong>Filtriraj</strong></h4>
         <div className="form-group row">
           <label className="col-sm-2 form-control-label" htmlFor="name">Naziv događaja:</label>
           <div className="col-sm-4">
             <input type="text" name="name" id="name" className="form-control" value={this.state.name} onChange={this.handleChange.bind(this, 'name')}/>
           </div>
         </div>
         <div className="form-group row">
           <label className="col-sm-2 form-control-label" htmlFor="category">Vrsta događaja:</label>
           <div className="col-sm-4">
           <select className="form-control" onChange={this.handleChange.bind(this, 'type')}>
             <option defaultValue>Odaberi</option>
             <option value="prvi">Prvi</option>
             <option value="drugi">Drugi</option>
           </select>
           </div>
         </div>
         <div className="form-group row">
           <label className="col-sm-2 form-control-label" htmlFor="date">Datum:</label>
           <div className="col-sm-4">
             <input type="date" name="date" id="date" className="form-control" onChange={this.handleChange.bind(this, 'date')}/>
           </div>
         </div>
         <input type="submit" className="btn btn-primary" defaultValue="Filtriraj" />
       </form>
    );
  }
}
