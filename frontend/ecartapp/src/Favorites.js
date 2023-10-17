// Favorites.js
import React from 'react';

const Favorites = ({ products, categories, brands }) => {
  return (
    <div>
      <h3>Favorite Products:</h3>
      <ul className="favorite-list">
        {products.map((product, index) => (
          <li key={index}>
            <div className="favorite-item">
              <img src={product.image} alt={product.name} />
              <div>
                <p>{product.name}</p>
                <p>{product.description}</p>
              </div>
            </div>
          </li>
        ))}
      </ul>

      <h3>Favorite Categories:</h3>
      <ul className="favorite-list">
        {categories.map((category, index) => (
          <li key={index}>
            <div className="favorite-item">
              <img src={category.image} alt={category.name} />
              <div>
                <p>{category.name}</p>
                <p>{category.description}</p>
              </div>
            </div>
          </li>
        ))}
      </ul>

      <h3>Favorite Brands:</h3>
      <ul className="favorite-list">
        {brands.map((brand, index) => (
          <li key={index}>
            <div className="favorite-item">
              <img src={brand.image} alt={brand.name} />
              <div>
                <p>{brand.name}</p>
                <p>{brand.description}</p>
              </div>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Favorites;
