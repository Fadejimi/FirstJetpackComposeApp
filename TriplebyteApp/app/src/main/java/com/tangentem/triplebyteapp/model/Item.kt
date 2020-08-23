package com.tangentem.triplebyteapp.model

data class Item(val name: String, val sub_items: List<SubItem>)

data class SubItem(val name: String, val date: String, val price: String)