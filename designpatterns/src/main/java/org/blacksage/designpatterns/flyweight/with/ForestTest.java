package org.blacksage.designpatterns.flyweight.with;

import org.junit.Test;

public class ForestTest {

        @Test
        public void callForest() {
                Forest forest = new Forest();

                // This will work the same.
                forest.plantTree(10, 10, "Tree1", "Red");
                forest.plantTree(20, 20, "Tree2", "Green");
                forest.plantTree(30, 30, "Tree3", "Blue");

                // This is also possible now.
                forest = new Forest();
                TreeType cherryBlossom = TreeTypeFactory.getTreeType("Cherry Blossom", "Cherry Blossom Pink");
                forest.plantTree(10, 10, cherryBlossom);
                forest.plantTree(20, 20, cherryBlossom);
                forest.plantTree(30, 30, cherryBlossom);
        }

}