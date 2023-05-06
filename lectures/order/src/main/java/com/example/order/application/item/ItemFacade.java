package com.example.order.application.item;

import com.example.order.domain.item.ItemCommand;
import com.example.order.domain.item.ItemInfo;
import com.example.order.domain.item.ItemService;
import com.example.order.domain.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ItemFacade {
    private final ItemService itemService;
    private final NotificationService notificationService;

    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken){
        String itemToken = itemService.registerItem(command, partnerToken);
        notificationService.sendEmail(null, null, null);
        return itemToken;
    }

    public void changeOnSaleItem(String itemToken){
        itemService.changeOnSale(itemToken);
    }
    public void changeEndOfSaleItem(String itemToken){
        itemService.changeEndOfSale(itemToken);
    }
    public ItemInfo.Main retrieveItemInfo(String itemToken){
        return itemService.retrieveItemInfo(itemToken);
    }
}
