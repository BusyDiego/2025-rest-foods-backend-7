package ch.noseryoung.backend.domain.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuItem> getAllMenuItems() {
        return menuRepository.findAll();
    }
}