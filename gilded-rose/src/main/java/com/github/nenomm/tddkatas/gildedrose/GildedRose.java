package com.github.nenomm.tddkatas.gildedrose;

class GildedRose {

  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (int i = 0; i < items.length; i++) {
      final Item item = items[i];

      if ("Sulfuras, Hand of Ragnaros".equals(item.name)) {
        continue;
      }

      if ("Aged Brie".equals(item.name)) {
        if (item.sellIn > 0) {
          item.quality++;
        } else {
          item.quality += 2;
        }
      } else if ("Backstage passes to a TAFKAL80ETC concert".equals(item.name)) {
        if (item.sellIn > 10) {
          item.quality++;
        } else if (item.sellIn > 5) {
          item.quality += 2;
        } else if ((item.sellIn > 0)) {
          item.quality += 3;
        } else {
          item.quality = 0;
        }
      } else if (item.name.startsWith("Conjured")) {
        if (item.sellIn > 0) {
          item.quality -= 2;
        } else {
          item.quality -= 4;
        }
      } else {
        // normal item
        if (item.sellIn > 0) {
          item.quality--;
        } else {
          item.quality -= 2;
        }
      }

      item.quality = Math.max(item.quality, 0);
      item.quality = Math.min(item.quality, 50);

      item.sellIn--;
    }
  }

  public void updateQualityOld() {
    for (int i = 0; i < items.length; i++) {
      if (!items[i].name.equals("Aged Brie")
          && !items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        if (items[i].quality > 0) {
          if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
            items[i].quality = items[i].quality - 1;
          }
        }
      } else {
        if (items[i].quality < 50) {
          items[i].quality = items[i].quality + 1;

          if (items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (items[i].sellIn < 11) {
              if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
              }
            }

            if (items[i].sellIn < 6) {
              if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
              }
            }
          }
        }
      }

      if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
        items[i].sellIn = items[i].sellIn - 1;
      }

      if (items[i].sellIn < 0) {
        if (!items[i].name.equals("Aged Brie")) {
          if (!items[i].name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (items[i].quality > 0) {
              if (!items[i].name.equals("Sulfuras, Hand of Ragnaros")) {
                items[i].quality = items[i].quality - 1;
              }
            }
          } else {
            items[i].quality = items[i].quality - items[i].quality;
          }
        } else {
          if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;
          }
        }
      }
    }
  }
}