package pie.ilikepiefoo.builder;

public class Item {
    private final String name;
    private int durability;
    private int damage;
    private int armor;
    private int weight;
    private int value;

    public Item(final String name) {
        this.name = name;
    }

    public static ItemBuilder newBuilder(final String name) {
        return new ItemBuilder(name);
    }

    public String getName() {
        return this.name;
    }

    public int getDurability() {
        return this.durability;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "Item{name='%s', durability=%d, damage=%d, armor=%d, weight=%d, value=%d}".formatted(this.name, this.durability, this.damage, this.armor, this.weight, this.value);
    }

    public static class ItemBuilder implements Builder<Item> {
        private final String name;
        private int durability;
        private int damage;
        private int armor;
        private int weight;
        private int value;

        public ItemBuilder(final String name) {
            this.name = name;
            this.durability = 0;
            this.damage = 0;
            this.armor = 0;
            this.weight = 0;
            this.value = 0;
        }

        public ItemBuilder setDurability(final int durability) {
            this.durability = durability;
            return this;
        }

        public ItemBuilder setDamage(final int damage) {
            this.damage = damage;
            return this;
        }

        public ItemBuilder setArmor(final int armor) {
            this.armor = armor;
            return this;
        }

        public ItemBuilder setWeight(final int weight) {
            this.weight = weight;
            return this;
        }

        public ItemBuilder setValue(final int value) {
            this.value = value;
            return this;
        }

        public Item build() {
            final Item item = new Item(this.name);
            item.durability = this.durability;
            item.damage = this.damage;
            item.armor = this.armor;
            item.weight = this.weight;
            item.value = this.value;
            return item;
        }
    }
}
