package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int): Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return when {
            year !=  other.year -> year - other.year
            month != other.month -> month - other.month
            else -> dayOfMonth - other.dayOfMonth
        }
    }
}
class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun TimeInterval.times(num: Int) = RepeatedTimeInterval(this, num)
operator fun MyDate.plus(timeInterval: TimeInterval) = addTimeIntervals(timeInterval,1)
operator fun MyDate.plus(timeIntervals: RepeatedTimeInterval) = addTimeIntervals(timeIntervals.ti, timeIntervals.n)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate): ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = DateIterator(this)
}

class DateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var curr = dateRange.start

    override fun hasNext(): Boolean {
        return curr <= dateRange.endInclusive
    }

    override fun next(): MyDate {
        var result: MyDate = curr;
        curr = curr.nextDay();
        return result;
    }

}
