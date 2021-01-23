package net.flytre.fguns.guns;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.flytre.fguns.entity.Bullet;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.UUID;

public class Minigun extends GunItem {

    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;


    public Minigun(double damage, double armorPen, double rps, double dropoff, int spray, int range, int clipSize, double reloadTime) {
        super(damage, armorPen, rps, dropoff, spray, range, clipSize, reloadTime, GunType.MINIGUN);

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(UUID.fromString("CB3F88D4-645B-4A38-C198-9C13A444A5CF"), "Weight modifier", -0.25, EntityAttributeModifier.Operation.MULTIPLY_BASE));
        this.attributeModifiers = builder.build();
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public void bulletSetup(World world, PlayerEntity user, Hand hand, Bullet bullet) {
        bullet.setPos(user.getX(), user.getEyeY() - 0.8, user.getZ());
        super.bulletSetup(world, user, hand, bullet);
    }
}