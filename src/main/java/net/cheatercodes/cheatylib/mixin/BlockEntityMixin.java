package net.cheatercodes.cheatylib.mixin;

import net.cheatercodes.cheatylib.BlockEntityExtension;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntity.class)
class BlockEntityMixin {
    @Mutable @Final @Shadow
    private BlockEntityType<?> type;

    @Inject(method="<init>*", at=@At("RETURN"))
    private void onConstructed(BlockEntityType<?> blockEntityType, CallbackInfo callbackInfo) {
        if(this instanceof BlockEntityExtension) {
            this.type = ((BlockEntityExtension)this).getBlockEntityType();
        }
    }
}