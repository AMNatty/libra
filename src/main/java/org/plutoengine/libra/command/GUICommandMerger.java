package org.plutoengine.libra.command;

import java.util.Collection;

class GUICommandMerger
{
    static LiCommandBuffer merge(Collection<LiCommandBuffer> commandBuffers)
    {
        return commandBuffers.stream()
                             .reduce(LiCommandBuffer::pushAll)
                             .orElseGet(LiCommandBuffer::uncleared);
    }
}
