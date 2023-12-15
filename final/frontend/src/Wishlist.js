/*@author: Sowmyalakshmi KL */
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import ProductsById from './ProductsById';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const Wishlist = ({ userEmail }) => {
  const [wishlistData, setWishlistData] = useState(null);
  const [error, setError] = useState(null);
  const email = userEmail ?? null;


  /* useEffect(() => {
    const fetchWishlistData = async () => {
      try {
       
        if (!(await isUserAuthenticated(email))) {
          setError('Unauthorized access');
          return;
        }
        var token = sessionStorage.getItem("tkn");        
       // const response = await axios.get(`http://localhost:9902/api/userdashboard/getUserWishlist/${userEmail}`);    
       axios.get(`http://localhost:9903/userdashboard/api/user/wishlist/${userEmail}`, {
          headers: {"Access-Control-Allow-Origin": "*",
            "content-type": "application/json",
            "Authorization": token,
          }
        })
          .then(json => {
            console.log("Data is : ", json.data.body[0]);
            //json.data.
            if (json.data && json.data.code && json.data.code == 200) {
              //setFavProducts(json.data.body);
              setWishlistData(json.data);
            } else {
              setError('Unauthorized access');
            }
          })
          .catch((e) => {
            console.log(e);
          });

       
      } catch (error) {
        setError(error.response ? error.response.data : 'An error occurred');
      }
    };

    fetchWishlistData(); */
    useEffect(() => {
      const fetchWishlistData = async () => {
        try {
           if (!isUserAuthenticated()) {
            setError('Unauthorized access');
            return;
          }
  
          const response = await axios.get(`http://localhost:9903/userdashboard/api/user/wishlist/${props.userEmail}`);
          setWishlistData(response.data);
        } catch (error) {
          setError(error.response ? error.response.data : 'An error occurred');
        }
      };
  
      fetchWishlistData();
  }, [userEmail]);

  if (error) {
    return <div>Error: {error}</div>;
  }

  if (!wishlistData) {
    return <div>Loading...</div>;
  }

  // Group the wishlistData into rows of three products each
  const rows = [];
  for (let i = 0; i < wishlistData.length; i += 3) {
    const rowProducts = wishlistData.slice(i, i + 3);
    rows.push(rowProducts);
  }

  return (
    <div>
      {rows.map((row, rowIndex) => (
        <Row key={rowIndex} className="mb-4">
          {row.map((productId, colIndex) => (
            <Col key={colIndex}>
              <ProductsById productId={productId} />
            </Col>
          ))}
        </Row>
      ))}
    </div>
  );
};

/* const isUserAuthenticated = async (email) => {
  console.log("Inside isauthenticated method");
  var s = sessionStorage.getItem("tkn");
  console.log("s", s);
  console.log("email", email);
  if (s != null) {
    
    return true;
  } else {
    //const idParam = new URLSearchParams(location.search).get('id');
    
    if (email) {
      try {
        const response = await axios.post('http://localhost:9900/auth/token', {
          "email": email
        });
        console.log("json", response.data);
        sessionStorage.setItem("tkn", response.data.accessToken);
        return true;
      } catch (error) {
        console.error('Error:', error);
        return false;
      }
    }
  }
}; */

const isUserAuthenticated = () => {  
  return true; // For simulation purposes, always return true
};
export default Wishlist;
