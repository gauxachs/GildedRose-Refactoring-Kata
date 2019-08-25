package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private static final String OTHER_ITEM = "OTHER_ITEM";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    public void testCreateItem() {
        String initialName = "foo";
        int initialSellIn = 0;
        int initialQuality = 0;
        Item[] items = new Item[]{new Item(initialName, initialSellIn, initialQuality)};

        String expectedName = "foo";
        int expectedSellIn = 0;
        int expectedQuality = 0;
        assertEquals(expectedName, items[0].name);
        assertEquals(expectedSellIn, items[0].sellIn);
        assertEquals(expectedQuality, items[0].quality);
    }

    @Test
    public void testUpdateOtherItem() {
        int initialQuality = 1;
        int initialSellIn = 1;
        Item[] items = new Item[]{new Item(OTHER_ITEM, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedSellIn = 0;
        int expectedQuality = 0;
        assertEquals(expectedSellIn, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateOtherItemAfterSellInDate() {
        int initialQuality = 10;
        int initialSellIn = 0;
        Item[] items = new Item[]{new Item(OTHER_ITEM, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 8;
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testQualityIsAlwaysPositiveOrZero() {
        int initialQuality = 0;
        int initialSellIn = 0;
        Item[] items = new Item[]{new Item(OTHER_ITEM, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 0;
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateAgedBrie() {
        int initialQuality = 0;
        int initialSellIn = 1;
        Item[] items = new Item[]{new Item(AGED_BRIE, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 1;
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateAgedBrieAfterSellInDate() {
        int initialQuality = 0;
        int initialSellIn = 0;
        Item[] items = new Item[]{new Item(AGED_BRIE, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 2;
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testQualityIsAlwaysLowerOrEqualTo50() {
        int initialQuality = 50;
        int initialSellIn = 1;
        Item[] items = new Item[]{new Item(AGED_BRIE, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 50;
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateSulfuras() {
        int initialQuality = 1;
        int initialSellIn = 1;
        Item[] items = new Item[]{new Item(SULFURAS_HAND_OF_RAGNAROS, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 1;
        assertEquals(1, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesUsualCase() {
        int initialQuality = 1;
        int initialSellIn = 30;
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 2;
        assertEquals(29, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesTenOrLess() {
        int initialQuality = 1;
        int initialSellIn = 10;
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 3;
        assertEquals(9, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesFiveOrLess() {
        int initialQuality = 1;
        int initialSellIn = 5;
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 4;
        assertEquals(4, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesZeroDays() {
        int initialQuality = 10;
        int initialSellIn = 0;
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, initialSellIn, initialQuality)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        int expectedQuality = 0;
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(expectedQuality, app.items[0].quality);
    }
}
