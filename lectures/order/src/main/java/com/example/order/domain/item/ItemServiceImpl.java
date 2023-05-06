package com.example.order.domain.item;

import com.example.order.domain.partner.Partner;
import com.example.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{
    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;
    @Override
    @Transactional
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {
        Partner partner = partnerReader.getPartnerByToken(partnerToken);
        Item initItem = command.toEntity(partner.getId());
        Item item = itemStore.store(initItem);
        itemOptionSeriesFactory.store(command, item);
        return item.getItemToken();
    }

    @Override
    @Transactional
    public void changeOnSale(String itemToken) {
        Item item = itemReader.getItemBy(itemToken);
        item.changeOnSale();
    }

    @Override
    @Transactional
    public void changeEndOfSale(String itemToken) {
        Item item = itemReader.getItemBy(itemToken);
        item.changeEndOfSale();
    }

    @Override
    @Transactional(readOnly = true)
    public ItemInfo.Main retrieveItemInfo(String itemToken) {
        Item item = itemReader.getItemBy(itemToken);
        Partner partner = partnerReader.getPartnerById(item.getPartnerId());
        List<ItemInfo.ItemOptionGroupInfo> itemOptionGroupInfoList = itemReader.getItemOptionSeries(item);
        return new ItemInfo.Main(item, partner.getPartnerToken(), itemOptionGroupInfoList);
    }
}
