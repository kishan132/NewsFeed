package me.kishankumar.newsfeed.extensions

val String.toPositiveLong :Long
get() {
    val hashCode: Int = this.hashCode()
    // Convert to positive long by bitwise AND with 0x7FFFFFFF (MAX_INT)
    return hashCode.toLong() and 0x7FFFFFFF
}