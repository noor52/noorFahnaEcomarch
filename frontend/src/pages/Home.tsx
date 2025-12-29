import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ProductCard from '../components/ProductCard';
import CartSummary from '../components/CartSummary';
import { useCart } from '../context/CartContext';

interface Product {
  id: number;
  name: string;
  type: string;
  price: number;
  size: string;
  image: string;
}

const Home = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [filter, setFilter] = useState<string>('ALL');
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();
  const { items } = useCart();

  useEffect(() => {
    fetchProducts(filter);
  }, [filter]);

  const fetchProducts = async (type: string) => {
    setLoading(true);
    try {
      const url = type === 'ALL'
        ? '/api/products'
        : `/api/products?type=${type}`;
      const response = await fetch(url);
      const data = await response.json();
      setProducts(data);
    } catch (error) {
      console.error('Error fetching products:', error);
    } finally {
      setLoading(false);
    }
  };

  const handleCheckout = () => {
    navigate('/checkout');
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <div className="grid grid-cols-1 lg:grid-cols-4 gap-8">
        <aside className="lg:col-span-1">
          <div className="bg-white rounded-lg shadow-md p-6 mb-6">
            <h2 className="text-xl font-bold mb-4">Categories</h2>
            <div className="space-y-2">
              <button
                onClick={() => setFilter('ALL')}
                className={`w-full text-left px-4 py-2 rounded-lg transition-colors ${
                  filter === 'ALL'
                    ? 'bg-primary text-white'
                    : 'bg-gray-100 hover:bg-gray-200'
                }`}
              >
                All Products
              </button>
              <button
                onClick={() => setFilter('SHIRT')}
                className={`w-full text-left px-4 py-2 rounded-lg transition-colors ${
                  filter === 'SHIRT'
                    ? 'bg-primary text-white'
                    : 'bg-gray-100 hover:bg-gray-200'
                }`}
              >
                Shirts
              </button>
              <button
                onClick={() => setFilter('PANT')}
                className={`w-full text-left px-4 py-2 rounded-lg transition-colors ${
                  filter === 'PANT'
                    ? 'bg-primary text-white'
                    : 'bg-gray-100 hover:bg-gray-200'
                }`}
              >
                Pants
              </button>
            </div>
          </div>

          {items.length > 0 && (
            <div className="hidden lg:block">
              <CartSummary onCheckout={handleCheckout} />
            </div>
          )}
        </aside>

        <main className="lg:col-span-3">
          <h2 className="text-2xl font-bold mb-6">
            {filter === 'ALL' ? 'All Products' : filter === 'SHIRT' ? 'Shirts' : 'Pants'}
          </h2>

          {loading ? (
            <div className="text-center py-12">
              <div className="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-primary"></div>
            </div>
          ) : (
            <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 gap-6">
              {products.map((product) => (
                <ProductCard key={product.id} product={product} />
              ))}
            </div>
          )}

          {items.length > 0 && (
            <div className="lg:hidden mt-8">
              <CartSummary onCheckout={handleCheckout} />
            </div>
          )}
        </main>
      </div>
    </div>
  );
};

export default Home;
