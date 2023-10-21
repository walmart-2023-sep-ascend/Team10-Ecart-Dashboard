import React, { useState, useEffect } from 'react';
import axios from 'axios';


const Home = () => {
  useEffect(() => {


    var s = sessionStorage.getItem("tkn");
    if (s) {
      console.log("home" + s);
    }

  }
    , []);
  return (
    <>
      <div className="banner-sec">
        <div className="banner-left  col-lg-4 position-relative " style={{ paddingRight: "0", paddingLeft: "0", paddingTop: "70px" }}>

          <form>
            <div className="row justify-content-center ">
              <div className="col-md-8">
                <div className="">
                  <div className="form-group row row-app">
                    <label className="col-sm-2 col-form-label">Email &nbsp;</label>
                    <div className="col-sm-10">
                      <input type="text" className="form-control" id="staticEmail" placeholder="email@example.com" />
                    </div>
                  </div>
                  <div className="form-group row row-app">
                    <label className="col-sm-2 col-form-label">Password&nbsp;</label>
                    <div className="col-sm-10">
                      <input type="password" className="form-control" id="inputPassword" placeholder="Password" />
                    </div>
                    <div className="form-group row row-app">
                      <label className="col-sm-2 col-form-label">&nbsp;</label>
                      <div className="col-sm-10" id="">
                        <a className="underlineHover" id="underlineHover" href="#">Forgot Password?</a>
                      </div>
                    </div>

                  </div>
                  <div className="form-group row row-app">
                    <div className="col-sm-5 button-end">
                      <input type="submit" className="btn btn-primary" id="staticEmail" value={" SIGNUP "} />
                    </div>
                    <div className="col-sm-5 " >
                      <input type="submit" className="btn btn-primary" id="staticEmail" value={" SIGNIN "} />
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </form>
        </div>
        <div className="banner-left banner-sec-border col-lg-1 position-relative " style={{ paddingRight: "0", paddingLeft: "0", width: "6px" }}>
        </div>
        <div className="banner-right col-lg-7 position-relative" style={{ paddingRight: "0", paddingLeft: "0" }}>
          <div className=" justify-content-center banner-sec-border banner-sec-sub">
            <p className="mb-0 text-center h4 margin-top5 sub-header-background-pro ">Promotions &nbsp;
              <img className='img_profile' src="assets/promotions2.jpg" id="output" width="200" />
            </p>
            <dl className="row banner-sec-sub">
              <dt className="col-sm-3 title1">Promotion Id :1</dt>
              <dd className="col-sm-9">A description list is perfect for defining terms.</dd>

              <dt className="col-sm-3 title1">Promotion Id :2</dt>
              <dd className="col-sm-9">
                <p>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</p>
                <p>Donec id elit non mi porta gravida at eget metus.</p>
              </dd>

              <dt className="col-sm-3 title1">Promotion Id :3</dt>
              <dd className="col-sm-9">Etiam porta sem malesuada magna mollis euismod.</dd>

              <dt className="col-sm-3 text-truncate title1">Promotion Id :4</dt>
              <dd className="col-sm-9">Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</dd>

              <dt className="col-sm-3 title1">Promotion Id :5</dt>
              <dd className="col-sm-9">
                <p>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</p>
                <p>Donec id elit non mi porta gravida at eget metus.</p>
              </dd>
            </dl>
          </div>
          <div className=" justify-content-center banner-sec-border banner-sec-sub">
            <p className="mb-0 text-center h4 margin-top5 sub-header-background-upc">Upcoming Launches &nbsp;
              <img className='img_profile' src="assets/promotions.jpg" id="output" width="200" />

            </p>
            <dl className="row banner-sec-sub">
              <dt className="col-sm-3 title1">Launch Id :1</dt>
              <dd className="col-sm-9">A description list is perfect for defining terms.</dd>

              <dt className="col-sm-3 title1">Launch Id :2</dt>
              <dd className="col-sm-9">
                <p>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</p>
                <p>Donec id elit non mi porta gravida at eget metus.</p>
              </dd>

              <dt className="col-sm-3 title1">Launch Id :3</dt>
              <dd className="col-sm-9">Etiam porta sem malesuada magna mollis euismod.</dd>

              <dt className="col-sm-3 text-truncate title1">Launch Id :4</dt>
              <dd className="col-sm-9">Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</dd>

              <dt className="col-sm-3 title1">Launch Id :5</dt>
              <dd className="col-sm-9">
                <p>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</p>
                <p>Donec id elit non mi porta gravida at eget metus.</p>
              </dd>
            </dl>
          </div>
        </div>
      </div>

    </>

  )
}

export default Home;