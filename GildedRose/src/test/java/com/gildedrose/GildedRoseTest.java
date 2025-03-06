package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    void TestSulfuras(){
        Item[] items = new Item[] {new Sulfuras("Sulfuras, Hand of Ragnaros", 0, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros, 0, 80", app.items[0].toString());
    }

    @Test
    void TestDatePeremption(){
        Item[] items = new Item[] {new Item("Livre", 0, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Livre, -1, 28", app.items[0].toString());
    }

    @Test
    void TestConjured(){
        Item[] items = new Item[] {new Conjured("Conjured", 10, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured, 9, 28", app.items[0].toString());
    }

    @Test
    void TestConjuredPeremption(){
        Item[] items = new Item[] {new Conjured("Conjured", 0, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured, -1, 26", app.items[0].toString());
    }

    @Test
    void TestConjuredQualite1(){
        Item[] items = new Item[] {new Conjured("Conjured", 0, 1)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured, -1, 0", app.items[0].toString());
    }

    @Test
    void TestAgedBrie(){
        Item[] items = new Item[] {new AgedBrie("Aged Brie", 10, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie, 9, 31", app.items[0].toString());
    }

    @Test
    void TestAgedBriePeremption(){
        Item[] items = new Item[] {new AgedBrie("Aged Brie", 0, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie, -1, 32", app.items[0].toString());
    }

    @Test
    void TestAgedBrieQualite50(){
        Item[] items = new Item[] {new AgedBrie("Aged Brie", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie, 9, 50", app.items[0].toString());
    }

    @Test
    void TestBackstage10jours(){
        Item[] items = new Item[] {new Backstage("Backstage passes to a TAFKAL80ETC concert", 10, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 9, 32", app.items[0].toString());
    }

    @Test
    void TestBackstage5jours(){
        Item[] items = new Item[] {new Backstage("Backstage passes to a TAFKAL80ETC concert", 5, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 4, 33", app.items[0].toString());
    }

    @Test
    void TestBackstagePeremption(){
        Item[] items = new Item[] {new Backstage("Backstage passes to a TAFKAL80ETC concert", 0, 30)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", app.items[0].toString());
    }

    @Test
    void TestBackstageQualite50(){
        Item[] items = new Item[] {new Backstage("Backstage passes to a TAFKAL80ETC concert", 10, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert, 9, 50", app.items[0].toString());
    }
}
