import React from 'react';
import {RouteHandler} from 'react-router';
import data from './../data';
import Firebase from 'firebase';
import ReactFireMixin from 'reactfire';
import TransitionGroup from 'react-addons-css-transition-group';



let App = React.createClass({

  mixins:[ReactFireMixin],

  getInitialState() {
    return {
      data: data
    };
  },

  componentWillMount() {
    var ref = new Firebase("https://lifeprotips-75424.firebaseio.com/");
    this.bindAsObject(ref, "data", function(error){
      console.log(error);
    });
    // console.log("data from firebase check: ", this.state.data);
  },

  render() {

    let key = this.props.routes[1].name;

    //https://lifeprotips-75424.firebaseio.com/

    console.log("App props", this.props, "app state", this.state);

    return(
      <div className="wrapper">
          {React.cloneElement(this.props.children, {key: key, data: data})}
      </div>
    );
  }

});

export default App;
