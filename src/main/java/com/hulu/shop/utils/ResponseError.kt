package com.hulu.shop.utils

class ResponseError(val code: Int, val msg: String) : Exception() {

}