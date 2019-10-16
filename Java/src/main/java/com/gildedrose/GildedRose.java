package com.gildedrose;

import java.util.Arrays;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    Item[] items;

    //TODO: Create classes and move the logic for each type of item.
    //TODO: Move constants to external class.
    //TODO: Check magic numbers

    public GildedRose(Item[] items) {
        this.items = items;
    }

    void updateQuality() {
        Arrays.stream(items).forEach(item -> {
            updateQuality(item);
            updateSellIn(item);
            updateQualityIfSellInHasPassed(item);
        });
    }

    private void updateQuality(Item item) {
        if (isAgedBrie(item)) {
            increaseQuality(item);
        } else if (isBackstagePasses(item)) {
            increaseQuality(item);
            if (item.sellIn < 11) {
                increaseQuality(item);
            }
            if (item.sellIn < 6) {
                increaseQuality(item);
            }
        } else {
            decreaseQuality(item);
        }
    }

    private void updateSellIn(Item item) {
        if (!isSulfuras(item)) {
            decreaseSellIn(item);
        }
    }

    private void updateQualityIfSellInHasPassed(Item item) {
        if (item.sellIn < 0) {
            if (isAgedBrie(item)) {
                increaseQuality(item);
            } else if (isBackstagePasses(item)) {
                item.quality = 0;
            } else {
                decreaseQuality(item);
            }

        }
    }

    boolean isAgedBrie(Item item) {
        return item.name.equals(AGED_BRIE);
    }

    boolean isBackstagePasses(Item item) {
        return item.name.equals(BACKSTAGE_PASSES);
    }

    boolean isSulfuras(Item item) {
        return item.name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private void increaseQuality(Item item) {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseQuality(Item item) {
        if (item.quality > MIN_QUALITY && !isSulfuras(item)) {
            item.quality = item.quality - 1;
        }
    }

    private void decreaseSellIn(Item item) {
        item.sellIn = item.sellIn - 1;
    }
}