package edu.hw11;

import edu.hw11.task2.ArithmeticUtils;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task2Test {

    public static class Interceptor {
        public static int multiple(int a, int b) {
            return a * b;
        }
    }

    @Test
    @DisplayName("redefinition ArithmeticUtils method")
    public void redefinition() {
        ByteBuddyAgent.install();
        new ByteBuddy()
            .redefine(ArithmeticUtils.class)
            .method(ElementMatchers.named("sum"))
            .intercept(MethodDelegation.to(Interceptor.class))
            .make()
            .load(
                ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        Assertions.assertEquals(10, ArithmeticUtils.sum(2, 5))  ;
    }

}
