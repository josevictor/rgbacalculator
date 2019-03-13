package br.unifor.rgbacalculator

import android.text.Editable
import android.text.TextWatcher

class EditTextEvent(val onChange: (s: CharSequence?) -> Unit): TextWatcher {
    override fun afterTextChanged(s: Editable?) {}

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onChange(s)
    }
}