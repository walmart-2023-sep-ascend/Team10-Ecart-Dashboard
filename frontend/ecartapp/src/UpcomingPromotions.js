import React, { useState, useEffect } from 'react';
import './UpcomingPromotions.css'; // Import the CSS file

const upcomingPromotionsData = [
  {
    id: 5,
    title: 'Upcoming Promotion 1',
    description: 'New promotion coming soon!',
    imageUrl: 'Fashwash.jpg', // Adjust the image URL
  },
  {
    id: 6,
    title: 'Upcoming Promotion 2',
    description: 'Stay tuned for this exciting deal!',
    imageUrl: 'watch2.jpg', // Adjust the image URL
  },
  {
    id: 7,
    title: 'Upcoming Promotion 3',
    description: 'Stay tuned for this exciting deal!',
    imageUrl: 'Lab Eqipment.jpg', // Adjust the image URL
  },
  {
    id: 8,
    title: 'Upcoming Promotion 4',
    description: 'Stay tuned for this exciting deal!',
    imageUrl: 'Hand Bag.jpg', // Adjust the image URL
  },
];

const UpcomingPromotions = () => {
  const [blink, setBlink] = useState(true);

  useEffect(() => {
    const timer = setTimeout(() => {
      setBlink(false);
    }, 100000); // Set blinking to stop after 10 seconds

    return () => {
      clearTimeout(timer);
    };
  }, []);

  return (
    <div className={`upcoming-promotions-container ${blink ? 'blink' : ''}`}>
      {upcomingPromotionsData.map((promotion) => (
        <div key={promotion.id} className="upcoming-promotion-card">
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

export default UpcomingPromotions;
