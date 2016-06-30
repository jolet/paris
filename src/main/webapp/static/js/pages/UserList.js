import React from "react";

export default class UserList extends React.Component {

  constructor(props){
    super(props);
      this.state = {response:[]};
    }

  componentDidMount(){
  }

  render() {
    return (
      <div>
        lista usera
      </div>
    );
  }
}
