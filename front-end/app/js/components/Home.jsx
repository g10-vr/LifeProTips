import React from 'react';
import _ from 'underscore';
import Slider from 'react-slick';

var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

// container for all components on the page
let HomeView = React.createClass({
  getInitialState() {

    var initialLPTBatch = _.toArray(this.props.data.subreddit.threads);

    return {
      activeLPT: initialLPTBatch[0]
    }
  },

  render() {

  console.log("this.props.data.subreddit", this.props.data.subreddit, "activeLPT", this.props.data.subreddit.threads, this.state);
  console.log("HomeView props", this.props);

  if (w < 1024) {
    var MobileAd = <AdMobileWrapper />


  } else if (w > 1024) {
    var MobileAd = false;
  }

  var settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1
  };
}

    return(
      <div className="container-fluid lpt-home-view">
        <div className="row ">
          <div className="col-sm-12 ">
            <h1 className="lpt-logotype">life pro tips</h1>
          </div>
        </div>
        <div className="row ">
          <div className="col-sm-12 ">
            <LifeProTip data={this.props.data} routes={this.props.routes} activeLPT={this.state.activeLPT} />
              <Slider {...settings}>
                <div><h3>1</h3></div>
                <div><h3>2</h3></div>
                <div><h3>3</h3></div>
                <div><h3>4</h3></div>
                <div><h3>5</h3></div>
                <div><h3>6</h3></div>
              </Slider>
            </div>
          </div>
        <div className="row ">
          <div className="col-sm-12 ">
          {MobileAd}
          </div>
        </div>
      </div>
    );
  }
});

// quote/tip
let LifeProTip = React.createClass({
  render() {
    console.log("LifeProTip", this.props, this.props.activeLPT.title);

    return(
      <div className="lpt-life-pro-tip-wrapper">
        <h1 className="lpt-life-pro-tip" >
          {this.props.activeLPT.title}
        </h1>
        <h2 className="lpt-author">
          {this.props.activeLPT.author}
        </h2>
      </div>
    );
  }
});

// save this one

// kiosk button
let KisokButton = React.createClass({
  render() {

    return(
      <div className="lpt-kisok-mode-button">
      </div>
    );
  }
});

// social media share
let SocialMediaLinks = React.createClass({
  render() {

    return(
      <div className="lpt-social-media-links-group">
      </div>
    );
  }
});

// ad space
let AdMobileWrapper = React.createClass({
  render() {

    const ad_styles = {
        display: "inline-block",
        width: "320px",
        height:"100px"
    };

    return(
      <div className="lpt-ad-wrapper">
        <script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
        <ins class="adsbygoogle lpt-ad-ins"
             style={ad_styles}
             data-ad-client="ca-pub-9696196243360163"
             data-ad-slot="8257127628">
        </ins>
        <script>
        (adsbygoogle = window.adsbygoogle || []).push({});
        </script>
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
