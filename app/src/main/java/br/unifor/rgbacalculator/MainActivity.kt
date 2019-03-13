package br.unifor.rgbacalculator

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.view.Gravity
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var checkedHex:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_imageview_logo_unifor.imageAlpha = 0
        this.actionEditTextNotHex(main_edittext_intensity_red, main_seekBar_intensity_red)
        this.actionEditTextNotHex(main_edittext_intensity_green, main_seekBar_intensity_green)
        this.actionEditTextNotHex(main_edittext_intensity_blue, main_seekBar_intensity_blue)
        this.actionEditTextNotHex(main_edittext_intensity_alpha, main_seekBar_intensity_alpha)


        main_switch_activate_hex.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                var message:Toast = Toast.makeText(this, "Hexadecimal ativado !", Toast.LENGTH_LONG)
                message.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                message.show()
                this.actionEditTextHex(main_edittext_intensity_red, main_seekBar_intensity_red)
                this.actionEditTextHex(main_edittext_intensity_green, main_seekBar_intensity_green)
                this.actionEditTextHex(main_edittext_intensity_blue, main_seekBar_intensity_blue)
                this.actionEditTextHex(main_edittext_intensity_alpha, main_seekBar_intensity_alpha)
                this.checkedHex = true
            }
            else {
                var message:Toast = Toast.makeText(this, "Hexadecimal desativado !", Toast.LENGTH_LONG)
                message.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                message.show()
                this.actionEditTextNotHex(main_edittext_intensity_red, main_seekBar_intensity_red)
                this.actionEditTextNotHex(main_edittext_intensity_green, main_seekBar_intensity_green)
                this.actionEditTextNotHex(main_edittext_intensity_blue, main_seekBar_intensity_blue)
                this.actionEditTextNotHex(main_edittext_intensity_alpha, main_seekBar_intensity_alpha)
                this.checkedHex = false
            }
        }

        main_edittext_intensity_red.addTextChangedListener(setActionListener(main_seekBar_intensity_red))
        main_edittext_intensity_green.addTextChangedListener(setActionListener(main_seekBar_intensity_green))
        main_edittext_intensity_blue.addTextChangedListener(setActionListener(main_seekBar_intensity_blue))
        main_edittext_intensity_alpha.addTextChangedListener(setActionListener(main_seekBar_intensity_alpha))

        main_seekBar_intensity_red.setOnSeekBarChangeListener(SeekBarEvent { progress ->
            val valueRed:Int = progress
            val valueGreen:Int = main_seekBar_intensity_green.progress
            val valueBlue:Int = main_seekBar_intensity_blue.progress
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            this.setValue(main_edittext_intensity_red, main_seekBar_intensity_red, valueRed)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })


        main_seekBar_intensity_green.setOnSeekBarChangeListener(SeekBarEvent { progress ->
            val valueRed:Int = main_seekBar_intensity_red.progress
            val valueGreen:Int = progress
            val valueBlue:Int = main_seekBar_intensity_blue.progress
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            this.setValue(main_edittext_intensity_green, main_seekBar_intensity_green, valueGreen)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })

        main_seekBar_intensity_blue.setOnSeekBarChangeListener(SeekBarEvent {progress ->
            val valueRed:Int = main_seekBar_intensity_red.progress
            val valueGreen:Int = main_seekBar_intensity_green.progress
            val valueBlue:Int = progress
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            this.setValue(main_edittext_intensity_blue, main_seekBar_intensity_blue, valueBlue)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })

        main_seekBar_intensity_alpha.setOnSeekBarChangeListener(SeekBarEvent {progress ->
            val valueRed = main_seekBar_intensity_red.progress
            val valueGreen = main_seekBar_intensity_green.progress
            val valueBlue = main_seekBar_intensity_blue.progress
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            this.setValue(main_edittext_intensity_alpha, main_seekBar_intensity_alpha, progress)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
            main_imageview_logo_unifor.imageAlpha = progress
        })
    }

    private fun actionEditTextHex(editText: EditText, seekBar: SeekBar) {
        editText.hint = "FFF"
        editText.keyListener = DigitsKeyListener.getInstance("0123456789ABCDEFabcdef")
        editText.inputType = InputType.TYPE_CLASS_TEXT
        editText.filters = arrayOf(FilterHex())
        this.actionEditTextConvertToHex(editText, seekBar)
    }

    private fun actionEditTextNotHex(editText: EditText, seekBar: SeekBar) {
        editText.hint = "255"
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.keyListener = DigitsKeyListener.getInstance("0123456789")
        editText.filters = arrayOf(FilterNumber(0, 255))
        this.actionEditTextConvertToDecimal(editText, seekBar)
    }

    private fun actionEditTextConvertToHex(editText: EditText, seekBar: SeekBar) {
        if(editText.text.length !== 0) {
            var text: Int = editText.text.toString().toInt()
            var actionListEditText: TextWatcher = setActionListener(seekBar)
            editText.removeTextChangedListener(setActionListener(seekBar))
            editText.setText(Integer.toHexString(text).toString(), TextView.BufferType.EDITABLE)
            editText.setSelection(editText.length())
            editText.addTextChangedListener(actionListEditText)
        }
    }

    private fun actionEditTextConvertToDecimal(editText: EditText, seekBar: SeekBar) {
        if(editText.text.length !== 0) {
            var text: String = editText.text.toString()
            var actionListEditText: TextWatcher = setActionListener(seekBar)
            editText.removeTextChangedListener(setActionListener(seekBar))
            editText.setText(Integer.parseInt(text, 16).toString(), TextView.BufferType.EDITABLE)
            editText.setSelection(editText.length())
            editText.addTextChangedListener(actionListEditText)
        }
    }

    private fun setActionListener(seekBar:SeekBar): TextWatcher {
        return (EditTextEvent { s ->
            s?.let {
                var value: Int = 0
                if(it.length > 0) {

                    if (main_switch_activate_hex.isChecked) {
                        value = Integer.parseInt(s.toString(), 16)
                    } else {
                        value = s.toString().toInt()
                    }
                    seekBar.setProgress(value)
                }
            }
            ""
        })
    }

    private fun setValue(editText: EditText, seekBar: SeekBar, value:Int) {
        var actionListEditText: TextWatcher = setActionListener(seekBar)
        editText.removeTextChangedListener(setActionListener(seekBar))
        if(this.checkedHex) editText.setText(Integer.toHexString(value).toString())
        else editText.setText(value.toString(), TextView.BufferType.EDITABLE)
        editText.setSelection(editText.length())
        editText.addTextChangedListener(actionListEditText)
    }
}
