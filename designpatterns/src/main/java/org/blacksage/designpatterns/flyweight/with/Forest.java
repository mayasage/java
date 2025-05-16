package org.blacksage.designpatterns.flyweight.with;

import java.util.ArrayList;
import java.util.List;

public class Forest {

        private final List<Tree> trees = new ArrayList<>();

        // 2 constructors for convenience, but they do the same thing
        // they pass immutable part of the state to Tree constructor.

        public void plantTree(int x, int y, String name, String color) {
                plantTree(x, y, TreeTypeFactory.getTreeType(name, color));
        }

        public void plantTree(int x, int y, TreeType treeType) {
                Tree tree = new Tree(x, y, treeType);
                trees.add(tree);
        }
}
