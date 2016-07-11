import React from "react";
import Ticket from '../components/Ticket';
export default class UserTickets extends React.Component {

	constructor(props){
	    super(props);
	    this.state = {response:[]};
	  }

	  componentDidMount(){
		  var url = $(location).attr('href');
		  var id = url.substring(url.lastIndexOf('/') + 1);
		  var putanja= localStorage.getItem("environmentPrefix") + '/tickets/user/'+id;
		    $.ajax({
		      url: putanja,
		      context: this,
		      dataType: 'json',
		      type: 'GET'
		    }).done(function (data){
		      this.setState({response: data});
		      console.log(JSON.stringify(this.state.response));
		     
		    });
		  }
	  
	 
	  
  render() {
console.log(this.state);


	  
    return (
    		<div class="panel panel-info">
    		
    		<div class="panel-heading"><b>{localStorage.getItem('username')}</b></div>
    		
    		<br/>
    		
    		 
    	
    		 <div class="row tickets-ticket">
             { this.state.response.map((ticketAPI, i) => <Ticket key={i} ticket={ticketAPI} />)  }
           </div>
		     
		     
		   
		     </div>
    
    );
  }
}
