plugins {
    id("fuzs.multiloader.multiloader-convention-plugins-common")
}

dependencies {
    modCompileOnlyApi(sharedLibs.puzzleslib.common)
    modCompileOnlyApi(sharedLibs.iteminteractions.common)
}

multiloader {
    mixins {
        mixin("InventoryMixin")
    }
}
