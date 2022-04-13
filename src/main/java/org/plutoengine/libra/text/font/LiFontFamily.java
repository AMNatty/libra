package org.plutoengine.libra.text.font;

import org.plutoengine.libra.text.shaping.FontStyleValue;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class LiFontFamily<F extends LiFont<?, ?>> implements AutoCloseable
{
    private final Map<String, F> styles;

    public LiFontFamily()
    {
        this.styles = new HashMap<>();
    }

    public void add(@FontStyleValue String style, F font)
    {
        this.styles.put(style, font);
        font.family = this;
    }

    public Map<String, F> getStyles()
    {
        return Collections.unmodifiableMap(this.styles);
    }

    public F getStyle(@FontStyleValue String style)
    {
        return this.styles.get(style);
    }

    @Override
    public void close()
    {
        this.styles.values()
            .forEach(LiFont::close);
    }
}
