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

    private void updateQualityIfSellInHasPassed(int i) {
        if (items[i].sellIn < 0) {
            if (!isAgedBrie(i)) {
                if (!isBackstagePasses(i)) {

                    if (items[i].quality > MIN_QUALITY) {
                        if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                            decreaseQuality(i);
                        }
                    }
                } else {
                    items[i].quality = items[i].quality - items[i].quality;
                }
            } else {
                if (items[i].quality < MAX_QUALITY) {
                    increaseQuality(i);
                }
            }
        }
    }

    private void updateSellIn(int i) {
        if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
            decreaseSellIn(i);
        }
    }

    private void updateQuality(int i) {
        if (isAgedBrie(i) || isBackstagePasses(i)) {
            if (items[i].quality < MAX_QUALITY) {
                increaseQuality(i);

                if (items[i].name.equals(BACKSTAGE_PASSES)) {
                    if (items[i].sellIn < 11) {
                        if (items[i].quality < MAX_QUALITY) {
                            increaseQuality(i);
                        }
                    }

                    if (items[i].sellIn < 6) {
                        if (items[i].quality < MAX_QUALITY) {
                            increaseQuality(i);
                        }
                    }
                }
            }
        } else {
            if (items[i].quality > MIN_QUALITY) {
                if (!items[i].name.equals(SULFURAS_HAND_OF_RAGNAROS)) {
                    decreaseQuality(i);
                }
            }

        }
    }

    boolean isAgedBrie(int i) {
        return items[i].name.equals(AGED_BRIE);
    }

    boolean isBackstagePasses(int i) {
        return items[i].name.equals(BACKSTAGE_PASSES);
    }


    private void increaseQuality(int i) {
        items[i].quality = items[i].quality + 1;
    }

    private void decreaseQuality(int i) {
        items[i].quality = items[i].quality - 1;
    }

    private void decreaseSellIn(int i) {
        items[i].sellIn = items[i].sellIn - 1;
    }
}