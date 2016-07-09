import React from "react";
import UserEvent from '../components/UserEvent';

export default class UserEvents extends React.Component {

	constructor(props){
	    super(props);
	    this.state = {response:[]};
	  }

	  componentDidMount(){
		  var url = $(location).attr('href');
		  var id = url.substring(url.lastIndexOf('/') + 1);
		  var putanja='http://localhost:8080/events/user/'+id;
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
    		<div>
    		<div class="row events-event">
            { this.state.response.map((usereventAPI, i) => <UserEvent key={i} userevent={usereventAPI} />)  }
          </div>
      </div>
    
    );
  }
}
