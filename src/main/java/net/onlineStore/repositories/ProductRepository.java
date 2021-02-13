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

    Page<Product> findAllByAmountGreaterThan(Integer minAmount, Pageable pageable);

    Page<Product> findAllByAmountGreaterThanAndCategory(Integer minAmount, Category category, Pageable pageable);

    @Query(value = "select p.* FROM Product p\n" +
            "INNER JOIN category c on p.id_category = c.id " +
            "INNER JOIN producer pr on p.id_producer = pr.id " +
            "WHERE pr.id=p.id_producer and c.id=p.id_category and " +
            "(p.name ILIKE CONCAT('%',:query,'%') or p.description ILIKE CONCAT('%',:query,'%')) and " +
            "c.id IN :categories and pr.id IN :producers " +
            "and p.amount > 0", nativeQuery = true)
    Page<Product> findAllBySearchFormByAmountGreaterThanZero(@Param("query") String queryParams,
                                      @Param("categories") List<Integer> categoryIds,
                                      @Param("producers") List<Integer> producerIds,
                                      Pageable pageable);

    @Query(value = "SELECT pr.* FROM Product pr " +
            "INNER JOIN category c on pr.id_category = c.id " +
            "INNER JOIN producer p on pr.id_producer = p.id " +
            "WHERE p.id=pr.id_producer and c.id=pr.id_category and " +
            "    (CAST(pr.id as varchar) ILIKE CONCAT('%',:query,'%') or " +
            "     pr.name ILIKE CONCAT('%',:query,'%') " +
            "        or pr.description ILIKE CONCAT('%',:query,'%') " +
            "        or c.name ILIKE CONCAT('%',:query,'%')) " +
            "        or p.name ILIKE CONCAT('%',:query,'%') order by pr.id", nativeQuery = true)
    Page<Product> searchProductsByIdOrNameOrDescOrCategoryOrProducer(@Param("query") String query,
                                                                     Pageable pageable);

    Optional<Product> findById(Long id);

//ToDo: после добавления нового продукта инкрементировать количество продуктов в таблице Category
//ToDo: после удаления существующего продукта декрементировать количество продуктов в таблице Category
}
