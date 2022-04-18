package org.plutoengine.libra.command;

@FunctionalInterface
public interface IGUIRenderer extends AutoCloseable
{
    void render();

    @Override
    default void close()
    {

    }
}
