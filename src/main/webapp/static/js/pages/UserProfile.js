import React from "react";
import ReactDOM from 'react-dom';
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";

export default class UserProfile extends React.Component {

	constructor(props){
	    super(props);
	    this.state = {response:[]};
	  }

	  componentDidMount(){
		    $.ajax({
		      url: 'http://localhost:8080/users/{id}',
		      context: this,
		      dataType: 'json',
		      type: 'GET'
		    }).done(function (data){
		      this.setState({response: data});
		      // console.log(JSON.stringify(this.state.response));
		    });
		  }
  render() {

	  
    return (
    		<div class="panel panel-info">
    		<div class="panel-heading">Josip Perić</div>
    		<div className="row">
		     <div className="form-group col-sm-5">
		
		     </div>
		   </div>
    		  <div className="row">
    		     <div className="form-group col-sm-5">
    		     <b>&nbsp;  E-mail: </b>ejosip93@gmail.com
    		     </div>
    		   </div>
    		   <div className="row">
  		     <div className="form-group col-sm-5">
  		
  		     </div>
  		   </div>
      		  <div className="row">
      		     <div className="form-group col-sm-5">
      		   <b>&nbsp;  Phone number: </b>099/9999999
      		     </div>
      		   </div>
      		 <div className="row">
		     <div className="form-group col-sm-5">
		
		     </div>
		   </div>
    		  <div className="row">
    		     <div className="form-group col-sm-5">
    		     <b>&nbsp;  Account: </b>500.00
    		     </div>
    		   </div>
    		
    	      
    	     

      </div>
    
    );
  }
}
