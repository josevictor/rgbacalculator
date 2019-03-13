package br.unifor.rgbacalculator

import android.text.InputFilter
import android.text.Spanned

class FilterNumber : InputFilter {

    private var min: Int
    private var max: Int

     constructor(min:Int, max:Int) {
        this.min = min
        this.max = max
     }

    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        try {
            val input = (dest.toString() + source.toString()).toInt()
            if (isRange(this.min, this.max, input))
                return null
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun isRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c >= a && c <= b else c >= b && c <= a
    }
}