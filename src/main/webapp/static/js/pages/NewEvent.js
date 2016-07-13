import React from "react";
import Router from 'react-router';
import ReactDOM from 'react-dom';
import Category from '../components/Category'

export default class NewEvent extends React.Component {

// u inputu i constructoru postaviti ista imena varijabli
    constructor(props){
        super(props);
        this.state = {
            name: '',
            location: '',
            city: '',
            date: '',
            description: '',
            picture: '',
            price: '',
            idCategory: '1',
            idUser: '',
            time: '',
            website: '',
            ticketNumber: '',
            categoryResponse: []
        };

        this.handleChange = this.handleChange.bind(this);
        this.submit = this.submit.bind(this);
    }

    componentDidMount(){
        $.ajax({
            url: localStorage.getItem("environmentPrefix") + '/category',
            context: this,
            dataType: 'json',
            type: 'GET'
        }).done(function (data){
            this.setState({categoryResponse: data});
        });
    }

    handleChange(name, event){
        this.setState({[event.target.name]: event.target.value});
        console.log({[event.target.name]: event.target.value});
    }

    submit(event){
        event.preventDefault();
        console.log("Forma poslana");
        console.log(this.state);

        var headers = new Headers({
            "Content-type": "application/json",
            "Authorization": localStorage.getItem("token")
        });
        var body = JSON.stringify({
            name: this.state.name,
            location: this.state.location,
            city: this.state.city,
            date: this.state.date,
            description: this.state.description,
            picture: this.state.picture,
            price: this.state.price,
            time: this.state.time,
            website: this.state.website,
            ticketNumber: this.state.ticketNumber,
            idCategory: parseInt(this.state.idCategory),
            idUser: parseInt(localStorage.getItem("id"))
        });

        var init = {
            method: 'POST',
            headers: headers,
            body: body,
            mode: 'cors',
        };

        var url = new Request(localStorage.getItem("environmentPrefix") + '/events/save');

        console.log(JSON.stringify(init));

        fetch(url, init).then(function(response){
            if(response.status == 200 && response.ok == true){
                location.href = '/#/events';
            }

            return response;
        })
            .then((response) => response.json())
            .then((responseData) => {
                console.log(responseData);
                alert(responseData.errors[0].field + ' ' + responseData.errors[0].defaultMessage);
            });
    }


    render() {
        return (
            <div>
                <h2><strong>Novi događaj</strong></h2>
                <form onSubmit={this.submit} className="neweventform">
                    <div className="neweventformdiv">
                    <div className="row">
                        <div className="form-group col-sm-4">
                            Naziv događaja:
                            <input pattern="[A-Za-z0-9-_]{5,150}" title="Broj znakova mora biti viši od 5! Smiju se koristiti slova i brojevi te znakovi - i _" type="text" name="name" id="name" className="form-control" required onChange={this.handleChange.bind(this, 'name')}/>
                        </div>
                        <div className="form-group col-sm-4 col-sm-offset-3">
                            Datum početka:
                            <input type="date" name="date" id="date" className="form-control" required onChange={this.handleChange.bind(this, 'date')} />
                        </div>
                    </div>

                    <div className="row">
                        <div className="form-group col-sm-4">
                            Kategorija:
                            <select name="idCategory" className="form-control" required onChange={this.handleChange.bind(this, 'idCategory')}>
                                {this.state.categoryResponse.map((categoryAPI, i) => <Category key={i} category={categoryAPI} />)}
                            </select>
                        </div>
                        <div className="form-group col-sm-4 col-sm-offset-3">
                            Vrijeme početka:
                            <input type="time" name="time" required className="form-control" onChange={this.handleChange.bind(this, 'time')}/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="form-group col-sm-4">
                            Adresa:
                            <input type="text" name="location" id="location" className="form-control"  required onChange={this.handleChange.bind(this, 'location')}/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="form-group col-sm-4">
                            Grad:
                            <input pattern="[A-Za-z]{2,}" title="Samo slova dopuštena" type="text" name="city" id="city" className="form-control" required onChange={this.handleChange.bind(this, 'city')}/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="form-group col-sm-6">
                            Opis događaja:<br />
                            <textarea name="description" id="description" rows={5} className="form-control" defaultValue={""}  required onChange={this.handleChange.bind(this, 'description')}/>
                        </div>
                    </div>

                    <hr/>

                    <div className="row">
                        <div className="form-group col-sm-7">
                            URL slike:
                            <input type="url" name="picture" id="picture" className="form-control" onChange={this.handleChange.bind(this, 'picture')}/>
                        </div>
                        <div className="form-group col-sm-7">
                            Web adresa:
                            <input type="url" name="website" className="form-control" onChange={this.handleChange.bind(this, 'website')}/>
                        </div>
                    </div>

                    <hr/>

                    <div className="row">
                        <div className="form-group col-sm-3 col-sm-offset">
                            Broj ulaznica:
                            <input type="number" min={0} name="ticketNumber" className="form-control" onChange={this.handleChange.bind(this, 'ticketNumber')}/>
                        </div>
                    </div>

                    <div className="row">
                        <div className="form-group col-sm-3 col-sm-offset">
                            Cijena ulaznice:
                            <input type="number" step="any" min={0} name="price" id="price" className="form-control" required onChange={this.handleChange.bind(this, 'price')} />
                        </div>
                    </div>

                    <div className="row">
                        <input type="submit" id="btnSaveEvent" defaultValue="Spremi događaj" className="btn btn-primary pull-right" />
                    </div>
                    </div>
                </form>
            </div>
        );
    }
}
