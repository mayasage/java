package org.blacksage.designpatterns.flyweight.without;

import java.util.ArrayList;
import java.util.List;

public class Forest {

        private final List<Tree> trees = new ArrayList<>();

        // Stores the entire object (both mutable and immutable for every tree).
        // The immutable part of the state can be shared between instances to reduce RAM.
        public void plantTree(int x, int y, String name, String color) {
                Tree tree = new Tree(x, y, name, color);
                trees.add(tree);
        }
}
