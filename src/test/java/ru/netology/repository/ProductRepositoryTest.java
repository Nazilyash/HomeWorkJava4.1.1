package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Product first = new Book(1, "Cien anos de soledad", 1740, "Маркес Габриэль Гарсиа", 300, 1982);
    private Product second = new Book(2, "El doble", 880, "Достоевский Федор Михайлович", 567, 1846);
    private Product third = new TShirt(7, "FREEDOM", 499, "blue", "46");
    private Product fourth = new TShirt(16, "JUST DO IT!", 1999, "red", "50");
    private Product fifth = new Book(3, "Blade", 999, "Marvel Comics", 666, 1998);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    public void shouldCheckRemoveByExistingId() {
        int idToRemove = 1;
        repository.removeById(idToRemove);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{second, third, fourth, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldThrowNotFoundException() {
        int idToRemove = 10;
        assertThrows(NotFoundException.class, () -> repository.removeById(idToRemove));
    }
}
