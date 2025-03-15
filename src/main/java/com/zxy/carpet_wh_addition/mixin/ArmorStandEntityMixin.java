package com.zxy.carpet_wh_addition.mixin;


//#if MC > 12002
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.VehicleEntity;
//#else
//$$ import net.minecraft.entity.vehicle.AbstractMinecartEntity;
//$$ import net.minecraft.entity.vehicle.BoatEntity;
//#endif

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.entity.projectile.ShulkerBulletEntity;

import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.zxy.carpet_wh_addition.config.CarpetWuHuSettings.armorOrBoatStandIgnoredShulkerBullet;

@Mixin(value = {ArmorStandEntity.class,
        //#if MC > 12002
        VehicleEntity.class
        //#else
        //$$ BoatEntity.class,
        //$$ AbstractMinecartEntity.class
        //#endif
})
public class ArmorStandEntityMixin {
    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    //#if MC < 12104
    //$$ public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

    //#else
    public void damage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
    //#endif

        if (armorOrBoatStandIgnoredShulkerBullet && source.getSource() instanceof ShulkerBulletEntity) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
