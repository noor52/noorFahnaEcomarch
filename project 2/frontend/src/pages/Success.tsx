import { useEffect } from 'react';
import { useLocation, useNavigate, Link } from 'react-router-dom';
import { CheckCircle } from 'lucide-react';

const Success = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const orderId = location.state?.orderId;

  useEffect(() => {
    if (!orderId) {
      navigate('/');
    }
  }, [orderId, navigate]);

  return (
    <div className="container mx-auto px-4 py-16">
      <div className="max-w-md mx-auto bg-white rounded-lg shadow-lg p-8 text-center">
        <div className="mb-6 flex justify-center">
          <div className="relative">
            <CheckCircle className="text-green-500 animate-pulse" size={80} />
            <div className="absolute inset-0 flex items-center justify-center">
              <div className="w-20 h-20 border-4 border-green-500 rounded-full animate-ping opacity-20"></div>
            </div>
          </div>
        </div>

        <h1 className="text-3xl font-bold text-gray-800 mb-4">Order Confirmed!</h1>

        <p className="text-gray-600 mb-2">
          Thank you for your purchase.
        </p>

        <p className="text-gray-600 mb-6">
          Your order has been successfully placed.
        </p>

        <div className="bg-gray-50 rounded-lg p-4 mb-6">
          <p className="text-sm text-gray-500 mb-1">Order ID</p>
          <p className="text-2xl font-bold text-primary">#{orderId}</p>
        </div>

        <p className="text-sm text-gray-500 mb-8">
          You will receive a confirmation email shortly with your order details.
        </p>

        <Link
          to="/"
          className="inline-block w-full bg-primary text-white py-3 rounded-lg font-semibold hover:bg-teal-700 transition-colors duration-200"
        >
          Continue Shopping
        </Link>
      </div>
    </div>
  );
};

export default Success;
