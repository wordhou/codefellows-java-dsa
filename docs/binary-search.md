# Challenge Summary

We are asked to implement a classic binary search algorithm on a sorted array of `int`s.

## Challenge Description

Given a sorted list of `int`s, and a `int` value to look for, determine whether or not that value exists in the array, and if so, return an array index that contains that value.

## Approach & Efficiency

The classic binary search algorithm runs in logarithmic time and constant space, because every iteration of the binary search halves the search space. (Side note: this comes at the cost of having a sorted list. Sorting a list takes at best `O(n log n)` time. If only one query needs to be made on an unsorted array, the sequential search wins out.) This can give considerable gains over sequential search on a sorted list.

## Solution

[Our whiteboard for the binary search](../assets/binary-search.png)

A link to my [completed code](../challenges/lib/src/main/java/challenges/BinarySearch.java).

## Collaborators

This solution was discussed and whiteboarded with collaborators James Mansour and Amelia Waggoner.
