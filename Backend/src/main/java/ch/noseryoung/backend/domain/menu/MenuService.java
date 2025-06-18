package ch.noseryoung.backend.domain.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    public List<MenuItem> findAll() {
        return menuRepository.findAll();
    }
    
    public List<MenuItem> findAllWithFilters(Boolean chefsChoice, String category, Double minPrice, Double maxPrice) {
        return menuRepository.findAllWithFilters(chefsChoice, category, minPrice, maxPrice);
    }
    
    public Optional<MenuItem> findById(Long id) {
        return menuRepository.findById(id);
    }

    public MenuItem create(MenuItem menuItem) {
        return menuRepository.save(menuItem);
    }

    public MenuItem update(Long id, MenuItem menuItem) {
        menuItem.setId(id);
        return menuRepository.save(menuItem);
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }
}