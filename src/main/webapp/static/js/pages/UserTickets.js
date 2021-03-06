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
		      type: 'GET',
				beforeSend: function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				}
		    }).done(function (data){
		      this.setState({response: data});
		      console.log(JSON.stringify(this.state.response));
		     
		    });
		  }
	  
	 
	  
  render() {
console.log(this.state);


	  
    return (
    		<div class="col span_12_of_12">
				<h1>Moje ulaznice</h1>
    		 <div class="row tickets-ticket">
             { this.state.response.map((ticketAPI, i) => <Ticket key={i} ticket={ticketAPI} />)  }
           </div>
		     
		     
		   
		     </div>
    
    );
  }
}
