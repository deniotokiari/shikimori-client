package by.deniotokiari.shikimoriclient

import org.apache.commons.lang3.time.FastDateFormat
import java.text.Format
import java.util.*

interface IDateFormatter {

    fun getYearMonthDayFormatter(): Format

    private object Formatter {

        val yearMonthDayFormatter: Format by lazy { FastDateFormat.getInstance("yyyy-MM-dd", Locale.ENGLISH) }

    }

    companion object {

        fun newInstance(): IDateFormatter {
            return object : IDateFormatter {
                override fun getYearMonthDayFormatter(): Format {
                    return Formatter.yearMonthDayFormatter
                }
            }
        }

    }

}