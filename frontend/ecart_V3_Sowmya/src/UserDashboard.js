import React from 'react'
import { Button, Container, Divider, Form, Grid, Segment } from 'semantic-ui-react'
import { Card, Row, Col } from 'react-bootstrap';


import DefaultPromotions from './DefaultPromotions';
import UserProducts from './UserProducts'
import UserWishlist from './UserWishlist'
import Image from 'react-bootstrap/Image';
import UserInterests from './UserInterests';
import UserFavCategories from './UserFavCategories';
const UserDashboard = () => (
    <Segment >
        <Grid columns={2} >
            <Grid.Column width={6}>
                <h5>My wishlist</h5>
                <UserWishlist /><UserInterests/>
        <UserFavCategories/>
            </Grid.Column>
            <Grid.Column width={6}>
                <center>
                    <h5>Notifications</h5>
                Here are some product recommendations based on your recent searches
                </center>
                <br/>
                <UserProducts />
            </Grid.Column>
        </Grid>
        
    </Segment>
    
)
export default UserDashboard;

