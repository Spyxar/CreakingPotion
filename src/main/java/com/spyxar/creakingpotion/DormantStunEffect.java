package com.spyxar.creakingpotion;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class DormantStunEffect extends StatusEffect
{
    protected DormantStunEffect()
    {
        super(StatusEffectCategory.HARMFUL, 0x84807F);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier)
    {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(ServerWorld world, LivingEntity entity, int amplifier)
    {
        for (ServerPlayerEntity player : world.getPlayers())
        {
            if (entity.isEntityLookingAtMe(player, 0.5F, false, true, entity.getEyeY(), entity.getY() + (double) 0.5F * (double) entity.getScale(), (entity.getEyeY() + entity.getY()) / (double) 2.0F))
            {
                entity.removeStatusEffect(ModEffects.DORMANT_STUN);
                entity.addStatusEffect(new StatusEffectInstance(ModEffects.STUN, 60, 0), player);
                break;
            }
        }
        return super.applyUpdateEffect(world, entity, amplifier);
    }
}