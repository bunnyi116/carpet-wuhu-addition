package com.zxy.carpet_wh_addition.mixin.openInv;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.zxy.carpet_wh_addition.OpenInventoryPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

//#if MC > 12108
//$$ @Mixin(value = ServerPlayerEntity.class)
//#else
@Mixin(value = PlayerEntity.class)
//#endif
public class MixinPlayerEntity {
    @WrapOperation(
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/screen/ScreenHandler;canUse(Lnet/minecraft/entity/player/PlayerEntity;)Z"
            ),
            method = "tick"
    )
    public boolean onTick(ScreenHandler instance, PlayerEntity playerEntity, Operation<Boolean> original) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        for (ServerPlayerEntity p : OpenInventoryPacket.playerlist) {
            if (p.equals(player)) {
                return true;
            }
        }
        return original.call(instance, playerEntity);
    }
}