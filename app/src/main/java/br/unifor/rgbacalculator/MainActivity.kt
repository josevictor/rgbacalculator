package br.unifor.rgbacalculator

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.Gravity
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var checkedHex:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_imageview_logo_unifor.imageAlpha = 0
        this.actionEditTextNotHex(main_edittext_intensity_red)
        this.actionEditTextNotHex(main_edittext_intensity_green)
        this.actionEditTextNotHex(main_edittext_intensity_blue)
        this.actionEditTextNotHex(main_edittext_intensity_alpha)


        main_switch_activate_hex.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                var message:Toast = Toast.makeText(this, "Hexadecimal ativado !", Toast.LENGTH_LONG)
                message.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                message.show()
                this.actionEditTextHex(main_edittext_intensity_red)
                this.actionEditTextHex(main_edittext_intensity_green)
                this.actionEditTextHex(main_edittext_intensity_blue)
                this.actionEditTextHex(main_edittext_intensity_alpha)
                this.checkedHex = true
            }
            else {
                var message:Toast = Toast.makeText(this, "Hexadecimal desativado !", Toast.LENGTH_LONG)
                message.setGravity(Gravity.TOP or Gravity.CENTER_HORIZONTAL, 0, 0)
                message.show()
                this.actionEditTextNotHex(main_edittext_intensity_red)
                this.actionEditTextNotHex(main_edittext_intensity_green)
                this.actionEditTextNotHex(main_edittext_intensity_blue)
                this.actionEditTextNotHex(main_edittext_intensity_alpha)
                this.checkedHex = false
            }
        }

        main_edittext_intensity_red.addTextChangedListener(EditTextEvent {s ->
            s?.let {
                if(it.length > 0) {
                    //alterar seekbar
                }
            }
        })

        main_seekBar_intensity_red.setOnSeekBarChangeListener(SeekBarEvent { progress ->
            val valueRed = progress * 255 / 100
            val valueGreen = main_seekBar_intensity_green.progress * 255 / 100
            val valueBlue = main_seekBar_intensity_blue.progress * 255 / 100
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })


        main_seekBar_intensity_green.setOnSeekBarChangeListener(SeekBarEvent { progress ->
            val valueRed = main_seekBar_intensity_red.progress * 255 / 100
            val valueGreen = progress * 255 / 100
            val valueBlue = main_seekBar_intensity_blue.progress * 255 / 100
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })

        main_seekBar_intensity_blue.setOnSeekBarChangeListener(SeekBarEvent {progress ->
            val valueRed = main_seekBar_intensity_red.progress * 255 / 100
            val valueGreen = main_seekBar_intensity_green.progress * 255 / 100
            val valueBlue = progress * 255 / 100
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })

        main_seekBar_intensity_alpha.setOnSeekBarChangeListener(SeekBarEvent {progress ->
            val valueRed = main_seekBar_intensity_red.progress * 255 / 100
            val valueGreen = main_seekBar_intensity_green.progress * 255 / 100
            val valueBlue = main_seekBar_intensity_blue.progress * 255 / 100
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)

            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
            main_imageview_logo_unifor.imageAlpha = progress
        })
    }

    private fun actionEditTextHex(editText: EditText) {
        editText.hint = "FFF"
        editText.keyListener = DigitsKeyListener.getInstance("0123456789ABCDEFabcdef")
        editText.inputType = InputType.TYPE_CLASS_TEXT
        editText.filters = arrayOf(FilterHex())
    }

    private fun actionEditTextNotHex(editText: EditText) {
        editText.hint = "255"
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.keyListener = DigitsKeyListener.getInstance("0123456789")
        editText.filters = arrayOf(FilterNumber(0, 255))
    }

    private fun actionEditTextConvertToHex(editText: EditText) {
        var text:Int = editText.text.toString().toInt()
        editText.setText(Integer.toHexString(text).toString())
    }

    private fun actionEditTextConvertToDecimal(editText: EditText) {
        var text:String = editText.text.toString()
        editText.setText(Integer.parseInt(text, 16).toString())
    }
}
