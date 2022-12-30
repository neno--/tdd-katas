package com.github.nenomm.tddkatas.gildedrose;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GildedRoseTest {

  Item item;

  @Test
  void sulfurasValuesAlwaysStayTheSame() {
    final String name = "Sulfuras, Hand of Ragnaros";
    final int initialSellin = 0;
    final int initialQuality = 80;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(initialSellin, app.items[0].sellIn);
    assertEquals(initialQuality, app.items[0].quality);
  }

  @Test
  void commonItemDecreaseInValueByOne() {
    final String name = "Elixir of the Mongoose";
    final int initialSellin = 5;
    final int initialQuality = 3;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(4, app.items[0].sellIn);
    assertEquals(2, app.items[0].quality);
  }

  @Test
  void commonItemMinQualityIsZero() {
    final String name = "Elixir of the Mongoose";
    final int initialSellin = 5;
    final int initialQuality = 0;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(4, app.items[0].sellIn);
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void commonItemQualityDecreasesTwiceAsFastAfterSellByDate() {
    final String name = "Elixir of the Mongoose";
    final int initialSellin = 0;
    final int initialQuality = 5;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(3, app.items[0].quality);
  }

  @Test
  void commonItemQualityDecreasesTwiceAsFastAfterSellByDateButCannotBeLessThanZero() {
    final String name = "Elixir of the Mongoose";
    final int initialSellin = 0;
    final int initialQuality = 1;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void agedBrieIncreasesInQuality() {
    final String name = "Aged Brie";
    final int initialSellin = 5;
    final int initialQuality = 1;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(4, app.items[0].sellIn);
    assertEquals(2, app.items[0].quality);
  }

  @Test
  void agedBrieIncreasesInQualityTwiceAsFastAfterSellByDate() {
    final String name = "Aged Brie";
    final int initialSellin = 0;
    final int initialQuality = 1;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(3, app.items[0].quality);
  }

  @Test
  void agedBrieMaxQualityIsFifty() {
    final String name = "Aged Brie";
    final int initialSellin = 5;
    final int initialQuality = 50;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(4, app.items[0].sellIn);
    assertEquals(50, app.items[0].quality);
  }

  @Test
  void agedBrieMaxQualityIncreasesTwiceAsFastAfterSellByDateButCannotBeMoreThanFifty() {
    final String name = "Aged Brie";
    final int initialSellin = 0;
    final int initialQuality = 49;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(50, app.items[0].quality);
  }

  @Test
  void backstagePassesIncreaseInQualityByOne() {
    final String name = "Backstage passes to a TAFKAL80ETC concert";
    final int initialSellin = 15;
    final int initialQuality = 20;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(14, app.items[0].sellIn);
    assertEquals(21, app.items[0].quality);
  }

  @Test
  void backstagePassesIncreaseInQualityByTwoWhenThereAreTenDaysLeft() {
    final String name = "Backstage passes to a TAFKAL80ETC concert";
    final int initialSellin = 10;
    final int initialQuality = 20;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(9, app.items[0].sellIn);
    assertEquals(22, app.items[0].quality);
  }

  @Test
  void backstagePassesIncreaseInQualityByThreeWhenThereAreFiveDaysLeft() {
    final String name = "Backstage passes to a TAFKAL80ETC concert";
    final int initialSellin = 1;
    final int initialQuality = 20;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(0, app.items[0].sellIn);
    assertEquals(23, app.items[0].quality);
  }

  @Test
  void backstagePassesQualityDropsToZeroAfterTheConcert() {
    final String name = "Backstage passes to a TAFKAL80ETC concert";
    final int initialSellin = 0;
    final int initialQuality = 20;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void conjuredItemDecreaseInValueByTwo() {
    final String name = "Conjured Elixir of the Mongoose";
    final int initialSellin = 5;
    final int initialQuality = 3;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(4, app.items[0].sellIn);
    assertEquals(1, app.items[0].quality);
  }

  @Test
  void conjuredItemMinQualityIsZero() {
    final String name = "Conjured Elixir of the Mongoose";
    final int initialSellin = 5;
    final int initialQuality = 0;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(4, app.items[0].sellIn);
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void conjuredItemQualityDecreasesTwiceAsFastAfterSellByDate() {
    final String name = "Conjured Elixir of the Mongoose";
    final int initialSellin = 0;
    final int initialQuality = 5;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(1, app.items[0].quality);
  }

  @Test
  void conjuredItemQualityDecreasesTwiceAsFastAfterSellByDateButCannotBeLessThanZero() {
    final String name = "Conjured Elixir of the Mongoose";
    final int initialSellin = 0;
    final int initialQuality = 1;

    // given
    item = new Item(name, initialSellin, initialQuality);
    GildedRose app = new GildedRose(new Item[]{item});

    // when
    app.updateQuality();

    // then
    assertEquals(name, app.items[0].name);
    assertEquals(-1, app.items[0].sellIn);
    assertEquals(0, app.items[0].quality);
  }
}