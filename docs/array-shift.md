# Challenge Summary

Code challenge two asks us to insert an element into the middle of an array.

## Challenge Description

We're writing a method that takes an array and an element, and returns a new array with the element added after the middle index. If the input array has an even number of elements, the new element is inserted right in the middle of the array.

## Approach & Efficiency

We first allocated a new array to hold the result. The length of this new array is the length of the input array plus one.

We then iterated over the original array, copying elements over one by one into the result array. If the index we're copying is less than the index of the inserted element, we kept the index the same, and if the index we're copying is greater than or equal to the index of the new element, we shifted it to the right by one in the new array.

The efficiency of this approach should be linear in time and space, since it loops through the input array exactly once, performing one copy operation for each element.

## Solution

[Our group's whiteboard for the problem](../assets/array-shift.png)

## Collaborators

This problem was whiteboarded together with Andy Agulue, James Mansour, and Amelia Waggoner.
