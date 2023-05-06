package com.example.order.domain.item;

import com.example.order.domain.item.option.ItemOption;
import com.example.order.domain.item.optiongroup.ItemOptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

public class ItemInfo {
    @Getter
    @ToString
    public static class Main{
        private final String itemToken;
        private final String partnerToken;
        private final String itemName;
        private final Long itemPrice;
        private final Item.Status status;
        private final List<ItemOptionGroupInfo> itemOptionGroupInfoList;

        public Main(Item item, String partnerToken, List<ItemOptionGroupInfo> itemOptionGroupInfoList) {
            this.itemToken = item.getItemToken();
            this.partnerToken = partnerToken;
            this.itemName = item.getItemName();
            this.itemPrice = item.getItemPrice();
            this.status = item.getStatus();
            this.itemOptionGroupInfoList = itemOptionGroupInfoList;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionGroupInfo {
        private final Integer ordering;
        private final String itemOptionGroupName;
        private final List<ItemOptionInfo> itemOptionInfoList;

        public ItemOptionGroupInfo(ItemOptionGroup itemOptionGroup, List<ItemOptionInfo> itemOptionInfoList) {
            this.ordering = itemOptionGroup.getOrdering();
            this.itemOptionGroupName = itemOptionGroup.getItemOptionGroupName();
            this.itemOptionInfoList = itemOptionInfoList;
        }
    }

    @Getter
    @ToString
    public static class ItemOptionInfo {
        private final Integer ordering;
        private final String itemOptionName;
        private Long itemOptionPrice;

        public ItemOptionInfo(ItemOption itemOption) {
            this.ordering = itemOption.getOrdering();
            this.itemOptionName = itemOption.getItemOptionName();
            this.itemOptionPrice = itemOption.getItemOptionPrice();
        }
    }
}
