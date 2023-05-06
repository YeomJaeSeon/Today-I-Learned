package com.example.order.infrastructure.item;

import com.example.order.domain.item.Item;
import com.example.order.domain.item.ItemStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemStoreImpl implements ItemStore {
    private final ItemRepository itemRepository;
    @Override
    public Item store(Item item) {
        return itemRepository.save(item);
    }
}
