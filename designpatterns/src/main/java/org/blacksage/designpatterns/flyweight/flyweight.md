# Flyweight

The name comes from a wrestler who ways < 51kgs.

## Usage

When you want to create millions of instances, but it's filling up the RAM due to memory-expensive fields.
Example: A Forest where you want to plant millions of trees. If you put a custom sprite of say, 20KB, it will take 20GB  
for 1 million instances.

So, you extract a common immutable part of the state out and use it as a reference in all your instances.  
This will make you go from 20GB → 20KB (save sprite in one class and reuse it in all the instances as a reference).

## Warning

This creates a hell lot of complexity, as you can see.  
This is strictly a last-choice optimization technique.  
This is only needed when you are creating millions of instances of memory-intensive properties.  
You are trading off on CPU cycles. Every time you create a Tree, you need a TreeType, which requires additional CPU
cycles to compute.
