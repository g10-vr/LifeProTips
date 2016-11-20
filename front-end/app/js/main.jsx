import 'bootstrap-webpack';
import './../styles/main';

import React from 'react';
import {render} from 'react-dom';
import {Router, hashHistory} from 'react-router';

import routes from './router';

render(<Router history={hashHistory} routes={routes} />, document.getElementById("app"));
