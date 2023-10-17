import React from 'react';
import { Grid, Image } from 'semantic-ui-react'
import { Message } from 'semantic-ui-react'
import { Button } from 'semantic-ui-react'

export const PhoneComponent = () => {
    return (
        <>
            <Grid>
                <Grid.Column width={4}>
                    <Image src='https://images.macrumors.com/t/oiWkxB5isnYn8BFbcgKsnDIUOdI=/800x0/smart/article-new/2023/09/iPhone-15-Pro-Lineup-Feature.jpg?lossy' />
                </Grid.Column>
                <Grid.Column width={9}>
                    <Message>
                        <Message.Header>iPhone 15</Message.Header>
                        <p>
                            iPhone 15 and iPhone 15 Plus will be available in five stunning new colors: pink, yellow, green, blue, and black. Pre-orders begin Friday, September 15, with availability beginning Friday, September 22.
                            “iPhone 15 and iPhone 15 Plus represent a huge leap forward with exciting camera innovations that inspire creativity, the intuitive Dynamic Island, and features like Roadside Assistance via satellite that make a real difference in users’ lives,” said Kaiann Drance, Apple’s vice president of Worldwide iPhone Product Marketing. “We’re also pushing the power of computational photography to new levels this year with a 48MP Main camera featuring a new 24MP default for super-high-resolution photos, a new 2x Telephoto option, and next-generation portraits.”
                        </p>
                    </Message>
                </Grid.Column>
                <Grid.Column width={3} verticalAlign='middle'>
                    <Button.Group>
                        <Button>Buy Now!</Button>
                        <Button.Or />
                        <Button positive>Add To Cart</Button>
                    </Button.Group>
                </Grid.Column>
            </Grid>

            <Grid>
                <Grid.Column width={4}>
                    <Image src='https://images.macrumors.com/t/oiWkxB5isnYn8BFbcgKsnDIUOdI=/800x0/smart/article-new/2023/09/iPhone-15-Pro-Lineup-Feature.jpg?lossy' />
                </Grid.Column>
                <Grid.Column width={9}>
                    <Message>
                        <Message.Header>iPhone 14</Message.Header>
                        <p>
                            iPhone 15 and iPhone 15 Plus will be available in five stunning new colors: pink, yellow, green, blue, and black. Pre-orders begin Friday, September 15, with availability beginning Friday, September 22.
                            “iPhone 15 and iPhone 15 Plus represent a huge leap forward with exciting camera innovations that inspire creativity, the intuitive Dynamic Island, and features like Roadside Assistance via satellite that make a real difference in users’ lives,” said Kaiann Drance, Apple’s vice president of Worldwide iPhone Product Marketing. “We’re also pushing the power of computational photography to new levels this year with a 48MP Main camera featuring a new 24MP default for super-high-resolution photos, a new 2x Telephoto option, and next-generation portraits.”
                        </p>
                    </Message>
                </Grid.Column>
                <Grid.Column width={3} verticalAlign='middle'>
                    <Button.Group>
                        <Button>Buy Now!</Button>
                        <Button.Or />
                        <Button positive>Add To Cart</Button>
                    </Button.Group>
                </Grid.Column>
            </Grid>
        </>
    )
}