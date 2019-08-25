package com.gildedrose;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    private static final String OTHER_ITEM = "OTHER_ITEM";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    @Test
    public void foo() {
        Item[] items = new Item[]{new Item("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    public void testUpdateOtherItem() {
        Item[] items = new Item[]{new Item(OTHER_ITEM, 1, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void testUpdateOtherItemAfterSellInDate() {
        Item[] items = new Item[]{new Item(OTHER_ITEM, 0, 10)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertEquals(8, app.items[0].quality);
    }

    @Test
    public void testQualityIsAlwaysPositiveOrZero() {
        Item[] items = new Item[]{new Item(OTHER_ITEM, 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void testUpdateAgedBrie() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 1, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void testUpdateAgedBrieAfterSellInDate() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }

    @Test
    public void testQualityIsAlwaysLowerOrEqualTo50() {
        Item[] items = new Item[]{
                new Item(AGED_BRIE, 1, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }

    @Test
    public void testUpdateSulfuras() {
        Item[] items = new Item[]{new Item(SULFURAS_HAND_OF_RAGNAROS, 1, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesUsualCase() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 30, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(29, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesTenOrLess() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 10, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(9, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesFiveOrLess() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 5, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    public void testUpdateBackStagePassesZeroDays() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }
}
