package ch.noseryoung.backend.domain.table;

import ch.noseryoung.backend.domain.menu.MenuItem;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
}