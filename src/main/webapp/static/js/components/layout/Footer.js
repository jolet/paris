import React from "react";
import { IndexLink, Link, Router, Route, hashHistory } from "react-router";


export default class Footer extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            version: ''
        };
    }

    componentDidMount(){

         $.ajax({
             url:  localStorage.getItem("environmentPrefix") + '/changelog',
             context: this,
             dataType: '',
             type: 'GET'
         }).done(function (data){
            console.log("changelog data ");
            this.setState({version: data});
         });
    }

    render() {
    return (
      <footer>
        <div class="row">
          <div class="col-lg-12">
            <hr />
            <p>Tim Paris 2016 | <span className="build-version"> version: {this.state.version} </span></p>
          </div>
        </div>
      </footer>
    );
    }
}
