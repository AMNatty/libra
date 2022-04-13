module libra.core
{
    requires transitive org.jetbrains.annotations;
    requires transitive org.plutoengine.plutolib;

    exports org.plutoengine.libra;
    exports org.plutoengine.libra.command;
    exports org.plutoengine.libra.command.impl;
    exports org.plutoengine.libra.paint;
    exports org.plutoengine.libra.text;
    exports org.plutoengine.libra.text.font;
    exports org.plutoengine.libra.text.shaping;
}