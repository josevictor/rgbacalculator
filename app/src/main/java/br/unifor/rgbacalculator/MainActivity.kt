package br.unifor.rgbacalculator

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        main_switch_activate_hex.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Hexadecimal ativado !", Toast.LENGTH_LONG).show()
                main_edittext_intensity_red.hint = "FFF"
                main_edittext_intensity_red.keyListener = DigitsKeyListener.getInstance("ABCDEF")
                main_edittext_intensity_red.inputType = InputType.TYPE_CLASS_TEXT
            }
            else {
                Toast.makeText(this, "Hexadecimal desativado !", Toast.LENGTH_LONG).show()
                main_edittext_intensity_red.hint = "255"
                main_edittext_intensity_red.keyListener = DigitsKeyListener.getInstance("0123456789")
                main_edittext_intensity_red.inputType = InputType.TYPE_CLASS_NUMBER
            }

        }


        main_seekBar_intensity_red.setOnSeekBarChangeListener(SeekBarEvent { progress ->
            //val valueAlpha = main_seekBar_intensity_alpha.progress * 255 / 100
            val valueRed = progress * 255 / 100
            val valueGreen = main_seekBar_intensity_green.progress * 255 / 100
            val valueBlue = main_seekBar_intensity_blue.progress * 255 / 100
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })


        main_seekBar_intensity_green.setOnSeekBarChangeListener(SeekBarEvent { progress ->
            //val valueAlpha = main_seekBar_intensity_alpha.progress * 255 / 100
            val valueRed = main_seekBar_intensity_red.progress * 255 / 100
            val valueGreen = progress * 255 / 100
            val valueBlue = main_seekBar_intensity_blue.progress * 255 / 100
            val colorApplyImage = Color.rgb(valueRed, valueGreen, valueBlue)
            main_imageview_logo_unifor.setColorFilter(colorApplyImage)
        })

        main_seekBar_intensity_blue.setOnSeekBarChangeListener(SeekBarEvent {progress ->
            //val valueAlpha = main_seekBar_intensity_alpha.progress * 255 / 100
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

        //this.window.decorView.setBackgroundColor(Color.parseColor("#2196F3"))

    }
}
