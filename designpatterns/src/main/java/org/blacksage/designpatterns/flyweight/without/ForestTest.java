package org.blacksage.designpatterns.flyweight.without;

import org.junit.Test;

public class ForestTest {

        @Test
        public void callForest() {
                Forest forest = new Forest();
                forest.plantTree(10, 10, "Tree1", "Red");
                forest.plantTree(20, 20, "Tree2", "Green");
                forest.plantTree(30, 30, "Tree3", "Blue");
        }

}