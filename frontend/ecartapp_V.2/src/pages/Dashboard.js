import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate, useLocation } from 'react-router-dom';

const Dashboard = () => {

  const [favProducts, setFavProducts] = useState([]);
  const [brands, setBrands] = useState([]);
  const [cats, setCats] = useState([]);
  const [authers, setAuthers] = useState({ email: "", id: 0, jwttoken: "", token: "" });
  const navigate = useNavigate();
  const location = useLocation();

  const catsentries = Object.entries(cats);
  const brandsentries = Object.entries(brands);
  //sessionStorage.setItem("tkn", "ecarteyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmFudmlsbGUwQG5wcy5nb3YiLCJleHAiOjE2OTc3MDY2NTgsImlhdCI6MTY5NzY5OTQ1OH0.izEIs7sl2YVrcEB9UppBGOCSNUPJx4d8mAn_7gFud-EN4bEScJWm3DgoqKL1Ay8VQiXReFfnot2CAX1VM7fa6A");
  useEffect(() => {


    var s = sessionStorage.getItem("tkn")
    const idParam = new URLSearchParams(location.search).get('id');
    if (!s && !idParam) {
      console.log('if');
      navigate('/login');
    } else {
      console.log('else');
      axios.post('http://localhost:9091/ECartPGPTen/api/ecarto/authenticate',
        {
          "email": idParam
        })
        .then(json => {
          console.log("infoof is : ", json.data.body);
          setAuthers(json.data.body);
          console.log('auth.idaaaAA @@' + authers);
          console.log('auth.idaaaid @@' + json.data.body.id);
          sessionStorage.setItem("tkn", json.data.body.token);
          if (json.data && json.data.code && json.data.code == 200) {
            fetchKewords(json.data.body.id);
          } else {
            navigate('/login?err=' + json.data.code);
          }
        })
        .catch((e) => {
          console.log(e);
        });
      console.log('auth.id ' + authers.token);
      console.log('auth : ' + authers);




    }
  }, []);

  const fetchKewords = (id) => {
    const ids = authers.id;
    console.log("test" + ids);
    const token = sessionStorage.getItem("tkn");
    axios.get('http://localhost:9091/ECartPGPTen/api/ecartp/favBrandsCatgProducts/' + id, {
      headers: {
        "content-type": "application/json",
        "Authorization": token,
      }
    })
      .then(json => {
        console.log("Data is : ", json.data.body[0]);
        //json.data.
        if (json.data && json.data.code && json.data.code == 200) {
          setFavProducts(json.data.body);
        } else {
          navigate('/login?err=' + json.data.code);
        }
      })
      .catch((e) => {
        console.log(e);
      });

    /**Get brands and categories */
    axios.get('http://localhost:9091/ECartPGPTen/api/ecartp/favBrandsCatg/' + id, {
      headers: {
        "content-type": "application/json",
        "Authorization": token,
      }
    })
      .then(json => {
        if (json.data && json.data.code && json.data.code == 200) {
          console.log("cat is : ", json.data.body.categories);
          setBrands(json.data.body.brands);
          setCats(json.data.body.categories);
        } else {
          navigate('/login?err=' + json.data.code);
        }
      })
      .catch((e) => {
        console.log(e);
      });
  }
  const catsentriesUI = catsentries.map(([key, value]) => (
    <dl className="row banner-sec-sub overflow-auto block-scroll">
      <dt className="col-sm-1 title1 ">
        <img className='img_profile img_profile-data' src={value[0].iconUrl} id="output" width="100" />
      </dt>
      <dd className="col-sm-11">{key}</dd>
    </dl>
  ));

  const brandsentriesUI = brandsentries.map(([key, value]) => (
    <dl className="row banner-sec-sub overflow-auto block-scroll">
      <dt className="col-sm-1 title1 ">
        <img className='img_profile img_profile-data' src={value[0].iconUrl} id="output" width="100" />
      </dt>
      <dd className="col-sm-11">{key}</dd>
    </dl>
  ));

  return (
    <>
      <div className="banner-sec">
        <div className="banner-right col-lg-5 banner-sec-border position-relative" style={{ paddingRight: "0", paddingLeft: "0" }}>
          <p className="mb-0 text-center h4 margin-top5 sub-header-background-upc2 ">Favoutires &nbsp;
            <img className='img_profile' src="assets/fav.jfif" id="output" width="200" />
          </p>
          <div className=" justify-content-center banner-sec-border banner-sec-sub">
            <p className="mb-0 text-center h4 margin-top5 sub-header-background-pro ">
              <span>Categories &nbsp;</span>
              {/* <img className='img_profile' src="assets/promotions2.jpg" id="output" width="200" /> */}
            </p>
            {catsentriesUI}
          </div>
          <div className=" justify-content-center banner-sec-border banner-sec-sub">
            <p className="mb-0 text-center h4 margin-top5 sub-header-background-upc">Brands &nbsp;
              {/*<img className='img_profile' src="assets/promotions.jpg" id="output" width="200" />*/}

            </p>
            {brandsentriesUI}
          </div>
          <div className=" justify-content-center banner-sec-border banner-sec-sub">
            <p className="mb-0 text-center h4 margin-top5 sub-header-background-upc">Products &nbsp;
              {/* <img className='img_profile' src="assets/promotions.jpg" id="output" width="200" />*/}
            </p>

            {favProducts.map(comment => (
              <dl className="row banner-sec-sub overflow-auto block-scroll">
                <dt className="col-sm-1 title1 ">
                  <img className='img_profile img_profile-data' src={comment.imageUrls[0]} id="output" width="100" />
                </dt>
                <dd className="col-sm-11">{comment.shortDescription}</dd>
              </dl>
            ))}


          </div>
        </div>
        <div className="banner-left banner-sec-border col-lg-1 position-relative " style={{ paddingRight: "0", paddingLeft: "0", width: "6px" }}>
        </div>
        <div className="banner-right col-lg-6 position-relative" style={{ paddingRight: "0", paddingLeft: "0" }}>
          <div className=" justify-content-center banner-sec-border banner-sec-sub">
            <p className="mb-0 text-center h4 margin-top5 sub-header-background-pro ">Notifications &nbsp;
              <img className='img_profile' src="assets/notification.png" id="output" width="200" />
            </p>
            <dl className="row banner-sec-sub">
              <dt className="col-sm-3 title1">Notification :1</dt>
              <dd className="col-sm-9">A description list is perfect for defining terms.</dd>

              <dt className="col-sm-3 title1">Notification :2</dt>
              <dd className="col-sm-9">
                <p>Vestibulum id ligula porta felis euismod semper eget lacinia odio sem nec elit.</p>
                <p>Donec id elit non mi porta gravida at eget metus.</p>
              </dd>

              <dt className="col-sm-3 title1">Notification :3</dt>
              <dd className="col-sm-9">Etiam porta sem malesuada magna mollis euismod.</dd>


            </dl>
          </div>
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



            </dl>
          </div>
        </div>
      </div>

    </>

  )
}

export default Dashboard;