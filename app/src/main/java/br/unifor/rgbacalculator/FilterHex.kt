package br.unifor.rgbacalculator

import android.text.InputFilter
import android.text.Spanned

class FilterHex : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        source?.let {
            return if (it?.length > 2){
                source.dropLast(1)
            } else {
                var regexValor = Regex("[^0-9a-fA-F]")
                var l:String = regexValor.replace(it,"")
                l
            }
        }
        return null
    }
}
