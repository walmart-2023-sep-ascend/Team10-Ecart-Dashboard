import React from 'react'
import { Button, Container, Divider, Form, Grid, Segment } from 'semantic-ui-react'
import { Card, Carousel, Row, Col } from 'react-bootstrap';

import myCarousel from './carousel';
import SowComponent from './DefaultPromotions';
import Image from 'react-bootstrap/Image';
import UserSearchesComponent from './UserSearchesComponent';
const DashboardComponent = () => (
    <Segment >
        <Grid columns={2} relaxed='very' stackable>
            <Grid.Column width={4}>
                <UserSearchesComponent/>
            </Grid.Column>
            

            <Grid.Column width={12}>
                <h2><center>Offers you simply cannot resist!!!</center></h2>
                <SowComponent/>  
            </Grid.Column>
        </Grid>
<Grid>


</Grid>

    </Segment>



)

export default DashboardComponent;

