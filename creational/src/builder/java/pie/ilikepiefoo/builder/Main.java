
package pie.ilikepiefoo.builder;

public class Main {
    public static void main(final String[] args) {
        final var sword = Item.newBuilder("Generic Sword")
                .setDurability(100)
                .setDamage(10)
                .setArmor(0)
                .setValue(100)
                .setWeight(2)
                .build();
        System.out.println(sword);
        final var shield = Item.newBuilder("Generic Shield")
                .setDurability(100)
                .setDamage(0)
                .setArmor(10)
                .setValue(100)
                .setWeight(5)
                .build();
        System.out.println(shield);
        final var armor = Item.newBuilder("Generic Armor")
                .setDurability(100)
                .setDamage(0)
                .setArmor(10)
                .setValue(200)
                .setWeight(10)
                .build();
        System.out.println(armor);
    }
}
