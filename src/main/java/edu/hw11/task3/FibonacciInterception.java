package edu.hw11.task3;

import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import org.jetbrains.annotations.NotNull;

public class FibonacciInterception implements Implementation {
    @Override
    public @NotNull ByteCodeAppender appender(@NotNull Target target) {
        return (methodVisitor, context, methodDescription) -> {
            return null;
        };
    }

    @Override
    public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType)     {
        return instrumentedType;
    }
}
