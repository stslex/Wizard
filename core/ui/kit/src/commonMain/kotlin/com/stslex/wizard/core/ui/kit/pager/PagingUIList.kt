package com.stslex.wizard.core.ui.kit.pager

import androidx.compose.runtime.Immutable

@Immutable
class PagingUIList<Item : Any> {

    private val _items = mutableListOf<Item>()

    val size: Int get() = _items.size

    operator fun get(index: Int): Item? = _items.getOrNull(index)

    fun addItems(items: List<Item>) {
        _items.addAll(items)
    }

    fun clear() {
        _items.clear()
    }

    fun removeItem(item: Item) {
        _items.remove(item)
    }

    fun removeItems(items: List<Item>) {
        _items.removeAll(items)
    }
}