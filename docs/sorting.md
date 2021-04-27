# Day 26: Insertion Sort

## Sorting

Sorting is a fundamental tasks in computer science. The idea is simple. We want to take a list of items that can be
compared, and produce a list of those same items in sorted order --- meaning every item in the list is no greater than
the next. Sorting can be done in-place on an array, meaning the original unsorted array is mutated and items are swapped
until that array is sorted. Sorting can also be done "out of place", meaning the original array is untouched and a new
sorted array is produced. This week we'll take a look at several basic sorting algorithms and investigate their
properties.

## The idea

Insertion sort is a basic and easy-to-understand algorithm for sorting an array. The idea is that we're sorting the list
in place from beginning to end. After the `i`th step, we want the first `i` elements of the list to be rearranged in
sorted order from the original order.

This means that at the beginning of the `i`th step, the first `i-1` elements should be the first `i-1` elements from the
original list in sorted order. At the end of the `i`th step, we'd like the first `i` elements to be in sorted order. We
can simply take the `i`th element and insert it into the first `i-1` elements of the array. All the elements less than
the `i`th element will remain untouched and all the elements greater than the `i`th will be shifted to the right to
accommodate the newly inserted element.

## An example

We're going to sort the list:

```
[5, 3, 2, 1, 4]
```

The first step involves sorting the sub-array containing the first element. We're already done! Any array with one
element is already sorted.

The second step involves sorting the sub-array containing the first 2 elements.

```
Step 2:
[5, 3, 2, 1, 4]
[3, 5, 2, 1, 4]
 **** <- the sorted part of the array
```

You can see that in step two we had to insert 3 before 5 and ended up with the first two elements sorted.

```
Step 3:
[3, 5, 2, 1, 4]
[2, 3, 5, 1, 4]
 *******
```

In step 3 we insert 2 in the beginning of the sub-array because it's less than both elements that have already been
sorted.

```
Step 4:
[2, 3, 5, 1, 4]
[1, 2, 3, 5, 4]
 **********
```

Then we move 1 to the beginning of the sub-array because it's less than all the other elements that have been sorted.
We've now sorted the first 4 elements of the array.

```
Step 5:
[2, 3, 5, 1, 4]
[1, 2, 3, 4, 5]
 *************
```

Finally, 4 is inserted before 5 and all of our array is sorted.

## The pseudo-pseudo code

From this example we can come up with an algorithm:

* For `i` from `1` through `n`, the length of the array:
    * Shift any element from the first `i-1` elements greater than the `i`th element one position to the right.
    * Insert the `i`th element into the first `i-i` elements in the left-most position where it isn't less than the
      element to its left.

## The pseudo-code

Note that in the above examples we've been referring to the `i`th element counting from the first. In the following
pseudocode the array is indexed from 0.

```
for i from 1 to n:
    j <- i - 1
    val <- array[i]
    while (j >= 0 and value < array[j]):
        array[j + 1] <- array[j]
        j <- j - 1
    array[j + 1] <- value
```

## Efficiency

In each step we need to insert an element into a sorted array, shifting everything greater than the element one to the
right. Inserting into an array is an ![formula](https://render.githubusercontent.com/render/math?math=O(n)) operation
since some fraction of the list will have to be copied and moved one position to the right. Since we're performing this
operation once for every element in the list, our algorithm runs
in ![formula](https://render.githubusercontent.com/render/math?math=O(n^2)) time.

## Can we do better?

As it turns out, we can sort better than ![formula](https://render.githubusercontent.com/render/math?math=O(n^2)). An
unsorted list with `n` unique elements can be thought of as a permutation of the elements in the list. Sorting that list
performs a unique sequence of steps for each permutation `n` elements, since two different permutations need to be
sorted differently to end up with a sorted list. Since there
are ![formula](https://render.githubusercontent.com/render/math?math=n!) permutations of `n` elements, we need to
perform enough comparisons to uniquely determine which of
the ![formula](https://render.githubusercontent.com/render/math?math=n!) permutations our list started out with.

How many comparisons is that? We can think of each comparison as dividing our set of possible permutations in half,
since our comparison has two possibilities. So performing `x` comparisons narrows our set of possible permutations down
by a factor of ![formula](https://render.githubusercontent.com/render/math?math=2^x). That means we need to perform at
least `x` comparisons to sort a list of `n` elements, where `x` is the smallest number such
that ![formula](https://render.githubusercontent.com/render/math?math=2^x=n!). So intuitively, comparing the growth rate
of ![formula](https://render.githubusercontent.com/render/math?math=2^x)
and ![formula](https://render.githubusercontent.com/render/math?math=n!) should tell us something about the growth rate
of the best possible sorting algorithm. It turns out that the solving the
equation ![formula](https://render.githubusercontent.com/render/math?math=2^x=n!) for `x` tells us that best possible
sorting algorithm can run no better than ![formula](https://render.githubusercontent.com/render/math?math=O(n\log%20n)).

(How do we do this? Take the logarithm of both sides and then
use [Stirling's Approximation](https://en.wikipedia.org/wiki/Stirling's_approximation) of the factorial function).

The point of all this mathematics is to demonstrate that no sorting algorithm can do better
than ![formula](https://render.githubusercontent.com/render/math?math=O(n\log%20n)). We say that this is a lower bound
on the time efficiency of sorting. However, it doesn't actually demonstrate that such a sorting algorithm exists. As it
turns out, there are many algorithms that sort
in ![formula](https://render.githubusercontent.com/render/math?math=O(n\log%20n)) time and later this week we'll explore
some of them.

# Day 26 bonus round: Heap sort

