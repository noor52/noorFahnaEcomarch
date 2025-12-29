import { Trash2, Plus, Minus } from 'lucide-react';
import { useCart } from '../context/CartContext';

interface CartSummaryProps {
  showCheckoutButton?: boolean;
  onCheckout?: () => void;
}

const CartSummary: React.FC<CartSummaryProps> = ({
  showCheckoutButton = true,
  onCheckout
}) => {
  const { items, removeFromCart, updateQuantity, getTotal } = useCart();

  if (items.length === 0) {
    return (
      <div className="bg-white rounded-lg shadow-md p-6">
        <h2 className="text-2xl font-bold mb-4">Shopping Cart</h2>
        <p className="text-gray-500 text-center py-8">Your cart is empty</p>
      </div>
    );
  }

  return (
    <div className="bg-white rounded-lg shadow-md p-6">
      <h2 className="text-2xl font-bold mb-4">Shopping Cart</h2>
      <div className="space-y-4">
        {items.map((item) => (
          <div key={item.productId} className="flex items-center gap-4 border-b pb-4">
            <img
              src={item.image}
              alt={item.name}
              className="w-20 h-20 object-cover rounded"
            />
            <div className="flex-1">
              <h3 className="font-semibold">{item.name}</h3>
              <p className="text-sm text-gray-500">Size: {item.size}</p>
              <p className="text-primary font-semibold">${item.price.toFixed(2)}</p>
            </div>
            <div className="flex items-center gap-2">
              <button
                onClick={() => updateQuantity(item.productId, item.quantity - 1)}
                className="p-1 rounded bg-gray-200 hover:bg-gray-300"
              >
                <Minus size={16} />
              </button>
              <span className="w-8 text-center font-semibold">{item.quantity}</span>
              <button
                onClick={() => updateQuantity(item.productId, item.quantity + 1)}
                className="p-1 rounded bg-gray-200 hover:bg-gray-300"
              >
                <Plus size={16} />
              </button>
            </div>
            <button
              onClick={() => removeFromCart(item.productId)}
              className="text-red-500 hover:text-red-700"
            >
              <Trash2 size={20} />
            </button>
          </div>
        ))}
      </div>
      <div className="mt-6 pt-4 border-t">
        <div className="flex justify-between items-center mb-4">
          <span className="text-xl font-bold">Total:</span>
          <span className="text-2xl font-bold text-primary">${getTotal().toFixed(2)}</span>
        </div>
        {showCheckoutButton && (
          <button
            onClick={onCheckout}
            className="w-full bg-primary text-white py-3 rounded-lg font-semibold hover:bg-teal-700 transition-colors duration-200"
          >
            Proceed to Checkout
          </button>
        )}
      </div>
    </div>
  );
};

export default CartSummary;
