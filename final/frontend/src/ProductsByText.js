/*@author: Sowmyalakshmi KL */
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Card from 'react-bootstrap/Card';
import Grid from '@mui/material/Grid';
import Button from 'react-bootstrap/Button';
function ProductsByText({ text }) {
  const [productData, setProductData] = useState([]);

  useEffect(() => {
    console.log('Fetching data for text:', text);
    axios.get(`/api/products/getByText/${text}`)
      .then((response) => {
        console.log('API response:', response.data);
        setProductData(response.data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  }, [text]);

  if (!Array.isArray(productData) || productData.length === 0) {
    console.log('No products found for the given search text:', text);
    return <div>No products found for the given search text...</div>;
  }
  console.log('Products:', productData);
  const firstProduct = productData[0];
  return (
    <div>
      <Grid container rowSpacing={2} columnSpacing={{ xs: 1, sm: 2, md: 3 }}>
        <Grid item xs={12} sm={6} md={4}>
          <Card style={{ width: '23rem', height: '100%' }}>
            <Card.Img 
              variant="top" 
              src={firstProduct.imageUrls[0]} 
              style={{ objectFit: 'cover', height: '200px', width: '100%' }}
            />
            <Card.Body>
              <Card.Title>{firstProduct.productName}</Card.Title>
              <Card.Text>{firstProduct.shortDescription}</Card.Text>
              <Card.Footer><center><Button
                variant="dark"
                href={`http://localhost:9100/products/searchProductsByTitleNameOrShortDesc/${productData.id}`}
                target="_blank"
              >
                Product details
              </Button></center></Card.Footer>
            </Card.Body>
          </Card>
        </Grid>
      </Grid>
    </div>
  );
}

export default ProductsByText;
