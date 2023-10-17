import React, { useState, useEffect } from 'react';
import Promotions from './Promotions';
import UpcomingPromotions from './UpcomingPromotions';
import Favorites from './Favorites';
import { Button, Divider, Form, Grid, Segment } from 'semantic-ui-react';
import './Login.css';

const LoginComponent = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loggedIn, setLoggedIn] = useState(false);
  const [notifications, setNotifications] = useState([]); // State to store notifications
  const [favoritesData, setFavoritesData] = useState(null);

  const handleLogin = () => {
    if (username === 'dummyuser' && password === 'dummypassword') {
      setLoggedIn(true);
      setNotifications([ // Simulate notifications when logged in
        'Welcome! You have successfully logged in.',
        'New promotions are available.',
      ]);
    } else {
      alert('Invalid username or password');
    }
  };

  useEffect(() => {
    // Load user favorites data from favoritesData.json
    fetch('/favoritesData.json')
      .then((response) => response.json())
      .then((data) => setFavoritesData(data))
      .catch((error) => console.error('Error loading favorites data:', error));
  }, []);

  return (
    <div className="login-container">
      <Segment placeholder>
        <Grid columns={2} relaxed='very' stackable>
          <Grid.Column>
            {loggedIn ? (
              <div>
                 <p style={{ color: 'green', fontWeight: 'bold' }}>Login Successful</p>
              </div>
            ) : (
              <Form>
                <Form.Input
                  icon='user'
                  iconPosition='left'
                  label='Username'
                  placeholder='Username'
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
                <Form.Input
                  icon='lock'
                  iconPosition='left'
                  label='Password'
                  type='password'
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
                <Button content='Login' primary onClick={handleLogin} />
              </Form>
            )}
          </Grid.Column>

          <Grid.Column verticalAlign='middle'>
            <Button content='Sign up' icon='signup' size='big' />
          </Grid.Column>
        </Grid>

        <Divider vertical>Or</Divider>
      </Segment>

      {loggedIn && ( // Render notifications only when logged in
        <Segment>
          <h2>Notifications</h2>
          <ul>
            {notifications.map((notification, index) => (
              <li key={index}>{notification}</li>
            ))}
          </ul>
        </Segment>
      )}

      {loggedIn && favoritesData && (
        <Favorites
          products={favoritesData.products}
          categories={favoritesData.categories}
          brands={favoritesData.brands}
        />
      )}

      <div className="upcoming-container">
        <div className="rectangle-box">
          <h2 className="promotions-heading">
            <img
              src="Promotions.jpg"
              alt="Promotions"
              className="logo"
              style={{ maxHeight: '60px' }}
            />
            Promotions
          </h2>
        </div>
        <Promotions />
      </div>

      <div className="upcoming-container">
        <div className="rectangle-box">
          <h2 className="upcoming-promotions-heading">
            <img
              src="UpcomingEvents.png"
              alt="Upcoming Promotions"
              className="logo"
              style={{ maxHeight: '60px' }}
            />
            Upcoming Promotions
          </h2>
        </div>
        <UpcomingPromotions />
      </div>
    </div>
  );
};

export default LoginComponent;
