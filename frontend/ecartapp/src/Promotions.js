import React from 'react';
import './Promotions.css';

const promotionsData = [
  {
    id: 1,
    title: 'Promotion 1',
    description: 'Get 20% off on all items',
    imageUrl: 'promotion1.jpg',
  },
  {
    id: 2,
    title: 'Promotion 2',
    description: 'Buy one, get one free!',
    imageUrl: 'promotion2.jpg',
  },
  {
    id: 3,
    title: 'Promotion 3',
    description: 'Special discount on selected items',
    imageUrl: 'promotion3.jpg',
  },
  {
    id: 4,
    title: 'Promotion 4',
    description: 'Special Coupon on selected items',
    imageUrl: 'promotion4.jpg',
  },
];

const Promotions = () => {
  return (
    <div className="promotions-container">
      {promotionsData.map((promotion, index) => (
        <div key={promotion.id} className="promotion-card">
          <img
            src={process.env.PUBLIC_URL + promotion.imageUrl}
            alt={promotion.title}
            style={{ maxWidth: '100%', height: '200px' }}
          />
          <h3>{promotion.title}</h3>
          <p>{promotion.description}</p>
        </div>
      ))}
    </div>
  );
};

export default Promotions;
