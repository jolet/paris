import React from "react";
import {Link} from "react-router";

export default class Homepage extends React.Component {
  render() {
    return (
      <div>
        <h1 className="text-center">Izdvojeni događaji</h1>
        <hr /><br />
        <div className="row">
          <div className="col-sm-2 col-xs-offset-1 main-page-events">
            <img className="homepage-imgs" src="http://crooksandliars.com/files/primary_image/15/05/carlos-santana.jpg"/>
            <div className="homepage-event-about">Phasellus velit erat, volutpat quis metus sit amet, efficitur finibus lectus. Sed mi eros, elementum sed dui a, imperdiet fringilla nisi. Curabitur facilisis elementum tortor, malesuada sodales nisi tempor eu. </div>
            <div className="text-center"><h4><Link to="#">Koncert Ime Prezime</Link></h4></div>
          </div>
          <div className="col-sm-2 main-page-events">
            <img className="homepage-imgs" src="http://www.nme.com/images/2015PRODIGY_EM_4_050515.hero.jpg"/>
            <div className="homepage-event-about">Phasellus velit erat, volutpat quis metus sit amet, efficitur finibus lectus. Sed mi eros, elementum sed dui a, imperdiet fringilla nisi. Curabitur facilisis elementum tortor, malesuada sodales nisi tempor eu. </div>
            <div className="text-center"><h4><Link to="#">Koncert Ime Prezime</Link></h4></div>
          </div>
          <div className="col-sm-2 main-page-events">
            <img className="homepage-imgs" src="http://www.droid.hr/wp-content/uploads/2016/05/droidconZG-social-media.png"/>
            <div className="homepage-event-about">Phasellus velit erat, volutpat quis metus sit amet, efficitur finibus lectus. Sed mi eros, elementum sed dui a, imperdiet fringilla nisi. Curabitur facilisis elementum tortor, malesuada sodales nisi tempor eu. </div>
            <div className="text-center"><h4><Link to="#">Konferencija Ime konferencije</Link></h4></div>
          </div>
          <div className="col-sm-2 main-page-events">
            <img className="homepage-imgs" src="http://crooksandliars.com/files/primary_image/15/05/carlos-santana.jpg"/>
            <div className="homepage-event-about">Phasellus velit erat, volutpat quis metus sit amet, efficitur finibus lectus. Sed mi eros, elementum sed dui a, imperdiet fringilla nisi. Curabitur facilisis elementum tortor, malesuada sodales nisi tempor eu. </div>
            <div className="text-center"><h4><Link to="#">Koncert Ime Prezime</Link></h4></div>
          </div>
          <div className="col-sm-2 main-page-events">
            <img className="homepage-imgs" src="http://crooksandliars.com/files/primary_image/15/05/carlos-santana.jpg"/>
            <div className="homepage-event-about">Phasellus velit erat, volutpat quis metus sit amet, efficitur finibus lectus. Sed mi eros, elementum sed dui a, imperdiet fringilla nisi. Curabitur facilisis elementum tortor, malesuada sodales nisi tempor eu. </div>
            <div className="text-center"><h4><Link to="#">Koncert Ime Prezime</Link></h4></div>
          </div>
        </div>
        <br /><hr />
        <div className="text-center"><h2 className="btn btn-default"><Link to="events">POGLEDAJ SVE DOGAĐAJE</Link></h2></div>
      </div>
    );
  }
}
