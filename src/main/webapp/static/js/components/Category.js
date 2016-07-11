import React from "react";
import ReactDOM from 'react-dom';

export default class Category extends React.Component {
  render() {

    const {category} = this.props;
    // console.log(category);

    return (
      <option value={category.id}>{category.name}</option>
    );
  }
}
