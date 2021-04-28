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

- For `i` from `1` through `n`, the length of the array:
  - Shift any element from the first `i-1` elements greater than the `i`th element one position to the right.
  - Insert the `i`th element into the first `i-i` elements in the left-most position where it isn't less than the
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
right. Inserting into an array is an ![formula](<https://render.githubusercontent.com/render/math?math=O(n)>) operation
since some fraction of the list will have to be copied and moved one position to the right. Since we're performing this
operation once for every element in the list, our algorithm runs
in ![formula](<https://render.githubusercontent.com/render/math?math=O(n^2)>) time.

## Can we do better?

As it turns out, we can sort better than ![formula](<https://render.githubusercontent.com/render/math?math=O(n^2)>). An
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
sorting algorithm can run no better than ![formula](<https://render.githubusercontent.com/render/math?math=O(n\log%20n)>).

(How do we do this? Take the logarithm of both sides and then
use [Stirling's Approximation](https://en.wikipedia.org/wiki/Stirling's_approximation) of the factorial function).

The point of all this mathematics is to demonstrate that no sorting algorithm can do better
than ![formula](<https://render.githubusercontent.com/render/math?math=O(n\log%20n)>). We say that this is a lower bound
on the time efficiency of sorting. However, it doesn't actually demonstrate that such a sorting algorithm exists. As it
turns out, there are many algorithms that sort
in ![formula](<https://render.githubusercontent.com/render/math?math=O(n\log%20n)>) time and later this week we'll explore
some of them.

# Day 27: Merge Sort

We've hinted that we can do better than ![formula](<https://render.githubusercontent.com/render/math?math=O(n^2)>) for
sorting. We've shown that our lower bound
is ![formula](<https://render.githubusercontent.com/render/math?math=O(n\log%20n)>). Today we'll focus on an algorithm
that actually meets that lower bound. Intuitively we except logarithms to show up in the time complexity when our
problem can be broken down recursively in tree-like structures. Merge sort is exactly a way to do that. We observe that
two sorted lists can be merged into one in ![formula](<https://render.githubusercontent.com/render/math?math=O(n)>) time,
where `n` is the total number of elements from both lists. This suggests a recursive approach: sort the first half of
the list, then sort the second half of the list, then merge those halves together. We'll investigate this approach and
its efficiency.

## The merge

The simplest way to merge two arrays is as follows:

- Allocate a new array with size equal to the sum of the sizes of the input arrays
- Initialize indices `i`, `j`, and `k` as 0 to keep track of positions in the three arrays.
- Compare the values of the first array at `i` and the second array at `j`, and add the smaller of the two to the result
  array.
- When we've reached the end of either of the input arrays, add the remaining elements from the other array to the
  result array.

With this algorithm in hand, we can define our merge sort algorithm as follows:

- If an array is "small enough", it can be sorted using simple methods. For instance, an array of size 1 is always
  sorted. An array of size 2 can be sorted with a simple comparison.
- For an array with n elements, split the array into two subarrays with
  size ![formula](https://render.githubusercontent.com/render/math?math=\left\lfloor\frac{n}{2}\right\rfloor) and
  size ![formula](https://render.githubusercontent.com/render/math?math=n-\left\lfloor\frac{n}{2}\right\rfloor).
- Sort both of those arrays recursively.
- Merge the sorted arrays.
- Return the result.

## Efficiency

We'll analyze the efficiency of this simple merge sort algorithm in two different ways. First, imagine the recursive
calls of mergesort forming a tree of sub-problems, where the sub-problems are arrays to be sorted. Since each problem is
broken down into two sub-problems, this will be a binary tree. Every node in the tree will get called exactly once
during the execution of the algorithm. Consider the nodes from one level of the tree. The sum of the sizes of the arrays
from that level will be exactly `n`, the number of elements in the original array, because splitting an array up into
sub-arrays doesn't change the total number of elements. Finally, the tree will
have ![formula](https://render.githubusercontent.com/render/math?math=\log%20n) levels, since each level halves the
sizes of the arrays.

![Merge sort](../assets/merge-sort.png)

Armed with these two facts, we can analyze the time and space efficiency of this algorithm from a top-down perspective.
Completing the sub-problems on each level takes a total
of ![formula](<https://render.githubusercontent.com/render/math?math=O(n)>) time and space, since copying and merging
arrays are both linear in the total number of elements. Since there
are ![formula](https://render.githubusercontent.com/render/math?math=\log%20n) levels, this gives us
the ![formula](<https://render.githubusercontent.com/render/math?math=O(n\log%20n)>) running time that we're looking for.

### The master theorem

There's another way to demonstrate the ![formula](<https://render.githubusercontent.com/render/math?math=O(n\log%20n)>)
running time of the merge sort algorithm. Recursive processes come up all the time in algorithms for various problems.
We can often express a relationship about the running time of an algorithm as an equation of the following form:

![formula](<https://render.githubusercontent.com/render/math?math=T(n)=aT\left(\frac{n}{b}\right)%2Bf(n)>)

We can imagine the ![formula](<https://render.githubusercontent.com/render/math?math=T\left(\frac{n}{b}\right)>) term as
describing the time it takes to solve a number of sub-problems, and
the ![formula](<https://render.githubusercontent.com/render/math?math=f(n)>) term as describing some amount of additional
overhead involved in breaking a problem up into sub-problems or combining those solutions into a master solution.

The [master theorem](<https://en.wikipedia.org/wiki/Master_theorem_(analysis_of_algorithms)>) allows us to analyze an
algorithm whose running time satisfies a recurrence relation of the above form, given certain constraints on the values
of `a`, `b`, and `f(n)`. In our case we have
values ![formula](https://render.githubusercontent.com/render/math?math=a%2D2)
, ![formula](https://render.githubusercontent.com/render/math?math=b%2D2)
, and ![formula](<https://render.githubusercontent.com/render/math?math=f(n)%2Dn>), which gives us the running time that
we're trying to demonstrate.

## A matter of space

One notable difference between our merge sort algorithm and our insertion sort algorithm is that our merge sort produces
a new sorted array and leaves the original array untouched, while our insertion sort modifies the array. This
duplication of space on each function call in total
uses ![formula](<https://render.githubusercontent.com/render/math?math=O(n\log%2Bn)>) space. Can we do better?

Note that going from one level to the next we only need a total
of ![formula](<https://render.githubusercontent.com/render/math?math=O(n)>) extra space. This suggests that we should be
able to come up with a merge sort algorithm that only
uses ![formula](<https://render.githubusercontent.com/render/math?math=O(n)>) space while keeping the same time
complexity. This is left as an exercise to the reader. (Hint: allocate an array at the beginning of the same size as the
input array.)

# Day 27 bonus round: Heap sort

A write-up on [heap sort](heap-sort.md) using binary heaps.
