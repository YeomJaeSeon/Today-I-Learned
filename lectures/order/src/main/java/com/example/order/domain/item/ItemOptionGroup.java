package com.example.order.domain.item;

import com.example.order.common.exception.InvalidParamException;
import com.google.common.collect.Lists;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "item_option_groups")
public class ItemOptionGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ordering;
    private String itemOptionGroupName;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemOptionGroup", cascade = CascadeType.PERSIST)
    private List<ItemOption> itemOptionList = Lists.newArrayList();

    public ItemOptionGroup(Item item, Integer ordering, String itemOptionGroupName) {
        if(item == null) throw new InvalidParamException();
        if(ordering == null) throw new InvalidParamException();
        if(StringUtils.isBlank(itemOptionGroupName)) throw new InvalidParamException();

        this.item = item;
        this.ordering = ordering;
        this.itemOptionGroupName = itemOptionGroupName;
    }
}
