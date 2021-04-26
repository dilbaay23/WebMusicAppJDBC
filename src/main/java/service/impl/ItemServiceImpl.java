package service.impl;

import entity.Item;
import repo.ItemRepository;
import repo.impl.ItemRepositoryImpl;
import service.ItemService;

/**
 * Created by Moon on 25/04/2021
 */
public class ItemServiceImpl implements ItemService {
   private final ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    public int save(Item item) {
        return itemRepository.save(item);
    }
}
