import React from "react";
import { IndexLink, Link } from "react-router";

export default class Nav extends React.Component {
    constructor() {
        super()
        this.state = {
            collapsed: true,
        };
        this.navigation = this.navigation.bind(this);
    }

    toggleCollapse() {
        const collapsed = !this.state.collapsed;
        this.setState({collapsed});
    }

    logout(){
        localStorage.clear();
        console.log('localstorage clear');
    }

    navigation(){
        const { location } = this.props;
        const { collapsed } = this.state;
        const homepageClass = location.pathname === "/" ? "active" : "";
        const newEventClass = location.pathname.match(/^\/newevent/) ? "active" : "";
        const eventsClass = location.pathname.match(/^\/events/) ? "active" : "";
        const aboutClass = location.pathname.match(/^\/about/) ? "active" : "";
        const registerClass = location.pathname.match(/^\/register/) ? "active" : "";
        const loginClass = location.pathname.match(/^\/login/) ? "active" : "";
        const logoutClass = location.pathname.match(/^\/logout/) ? "active" : "";
        const usersClass = location.pathname.match(/^\/users/) ? "active" : "";
        const navClass = collapsed ? "collapse" : "";


        switch(localStorage.getItem('role')){
            case 'Administrator':
                return      <ul class="nav navbar-nav navbar-right">
                    <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
                    <li class={newEventClass}><Link to="newevent" onClick={this.toggleCollapse.bind(this)}>Novi događaj</Link></li>
                    <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
                    <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>
                    <li class={usersClass}><Link to="users" onClick={this.toggleCollapse.bind(this)}>Korisnici</Link></li>

                    <li class="dropdown dropdownStyle" >
                            <span class="btn btn-sm btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                              {localStorage.getItem('username')}<span class="caret"></span>
                            </span>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li class="divider"></li>
                            <li class={loginClass}><Link to={"user/"+localStorage.getItem('id')} >Korisnički profil</Link></li>
                            <li class="divider"></li>
                            <li ><Link to="/" onClick={this.logout.bind(this)}>Odjava</Link></li>
                        </ul>
                    </li>

                </ul>
                break;
            case 'Organisator':
                return      <ul class="nav navbar-nav navbar-right">
                    <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
                    <li class={newEventClass}><Link to="newevent" onClick={this.toggleCollapse.bind(this)}>Novi događaj</Link></li>
                    <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
                    <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>

                    <li class="dropdown dropdownStyle" >
                            <span class="btn btn-sm btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                              {localStorage.getItem('username')}<span class="caret"></span>
                            </span>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li class="divider"></li>
                            <li class={loginClass}><Link to='#' >Moji događaji</Link></li>
                            <li class="divider"></li>
                            <li class={loginClass}><Link to={"user/"+localStorage.getItem('id')} >Korisnički profil</Link></li>
                            <li class="divider"></li>
                            <li ><Link to="/" onClick={this.logout.bind(this)}>Odjava</Link></li>
                        </ul>
                    </li>

                </ul>
                break;
            case 'User':
                return      <ul class="nav navbar-nav navbar-right">
                    <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
                    <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
                    <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>

                    <li class="dropdown dropdownStyle" >
                            <span class="btn btn-sm btn-default dropdown-toggle" type="button" data-toggle="dropdown">
                              {localStorage.getItem('username')}<span class="caret"></span>
                            </span>
                        <ul class="dropdown-menu dropdown-menu-right">
                            <li class="divider"></li>
                            <li class={loginClass}><Link to={"user/"+localStorage.getItem('id')} >Korisnički profil</Link></li>
                            <li class="divider"></li>
                            <li ><Link to="/" onClick={this.logout.bind(this)}>Odjava</Link></li>
                        </ul>
                    </li>

                </ul>
                break;
            default:
                return      <ul class="nav navbar-nav navbar-right">
                    <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
                    <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
                    <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>
                    <li class={registerClass}><Link to="registration" onClick={this.toggleCollapse.bind(this)}>Registracija</Link></li>
                    <li class={loginClass}><Link to="login" onClick={this.toggleCollapse.bind(this)}>Prijava</Link></li>
                </ul>

        }
    }

    render() {
        const { collapsed } = this.state;
        const navClass = collapsed ? "collapse" : "";

        return (
            <nav class="navbar navbar-inverse">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false" onClick={this.toggleCollapse.bind(this)}>
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="/">Codename-Paris</a>
                    </div>

                    <div class={"navbar-collapse " + navClass} id="bs-example-navbar-collapse-1">

                        {this.navigation()}

                    </div>
                </div>
            </nav>
        );
    }
}
