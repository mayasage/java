package org.blacksage.designpatterns.flyweight.without;

public class Tree {

        // External Mutable State
        private int x;
        private int y;

        // Intrinsic Immutable State
        // This can be made immutable and shared.
        private String name;
        private String color;

        public Tree(int x, int y, String name, String color) {
                this.x = x;
                this.y = y;
                this.name = name;
                this.color = color;
        }
}
