package com.zxy.carpet_wh_addition.util;

import net.minecraft.util.Identifier;

public class IdentifierUtils {
    public static Identifier of(String string) {
        //#if MC > 12006
        return Identifier.of(string);
        //#else
        //$$ return new Identifier(string);
        //#endif
    }

    public static Identifier of(String namespace, String path) {
        //#if MC > 12006
        return Identifier.of(namespace, path);
        //#else
        //$$ return new Identifier(namespace, path);
        //#endif
    }
}
