import React from "react";
import { Link } from "react-router";

import Footer from "../components/layout/Footer";
import Nav from "../components/layout/Nav";

export default class Layout extends React.Component {

  constructor(){
    super();

    // ako je aplikacija na webpackovom dev serveru, spremi link na api u localStorage
    console.log(location.href);
    if(location.href.startsWith("http://localhost:8100/")){
      localStorage.setItem("environmentPrefix", "http://localhost:8080");
    }else{
      localStorage.setItem("environmentPrefix", "");
    }
  }

  render() {
    const { location } = this.props;
    const containerStyle = {
      marginTop: "60px"
    };
    return (
      <div>

        <Nav location={location} />

        <div class="container" style={containerStyle}>
          <div class="row">
            <div class="col-lg-12">

              {this.props.children}

            </div>
          </div>
          <Footer/>
        </div>
      </div>

    );
  }
}
