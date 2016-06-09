import React from "react";

export default class extends React.Component {
  render() {

    const {category} = this.props;
    console.log(category);

    return (
      <option value={category.id}>{category.name}</option>
    );
  }
}
