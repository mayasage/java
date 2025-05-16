package org.blacksage.designpatterns.flyweight.with;

public class TreeType {

        // Intrinsic Immutable State
        // Since this is immutable, it can be shared between instances of Tree.
        private final String name;
        private final String color;

        // package-private constructor -- will only be called by TreeTypeFactory
        TreeType(String name, String color) {
                this.name = name;
                this.color = color;
        }
}
