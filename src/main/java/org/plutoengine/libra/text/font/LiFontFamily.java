package org.plutoengine.libra.text.font;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class LiFontFamily<F extends LiFont<G, M>, G extends LiFont<G, M>.GlyphAtlas, M extends GlyphMetrics>
{
    public static final String STYLE_BOLD = "bold";
    public static final String STYLE_BLACK = "black";
    public static final String STYLE_REGULAR = "regular";
    public static final String STYLE_BOLD_ITALIC = "bold italic";
    public static final String STYLE_ITALIC = "italic";
    public static final String STYLE_MEDIUM = "medium";

    private final Map<String, F> styles;

    public LiFontFamily()
    {
        this.styles = new HashMap<>();
    }

    public void add(String style, F font)
    {
        this.styles.put(style, font);
        font.family = this;
    }

    public Map<String, F> getStyles()
    {
        return Collections.unmodifiableMap(this.styles);
    }

    public F getStyle(String style)
    {
        return this.styles.get(style);
    }
}
