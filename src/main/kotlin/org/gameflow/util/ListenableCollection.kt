package org.gameflow.util

import java.util.Set
import org.gameflow.Component
import java.util.LinkedHashSet
import java.util.HashSet
import java.util.List
import java.util.ArrayList
import java.awt.image.renderable.RenderContext
import java.util.Collection
import java.util.Collections

/**
 * A collection that allows listening to addition and removal of elements,
 * as well as adding or removing elements while iterating through the collection.
 * The updateCollection method should be called regularly to make sure additions and removals are processed.
 * Only allows one instance of an element to be added.
 */
public class ListenableCollection<T> {

    private val elements : Set<T> = LinkedHashSet<T>(5)
    private val unmodifiableElements : Collection<T> = Collections.unmodifiableCollection(elements) !!

    private val elementsToAdd : Set<T> = HashSet<T>(1)
    private val elementsToRemove : Set<T> = HashSet<T>(1)
    private val addedElements: List<T> = ArrayList<T>(1)
    private val removedElements: List<T> = ArrayList<T>(1)

    private val additionListeners: List<(T) -> Unit> = ArrayList<(T) -> Unit>(1)
    private val removalListeners: List<(T) -> Unit> = ArrayList<(T) -> Unit>(1)

    public final fun elements() : Collection<T> {
        return unmodifiableElements
    }

    public final fun add(element: T) {
        elementsToAdd.add(element)
        elementsToRemove.remove(element)
    }

    public final fun remove(element: T) {
        elementsToRemove.add(element)
        elementsToAdd.remove(element)
    }

    public final fun removeAll() {
        elementsToRemove.addAll(elements)
        elementsToAdd.clear()
    }

    public final fun whenAdded(listener: (T) -> Unit) {
        additionListeners.add(listener)
    }
    public final fun whenRemoved(listener: (T) -> Unit) {
        removalListeners.add(listener)
    }

    public final fun removeAdditionListener(listener: (T) -> Unit) {
        additionListeners.remove(listener)
    }

    public final fun removeRemovalListener(listener: (T) -> Unit) {
        removalListeners.remove(listener)
    }


    /** Adds and removes elements scheduled for addition or removal, and calls listeners afterwards. */
    public final fun updateCollection() {
        // Update elements
        for (element in elementsToAdd) {
            if (!elements.contains(element)) {
                elements.add(element)
                addedElements.add(element)
            }
        }
        for (element in elementsToRemove) {
            if (elements.contains(element)) {
                elements.remove(element)
                removedElements.add(element)
            }
        }
        elementsToAdd.clear()
        elementsToRemove.clear()

        // Notify listeners
        // Doing this in a separate step allows the listeners to add or remove elements without problems.
        for (element in addedElements)   { for (listener in additionListeners) {listener(element)} }
        for (element in removedElements) { for (listener in removalListeners)  {listener(element)} }
        addedElements.clear()
        removedElements.clear()
    }

}