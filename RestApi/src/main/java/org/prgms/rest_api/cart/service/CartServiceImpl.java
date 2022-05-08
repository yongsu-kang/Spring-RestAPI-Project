package org.prgms.rest_api.cart.service;

import org.prgms.rest_api.cart.model.Cart;
import org.prgms.rest_api.cart.model.CartProductDto;
import org.prgms.rest_api.cart.repository.CartRepository;
import org.prgms.rest_api.product.dto.ProductDto;
import org.prgms.rest_api.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public List<CartProductDto> getCartByCustomer(Long customerId) {
        List<CartProductDto> products = new ArrayList<>();
        cartRepository.findProductIdByCustomerIdInCart(customerId)
                .forEach(cart -> {
                    ProductDto product = productService.getProductById(cart.getProductId());
                    products.add(new CartProductDto(cart.getCartId(), product.getProductId(), product.getProductName(), product.getCategory(), product.getPrice() * cart.getQuantity(), product.getDescription(), cart.getQuantity()));
                });
        return products;
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId);
    }

    @Override
    public void deleteCartById(Long cartId) {
        cartRepository.delete(cartId);
    }

    @Override
    public Long addCart(Long customerId, Long productId, int quantity) {
        return cartRepository.save(new Cart(null, customerId, productId, quantity));
    }
}
