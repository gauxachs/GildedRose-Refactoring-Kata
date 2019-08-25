package com.gildedrose;

class GildedRose {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            updateQuality(i);

            updateSellIn(i);

            updateQualityIfSellInHasPassed(i);
        }
    }

    private void updateQuality(int i) {
        if (isAgedBrie(i) || isBackstagePasses(i)) {
            increaseQuality(i);

            if (items[i].name.equals(BACKSTAGE_PASSES)) {
                if (items[i].sellIn < 11) {
                    increaseQuality(i);
                }

                if (items[i].sellIn < 6) {
                    increaseQuality(i);
                }
            }
        } else {
            decreaseQuality(i);
        }
    }

    private void updateQualityIfSellInHasPassed(int i) {
        if (items[i].sellIn < 0) {
            if (isAgedBrie(i)) {
                increaseQuality(i);
            } else {
                if (isBackstagePasses(i)) {
                    items[i].quality = items[i].quality - items[i].quality;
                } else {
                    decreaseQuality(i);
                }
            }
        }
    }

    private void updateSellIn(int i) {
        if (!isSulfuras(i)) {
            decreaseSellIn(i);
        }
    }

    boolean isAgedBrie(int i) {
        return items[i].name.equals(AGED_BRIE);
    }

    boolean isBackstagePasses(int i) {
        return items[i].name.equals(BACKSTAGE_PASSES);
    }

    boolean isSulfuras(int i) {
        return items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS);
    }

    private void increaseQuality(int i) {
        if (items[i].quality < MAX_QUALITY) {
            items[i].quality = items[i].quality + 1;
        }
    }

    private void decreaseQuality(int i) {
        if (items[i].quality > MIN_QUALITY && !isSulfuras(i)) {
            items[i].quality = items[i].quality - 1;
        }
    }

    private void decreaseSellIn(int i) {
        items[i].sellIn = items[i].sellIn - 1;
    }
}