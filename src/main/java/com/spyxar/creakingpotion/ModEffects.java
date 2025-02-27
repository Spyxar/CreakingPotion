package com.spyxar.creakingpotion;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects
{
    public static final RegistryEntry<StatusEffect> DORMANT_STUN = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(CreakingPotion.MOD_ID, "dormant_stun"), new DormantStunEffect());
    public static final RegistryEntry<StatusEffect> STUN = Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(CreakingPotion.MOD_ID, "stun"), new StunEffect());

    public static final Potion DORMANT_STUN_POTION = Registry.register(
            Registries.POTION,
            Identifier.of(CreakingPotion.MOD_ID, "dormant_stun"),
            new Potion("dormant_stun", new StatusEffectInstance(DORMANT_STUN, 2400, 0))
    );

    public static void init()
    {
        CreakingPotion.LOGGER.info("Registering status effects and potions...");

        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(
                    Potions.WATER,
                    Items.RESIN_CLUMP,
                    Registries.POTION.getEntry(DORMANT_STUN_POTION)
            );
        });
    }
}