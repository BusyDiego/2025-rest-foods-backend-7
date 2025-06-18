package ch.noseryoung.backend.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<MenuItem, Long> {
    
    @Query("SELECT m FROM MenuItem m WHERE " +
           "(:chefsChoice IS NULL OR m.chefsChoice = :chefsChoice) AND " +
           "(:category IS NULL OR m.category = :category) AND " +
           "(:minPrice IS NULL OR m.price >= :minPrice) AND " +
           "(:maxPrice IS NULL OR m.price <= :maxPrice)")
    List<MenuItem> findAllWithFilters(@Param("chefsChoice") Boolean chefsChoice,
                                      @Param("category") String category,
                                      @Param("minPrice") Double minPrice,
                                      @Param("maxPrice") Double maxPrice);
}
