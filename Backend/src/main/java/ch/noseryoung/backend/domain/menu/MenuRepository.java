package ch.noseryoung.backend.domain.menu;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuItem, Long> {
}
