/*@author: Sowmyalakshmi KL */
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { ThreeDots } from 'react-loader-spinner';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSpinner } from '@fortawesome/free-solid-svg-icons';
import Helmet from 'react-helmet';
import Wishlist from './Wishlist';
import { Button, Container, Divider, Form, Grid, Segment, Card } from 'semantic-ui-react';
import ProductsByText from './ProductsByText';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';

const Dashboard = () => {
  const [userData, setUserData] = useState(null);
  const [error, setError] = useState(null);
  const { emailId } = useParams();

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const response = await axios.get(`http://localhost:9903/userdashboard/api/user/${encodeURIComponent(emailId)}`);
        setUserData(response.data);
      } catch (error) {
        setError(error.response ? error.response.data : 'An error occurred');
      }
    };

    fetchUserData();
  }, [emailId]);

  if (error) {
    return <div>Error: {error}</div>;
  }

  if (!userData) {
    return (
      <div style={{ textAlign: 'center', marginTop: '20px' }}>
        <FontAwesomeIcon icon={faSpinner} spin size="4x" />
        <p>Loading...</p>
      </div>
    );
  }

  const rows = [];
  for (let i = 0; i < userData.recentSearches.length; i += 3) {
    const rowProducts = userData.recentSearches.slice(i, i + 3);
    rows.push(rowProducts);
  }

  return (
    <div>
      <Helmet>
        <title>WalECart</title>
      </Helmet>
      <Segment>
        <center>
          <h3>My Dashboard</h3>
        </center>

        <Grid columns={12}>
          {/* Left Column (75%) */}
          <Grid.Column width={12}>
           

            {/* Notifications Section */}
            <Segment>
              <h4>My Notifications</h4>
              <br/>Here are some product recommendations based on your recent searches<br/><br/>
              <div>
                {rows.map((row, rowIndex) => (
                  <Row key={rowIndex} className="mb-4" style={{ margin: '0 -5px' }}>
                    {row.map((searchTerm, colIndex) => (
                      <Col key={colIndex} style={{ padding: '0 5px' }}>
                        <ProductsByText text={searchTerm} />
                      </Col>
                    ))}
                  </Row>
                ))}
              </div>
            </Segment>
             {/* Wishlist Section */}
             <Segment>
              <h4>My Wishlist</h4>
              <Wishlist userEmail={emailId} />
            </Segment>
          </Grid.Column>

          {/* Right Column (25%) */}
          <Grid.Column width={3}>
            {/* Interests Section */}
            <Segment>
              <h4>My Interests</h4>
              <Card>
                <Card.Content>
                  <Card.Description>
                    <ul>
                      {Array.isArray(userData.userInterests) &&
                        userData.userInterests.map((interests, index) => (
                          <li key={index}>
                            <a href={`http://localhost:9100/products/searchProductsByTitleNameOrShortDesc/${interests}`}>
                              {interests}
                            </a>
                          </li>
                        ))}
                    </ul>
                  </Card.Description>
                </Card.Content>
              </Card>
            </Segment>

            {/* Favorite Categories Section */}
            <Segment>
              <h4>My Favorite Categories</h4>
              <Card>
                <Card.Content>
                  <Card.Description>
                    <ul>
                      {Array.isArray(userData.favoriteCategories) &&
                        userData.favoriteCategories.map((category, index) => (
                          <li key={index}>
                            <a href={`http://localhost:9100/products/searchProductsByTitleNameOrShortDesc/${category}`}>
                              {category}
                            </a>
                          </li>
                        ))}
                    </ul>
                  </Card.Description>
                </Card.Content>
              </Card>
            </Segment>
          </Grid.Column>
        </Grid>
      </Segment>
    </div>
  );
};

export default Dashboard;