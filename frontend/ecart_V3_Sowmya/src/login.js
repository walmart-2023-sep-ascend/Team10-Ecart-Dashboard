import React from 'react'
import { Button, Container, Divider, Form, Grid, Segment } from 'semantic-ui-react'
import { Card, Carousel, Row, Col } from 'react-bootstrap';

import myCarousel from './carousel';
import DefaultPromotions from './DefaultPromotions';
import Image from 'react-bootstrap/Image';
const LoginComponent = () => (
    <Segment >
        <Grid columns={2} relaxed='very' stackable>
            <Grid.Column width={4}>
                <Form>
                    <Form.Input icon='user' iconPosition='left' label='Username' placeholder='Username' />
                    <Form.Input icon='lock' iconPosition='left' label='Password' type='password' />
                    <center>
                        <Button content='Login' primary />
                        <Button content='Sign up' />
                        <br />Forgot password?
                    </center>
                </Form>
            </Grid.Column>
            

            <Grid.Column width={12}>
                <h3><center>Offers you simply cannot resist!!!</center></h3><br/>
                <DefaultPromotions/>  
            </Grid.Column>
        </Grid>
<Grid>


</Grid>

    </Segment>



)

export default LoginComponent;

