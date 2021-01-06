package net.onlineStore.repositories;

import net.onlineStore.entities.Category;
import net.onlineStore.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAll(Pageable pageable);

    Page<Product> findAllByCategory(Category category, Pageable pageable);

    @Query(value = "SELECT p.*, c.name as category, pr.name as producer FROM Product p, Category c, Producer pr " +
            "WHERE pr.id=p.id_producer and c.id=p.id_category and " +
            "(p.name ILIKE CONCAT('%',:query,'%')" +
            " or p.description ILIKE CONCAT('%',:query,'%')) " +
            "and c.id IN :categories and pr.id IN :producers", nativeQuery = true)
    Page<Product> findAllBySearchForm(@Param("query") String queryParams,
                                      @Param("categories") List<Integer> categoryIds,
                                      @Param("producers") List<Integer> producerIds,
                                      Pageable pageable);

    Optional<Product> findById(Long id);

//ToDo: после добавления нового продукта инкрементировать количество продуктов в таблице Category
//ToDo: после удаления существующего продукта декрементировать количество продуктов в таблице Category
}
