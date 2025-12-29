import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { CartProvider } from './context/CartContext';
import Home from './pages/Home';
import Checkout from './pages/Checkout';
import Success from './pages/Success';

function App() {
  return (
    <Router>
      <CartProvider>
        <div className="min-h-screen bg-gray-50">
          <header className="bg-primary text-white shadow-lg">
            <div className="container mx-auto px-4 py-6">
              <h1 className="text-3xl font-bold">CULTARAL-pro</h1>
              <p className="text-sm mt-1">Premium Cultural Garments</p>
            </div>
          </header>
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/checkout" element={<Checkout />} />
            <Route path="/success" element={<Success />} />
          </Routes>
        </div>
      </CartProvider>
    </Router>
  );
}

export default App;
