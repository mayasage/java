package org.blacksage.designpatterns.flyweight.with;

public class Tree {

        // External Mutable State
        private final int x;
        private final int y;
        private final TreeType treeType;

        // Receive intrinsic state as constructor argument.

        public Tree(int x, int y, TreeType treeType) {
                this.x = x;
                this.y = y;
                this.treeType = treeType;
        }
}
