package com.spyxar.creakingpotion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;

public class StunEffect extends StatusEffect
{
    private ServerPlayerEntity playerThatStunned;

    protected StunEffect()
    {
        super(StatusEffectCategory.HARMFUL, 0xF7B126);
        addAttributeModifier(EntityAttributes.MOVEMENT_SPEED, Identifier.ofVanilla("effect.slowness"), -1.0F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        addAttributeModifier(EntityAttributes.JUMP_STRENGTH, Identifier.ofVanilla("effect.jump_boost"), -1.0F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier)
    {
        if (playerThatStunned != null)
        {
            if (!entity.isEntityLookingAtMe(playerThatStunned, 0.5F, false, true, entity.getEyeY(), entity.getY() + (double) 0.5F * (double) entity.getScale(), (entity.getEyeY() + entity.getY()) / (double) 2.0F))
            {
                entity.removeStatusEffect(ModEffects.STUN);
            }
            return true;
        }

        for (ServerPlayerEntity player : world.getPlayers())
        {
            if (entity.isEntityLookingAtMe(player, 0.5F, false, true, entity.getEyeY(), entity.getY() + (double) 0.5F * (double) entity.getScale(), (entity.getEyeY() + entity.getY()) / (double) 2.0F))
            {
                playerThatStunned = player;
                break;
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}