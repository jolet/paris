import React from "react";

export default class AlertDanger extends React.Component {

    render() {

        return (
            <div class="alert alert-danger" id="errorAlert">
                <strong>Došlo je do pogreške:</strong> {this.props.error}.
            </div>
        );
    }
}

