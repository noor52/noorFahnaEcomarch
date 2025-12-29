# CULTARAL-pro

A full-stack e-commerce platform for cultural garments built with Spring Boot and React.

## Features

- Product catalog with category filtering (Shirts/Pants)
- Shopping cart with quantity management
- 3-step checkout process (Cart → Shipping → Payment)
- Order processing and confirmation
- Design Patterns:
  - **Factory Method Pattern**: `GarmentFactory` for creating product instances
  - **Builder Pattern**: `OrderBuilder` for constructing orders
- H2 in-memory database with automatic data seeding
- RESTful API with CORS support
- Responsive UI with Tailwind CSS
- Docker containerization
- CI/CD with GitHub Actions

## Tech Stack

### Backend
- Java 11+
- Spring Boot 2.7.14
- Spring Data JPA
- H2 Database
- Maven

### Frontend
- React 18
- TypeScript
- Vite
- Tailwind CSS
- React Router
- lucide-react icons

### DevOps
- Docker & Docker Compose
- GitHub Actions
- Nginx

## Project Structure

```
CULTARAL-pro/
├── backend/
│   ├── src/main/java/com/noor170/culturalpro/
│   │   ├── EcomApplication.java
│   │   ├── model/
│   │   │   ├── Product.java
│   │   │   ├── OrderItem.java
│   │   │   └── OrderEntity.java
│   │   ├── repository/
│   │   │   ├── ProductRepository.java
│   │   │   └── OrderRepository.java
│   │   ├── factory/
│   │   │   └── GarmentFactory.java
│   │   ├── builder/
│   │   │   └── OrderBuilder.java
│   │   ├── service/
│   │   │   ├── ProductService.java
│   │   │   └── OrderService.java
│   │   ├── controller/
│   │   │   ├── ProductController.java
│   │   │   └── OrderController.java
│   │   └── config/
│   │       └── DataLoader.java
│   ├── src/main/resources/
│   │   └── application.properties
│   └── pom.xml
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   │   ├── ProductCard.tsx
│   │   │   └── CartSummary.tsx
│   │   ├── context/
│   │   │   └── CartContext.tsx
│   │   ├── pages/
│   │   │   ├── Home.tsx
│   │   │   ├── Checkout.tsx
│   │   │   └── Success.tsx
│   │   ├── App.tsx
│   │   ├── main.tsx
│   │   └── index.css
│   ├── package.json
│   ├── vite.config.ts
│   └── tailwind.config.js
├── .github/workflows/
│   └── ci-cd.yml
├── Dockerfile.backend
├── Dockerfile.frontend
├── docker-compose.yml
└── README.md
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Node.js 18 or higher
- Maven 3.6+
- Docker & Docker Compose (optional)

### Running Locally

#### Backend

```bash
cd backend
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

Access H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:culturalprodb`
- Username: `sa`
- Password: (leave empty)

#### Frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend will start on `http://localhost:5173`

### Running with Docker Compose

Build and run the entire stack:

```bash
docker-compose up --build
```

Access the application:
- Frontend: `http://localhost:5173`
- Backend API: `http://localhost:8080`

To stop:

```bash
docker-compose down
```

## API Endpoints

### Products

**Get all products**
```bash
curl http://localhost:8080/api/products
```

**Filter products by type**
```bash
curl http://localhost:8080/api/products?type=SHIRT
curl http://localhost:8080/api/products?type=PANT
curl http://localhost:8080/api/products?type=ALL
```

**Get product by ID**
```bash
curl http://localhost:8080/api/products/1
```

### Orders

**Create an order**
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "John Doe",
    "address": "123 Elm Street, Dhaka",
    "paymentMethod": "CARD",
    "items": [
      {
        "productId": 1,
        "name": "Classic White Shirt",
        "size": "M",
        "quantity": 2,
        "price": 29.99
      }
    ]
  }'
```

## Running Tests

### Backend Tests

```bash
cd backend
mvn test
```

### Build Backend

```bash
cd backend
mvn clean package
```

### Build Frontend

```bash
cd frontend
npm run build
```

## CI/CD Configuration

The project uses GitHub Actions for continuous integration and deployment.

### Setting Up CI/CD Secrets

To enable Docker image pushing, add these secrets to your GitHub repository:

1. Go to your repository on GitHub
2. Navigate to Settings → Secrets and variables → Actions
3. Add the following secrets:
   - `DOCKERHUB_USERNAME`: Your Docker Hub username
   - `DOCKERHUB_TOKEN`: Your Docker Hub access token

### Workflow Triggers

The CI/CD pipeline runs on:
- Push to `main` branch
- Pull requests to `main` branch

### Pipeline Steps

1. Checkout code
2. Set up JDK 11
3. Build and test backend
4. Set up Node.js 18
5. Install and build frontend
6. Build Docker images
7. (Optional) Push images to Docker Hub

## Design Patterns

### Factory Method Pattern

The `GarmentFactory` implements the Factory Method pattern to create product instances:

```java
Product shirt = GarmentFactory.create("SHIRT", "Classic White Shirt", 29.99, "M", "image-url");
Product pant = GarmentFactory.create("PANT", "Dark Blue Jeans", 49.99, "32", "image-url");
```

### Builder Pattern

The `OrderBuilder` implements the Builder pattern for constructing complex order objects:

```java
OrderEntity order = OrderBuilder.create()
    .addCustomer("John Doe")
    .addAddress("123 Elm Street")
    .addPaymentMethod("CARD")
    .addItems(orderItems)
    .build();
```

## Database Seeding

The application automatically seeds the database with 6 products (3 shirts and 3 pants) on startup using the `DataLoader` component and `GarmentFactory`.

## Troubleshooting

### Backend Issues

- **Port 8080 already in use**: Change the port in `application.properties`:
  ```properties
  server.port=8081
  ```

- **Database issues**: The H2 database is in-memory and will be recreated on each restart

### Frontend Issues

- **API connection issues**: Check that the backend is running and the proxy is configured correctly in `vite.config.ts`

- **Build fails**: Clear node_modules and reinstall:
  ```bash
  rm -rf node_modules package-lock.json
  npm install
  ```

### Docker Issues

- **Port conflicts**: Change the port mappings in `docker-compose.yml`

- **Build fails**: Clear Docker cache and rebuild:
  ```bash
  docker-compose down -v
  docker system prune -a
  docker-compose up --build
  ```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License.

## Contact

For questions or support, please open an issue on GitHub.
