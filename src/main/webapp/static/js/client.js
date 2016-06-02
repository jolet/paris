import React from "react";
import ReactDOM from "react-dom";
import { Router, Route, IndexRoute, hashHistory } from "react-router";

import Layout from "./pages/Layout";
import Homepage from "./pages/Homepage";
import Events from './pages/Events';
import About from './pages/About'

const app = document.getElementById('app');

ReactDOM.render(
  <Router history={hashHistory}>
    <Route path="/" component={Layout}>
      <IndexRoute component={Homepage}></IndexRoute>
      <Route path="events" name="events" component={Events}></Route>
      <Route path="about" name="about" component={About}></Route>
    </Route>
  </Router>,
app);
