import React from 'react'
import Carousel from 'react-bootstrap/Carousel';

export const CarouselComponent = () => {
    return (
        
    <div>
        <Carousel data-bs-theme="dark">
            <Carousel.Item align="center">
              <img className="d-block w-90" src="promo1.jpg" alt="First slide" />
              {/* <Carousel.Caption>
                <h5>First slide label</h5>
                <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
              </Carousel.Caption> */}
            </Carousel.Item>
            <Carousel.Item align="center">
              <img className="d-block w-90" src="assets/logo2.png" alt="Second slide" />
              {/* <Carousel.Caption>
                <h5>Second slide label</h5>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
              </Carousel.Caption> */}
            </Carousel.Item>
            <Carousel.Item align="center">
              <img className="d-block w-90" src="assets/logo3.png" alt="Third slide" />
              {/* <Carousel.Caption>
                <h5>Third slide label</h5>
                <p>
                  Praesent commodo cursus magna, vel scelerisque nisl consectetur.
                </p>
              </Carousel.Caption> */}
            </Carousel.Item>
          </Carousel>
    </div>
  )
}
