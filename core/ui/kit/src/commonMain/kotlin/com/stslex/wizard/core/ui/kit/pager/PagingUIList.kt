package com.stslex.wizard.core.ui.kit.pager

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateListOf

@Immutable
class PagingUIList<Item : PagingUiItem> private constructor(vararg elements: Item) {

    private val _items = mutableStateListOf<Item>(*elements)

    val size: Int get() = _items.size

    val sizeOrHold: Int get() = if (_items.isEmpty()) 4 else _items.size

    operator fun get(index: Int): Item? = _items.getOrNull(index)

    fun addItems(items: List<Item>) {
        _items.addAll(items)
    }

    fun isEmpty(): Boolean = _items.isEmpty()

    fun clear() {
        _items.clear()
    }

    fun removeItem(item: Item) {
        _items.remove(item)
    }

    fun removeItems(items: List<Item>) {
        _items.removeAll(items)
    }

    val key: ((Int) -> Any)?
        get() = if (isEmpty()) {
            null
        } else {
            { index ->
                checkNotNull(get(index)?.id)
            }
        }

    companion object {

        fun <Item : PagingUiItem> pagingUiListOf(
            vararg elements: Item
        ): PagingUIList<Item> = PagingUIList(*elements)
    }
}