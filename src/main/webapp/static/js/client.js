import React from "react";
import ReactDOM from "react-dom";
import { Router, Route, IndexRoute, hashHistory } from "react-router";

import Layout from "./pages/Layout";
import Homepage from "./pages/Homepage";
import Events from './pages/Events';
import About from './pages/About'
import NewEvent from './pages/NewEvent'
import Registration from './pages/Registration'

const app = document.getElementById('app');

ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={Layout}>
      <IndexRoute component={Homepage}></IndexRoute>
      <Route path="events" name="events" component={Events}></Route>
      <Route path="about" name="about" component={About}></Route>
      <Route path="newevent" name="newevent" component={NewEvent}></Route>
      <Route path="registration" name="registration" component={Registration}></Route>
    </Route>
  </Router>,
app);
