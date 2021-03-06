import React from "react";
import { Link } from "react-router";

export default class UserProfile extends React.Component {

    constructor(props){
        super(props);
        this.state = {response:[]};
    }

    componentDidMount(){
        var url = $(location).attr('href');
        var id = url.substring(url.lastIndexOf('/') + 1);
        var putanja= localStorage.getItem("environmentPrefix") + '/users/'+id;
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
            //console.log(JSON.stringify(this.state.response));

        });
    }

    handleClick() {
        var id=localStorage.getItem('id');
        location.href = '/#/userTickets/'+id;

    }

    render() {
        console.log(this.state);


        return (
            <div class="panel panel-info">
                <div class="panel-heading"><b>{this.state.response.username}</b></div>
                <div className="row">
                    <div className="form-group col-sm-5">

                    </div>
                </div>
                <div className="row">
                    <div className="form-group col-sm-5">
                        <b>&nbsp;  E-mail  </b>{this.state.response.email}
                    </div>
                </div>
                <div className="row">
                    <div className="form-group col-sm-5">

                    </div>
                </div>
                <div className="row">
                    <div className="form-group col-sm-5">
                        <b>&nbsp;  Phone number: </b>{this.state.response.phone_number}
                    </div>
                </div>
                <div className="row">
                    <div className="form-group col-sm-5">

                    </div>
                </div>
                <div className="row">
                    <div className="form-group col-sm-5">
                        <b>&nbsp;  Account: </b>{this.state.response.account} <b>kn</b>
                    </div>
                </div>

                <div className="row">

             <span className="form-group col-sm-2">
  		   &nbsp;   &nbsp;<Link id="btn-karte" to={"/edituser/" + localStorage.getItem("id")} class="btn btn-warning btn-md">Uredi podatke</Link>
  		     </span>

  		     <span className="form-group col-sm-5">
  		   &nbsp;   &nbsp;<button id="btn-karte" type="button" onClick={this.handleClick} class="btn btn-primary btn-md">Kupljene karte</button>
  		     </span>
                </div>


            </div>

        );
    }
}
