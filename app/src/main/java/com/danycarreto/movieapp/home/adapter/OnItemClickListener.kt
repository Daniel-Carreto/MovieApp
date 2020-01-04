package com.danycarreto.movieapp.home.adapter

interface OnItemClickListener<T> {

    fun onItemClick(item: T, position:Int)
}