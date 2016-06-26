import React from "react";
import { IndexLink, Link } from "react-router";

export default class Nav extends React.Component {
  constructor() {
    super()
    this.state = {
      collapsed: true,
    };
  }

  toggleCollapse() {
    const collapsed = !this.state.collapsed;
    this.setState({collapsed});
  }

  logout(){
    localStorage.clear();
    console.log('localstorage clear');
  }

  render() {
    const { location } = this.props;
    const { collapsed } = this.state;
    const homepageClass = location.pathname === "/" ? "active" : "";
    const newEventClass = location.pathname.match(/^\/newevent/) ? "active" : "";
    const eventsClass = location.pathname.match(/^\/events/) ? "active" : "";
    const aboutClass = location.pathname.match(/^\/about/) ? "active" : "";
    const registerClass = location.pathname.match(/^\/register/) ? "active" : "";
    const loginClass = location.pathname.match(/^\/login/) ? "active" : "";
    const logoutClass = location.pathname.match(/^\/logout/) ? "active" : "";
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

            { localStorage.getItem('role') ?
              <span></span>
            :
            <ul class="nav navbar-nav navbar-right">
              <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
              <li class={newEventClass}><Link to="newevent" onClick={this.toggleCollapse.bind(this)}>Novi događaj</Link></li>
              <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
              <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>
              <li class={registerClass}><Link to="registration" onClick={this.toggleCollapse.bind(this)}>Registracija</Link></li>
              <li class={loginClass}><Link to="login" onClick={this.toggleCollapse.bind(this)}>Prijava</Link></li>
            </ul>
            }

            { localStorage.getItem('role') == "Administrator" ?
            <ul class="nav navbar-nav navbar-right">
              <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
              <li class={newEventClass}><Link to="newevent" onClick={this.toggleCollapse.bind(this)}>Novi događaj</Link></li>
              <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
              <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>
              <li class={loginClass}><Link to={"user/"+localStorage.getItem('id')} onClick={this.toggleCollapse.bind(this)}>Korisnički profil</Link></li>
              <li ><Link to="/" onClick={this.toggleCollapse.bind(this), this.logout.bind(this)}>Odjava</Link></li>
  		      </ul>
            :
            <span></span>
            }

            { localStorage.getItem('role') == "Organisator" ?
            <ul class="nav navbar-nav navbar-right">
              <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
              <li class={newEventClass}><Link to="newevent" onClick={this.toggleCollapse.bind(this)}>Novi događaj</Link></li>
              <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
              <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>
              <li ><Link to="/" onClick={this.toggleCollapse.bind(this), this.logout.bind(this)}>Odjava</Link></li>
              <li class={loginClass}><Link to={"user/"+localStorage.getItem('id')} onClick={this.toggleCollapse.bind(this)}>Korisnički profil</Link></li>
            </ul>
            :
            <span></span>
            }

            { localStorage.getItem('role') == "User" ?
            <ul class="nav navbar-nav navbar-right">
              <li class={homepageClass}><IndexLink to="/" onClick={this.toggleCollapse.bind(this)}>Početna</IndexLink></li>
              <li class={eventsClass}><Link to="events" onClick={this.toggleCollapse.bind(this)}>Svi događaji</Link></li>
              <li class={aboutClass}><Link to="about" onClick={this.toggleCollapse.bind(this)}>O Parisu</Link></li>
              <li ><Link to="/" onClick={this.toggleCollapse.bind(this), this.logout.bind(this)}>Odjava</Link></li>
              <li class={loginClass}><Link to={"user/"+localStorage.getItem('id')} onClick={this.toggleCollapse.bind(this)}>Korisnički profil</Link></li>
            </ul>
            :
            <span></span>
            }

  		    </div>
  		  </div>
  		</nav>
    );
  }
}
