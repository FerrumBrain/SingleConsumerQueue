package org.example

import kotlinx.atomicfu.*

class SingleConsumerQueue<T> {
    private class Node<T>(val value: T?, val next: AtomicRef<Node<T>?>)

    private val head = atomic(Node<T?>(null, atomic(null)))
    private val tail = atomic(head.value)

    fun enqueue(value: T) {
        val newNode = Node<T?>(value, atomic(null))
        while (true) {
            val currentTail = tail.value
            val next = currentTail.next.value
            if (next == null) {
                if (currentTail.next.compareAndSet(null, newNode)) {
                    tail.compareAndSet(currentTail, newNode)
                    return
                }
            } else {
                tail.compareAndSet(currentTail, next)
            }
        }
    }

    fun dequeue(): T? {
        while (true) {
            val currentHead = head.value
            val nextNode = currentHead.next.value ?: return null
            if (head.compareAndSet(currentHead, nextNode)) {
                return nextNode.value
            }
        }
    }

    fun peek(): T? {
        val nextNode = head.value.next.value
        return nextNode?.value
    }
}
