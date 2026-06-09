import React, { useEffect, useState } from 'react';
import './App.css';

interface Product {
  id: number;
  name: string;
  description: string;
  price: number;
  stock: number;
}

function App() {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    fetch('/api/products')
      .then(res => res.json())
      .then(data => {
        setProducts(data);
        setLoading(false);
      })
      .catch(() => {
        setError('Failed to load products');
        setLoading(false);
      });
  }, []);

  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;

  return (
    <div className="App">
      <header className="App-header">
        <h1>ShopEase</h1>
      </header>
      <main style={{ padding: '20px' }}>
        <div style={{
          display: 'grid',
          gridTemplateColumns: 'repeat(3, 1fr)',
          gap: '20px'
        }}>
          {products.map(product => (
            <div key={product.id} style={{
              border: '1px solid #ddd',
              borderRadius: '8px',
              padding: '16px'
            }}>
              <h3>{product.name}</h3>
              <p>{product.description}</p>
              <p><strong>${product.price}</strong></p>
              <p>Stock: {product.stock}</p>
              <button>Add to Cart</button>
            </div>
          ))}
        </div>
      </main>
    </div>
  );
}

export default App;
