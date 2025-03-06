package com.gildedrose;

public class Backstage extends Item{
    public Backstage(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateQuality() {
        this.sellIn = this.sellIn - 1;

        if (this.quality < 50) {
            this.quality = this.quality + 1;

            if (this.sellIn < 11) {
                if (this.quality < 50) {
                    this.quality = this.quality + 1;
                }
            }

            if (this.sellIn < 6) {
                if (this.quality < 50) {
                    this.quality = this.quality + 1;
                }
            }
        }

        if (this.sellIn < 0) {
            this.quality = 0;
        }
    }
}
