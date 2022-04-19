package org.plutoengine.libra.text.shaping;

import org.intellij.lang.annotations.MagicConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@MagicConstant(stringValues = {
    TextStyleOptions.STYLE_REGULAR,
    TextStyleOptions.STYLE_BOLD,
    TextStyleOptions.STYLE_ITALIC,
    TextStyleOptions.STYLE_BOLD_ITALIC,
    TextStyleOptions.STYLE_BLACK,
    TextStyleOptions.STYLE_MEDIUM
})
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE_USE})
public @interface FontStyleValue
{
}
