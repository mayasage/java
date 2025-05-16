package org.blacksage.designpatterns.flyweight.with;

import java.util.HashMap;

public class TreeTypeFactory {

        // cache every instance for re-usability.
        private static final HashMap<String, TreeType> treeTypes = new HashMap<>();

        public static TreeType getTreeType(String name, String color) {
                TreeType treeType = treeTypes.get(name + "-" + color);
                if (treeType == null) {
                        treeType = new TreeType(name, color);
                        treeTypes.put(name + color, treeType);
                }
                return treeType;
        }

        // block constructor
        private TreeTypeFactory() {}
}
