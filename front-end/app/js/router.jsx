import React from 'react';
import {Route, IndexRoute} from 'react-router';
import App from './components/App';
import Home from './components/Home';

let routes = (
  <Route name="app" path="/" component={App}>

    <IndexRoute component={Home} />

  </Route>
);

export default routes;
