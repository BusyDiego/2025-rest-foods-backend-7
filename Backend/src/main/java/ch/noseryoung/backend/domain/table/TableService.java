package ch.noseryoung.backend.domain.table;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;

    public List<RestaurantTable> findAll() {
        return tableRepository.findAll();
    }

    public Optional<RestaurantTable> findById(Long id) {
        return tableRepository.findById(id);
    }

    public RestaurantTable create(RestaurantTable table) {
        return tableRepository.save(table);
    }

    public RestaurantTable update(Long id, RestaurantTable table) {
        table.setId(id);
        return tableRepository.save(table);
    }

    public void delete(Long id) {
        tableRepository.deleteById(id);
    }
}
