import React from 'react';
import _ from 'underscore';
import Slider from 'react-slick';
// import * as FontAwesome from 'react-icons/fa';
// import FaAngleLeft from 'react-icons/fa/angle-left';
// import FaAngleRight from 'react-icons/fa/angle-right';

var w = Math.max(document.documentElement.clientWidth, window.innerWidth || 0);
var h = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);

// container for all components on the page
let HomeView = React.createClass({
  getInitialState() {

    var initialLPTBatch = _.toArray(this.props.data.subreddit.threads);

    return {
      activeLPT: initialLPTBatch[0],
      allLPTs: initialLPTBatch
    }
  },

  componentWillMount() {

  },

  render() {

  // console.log("this.props.data.subreddit", this.props.data.subreddit, "activeLPT", this.props.data.subreddit.threads, this.state);
  // console.log("HomeView props", this.props);

  if (w < 1024) {
    var MobileAd = <AdMobileWrapper />
  } else if (w > 1024) {
    var MobileAd = false;
  }

  var settings = {
    // dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    // prevArrow: LPTControlLeft,
    // nextArrow: LPTControlRight
  };

  var initialLPTBatch = _.toArray(this.props.data.subreddit.threads);

  var activeGallery = initialLPTBatch.map(function(lpt, i){
    // console.log(lpt);

    return(
      <div key={i}>
        <LifeProTip data={this.props.data} routes={this.props.routes} activeLPT={this.state.activeLPT} lpt={lpt} />
      </div>
    )
  }.bind(this));

  // console.log("activeGallery", activeGallery);

    return(
      <div className="container-fluid lpt-home-view">
        <div className="row ">
          <div className="col-sm-12 ">
            <h1 className="lpt-logotype">life pro tips</h1>
          </div>
        </div>
        <div className="row ">
          <div className="col-sm-12 ">
              <Slider {...settings}>
                {activeGallery}
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


const LPTControlLeft = React.createClass({
  render: function() {

    return(
      <div className="lpt-control-left">
        <span>prev</span>
      </div>
    );
  }
});

const LPTControlRight = React.createClass({
  render: function() {

    return(
      <div className="lpt-control-right">
        <span>next</span>
      </div>
    );
  }
});

// quote/tip
let LifeProTip = React.createClass({
  render() {
    // console.log("LifeProTip", this.props, this.props.lpt.title);

    return(
      <div className="lpt-life-pro-tip-wrapper">
        <h1 className="lpt-life-pro-tip" >
          {this.props.lpt.title}
        </h1>
        <h2 className="lpt-author">
          {"-" + this.props.lpt.author}
        </h2>
        <h3 className="lpt-category">
          {this.props.link_flair_text}
        </h3>
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
