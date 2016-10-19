import React from 'react';

// container for all components on the page
let HomeView = React.createClass({
  render() {

    console.log("HomeView props", this.props);

    return(
      <div className="home-view">
        <h1 className="logotype">life pro tips</h1>
      </div>
    );
  }
});

// quote/tip

// save this one

// kiosk button
let KisokButton = React.createClass({
  render() {

    return(
      <div className="kisok-button">
      </div>
    );
  }
});

// social media share
let SocialMediaLinks = React.createClass({
  render() {

    return(
      <div className="social-media-links">
      </div>
    );
  }
});

// ad space
let AdWrapper = React.createClass({
  render() {

    return(
      <div className="ad-wrapper">
      </div>
    );
  }
});

let LifeProTipWrapper = React.createClass({
  render() {
    return(
      <div className="life-pro-tip-wrapper">
      </div>
    );
  }
});


let LifeProTip = React.createClass({
  render() {
    return(
      <div className="life-pro-tip-item">
        <h1 className="life-pro-tip-headline"></h1>
      </div>
    );
  }
});

// donate button
let DonateButton = React.createClass({
  render() {

    return(
      <div className="donate-btn">
      </div>
    );
  }
});

export default HomeView;

// let AdWrapper = React.createClass({
//   render() {
//
//     return(
//       <div className="">
//       </div>
//     );
//   }
// });
