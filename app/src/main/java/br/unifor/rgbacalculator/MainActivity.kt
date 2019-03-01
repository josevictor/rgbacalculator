package br.unifor.rgbacalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mEditTextIntensityRed: EditText
    private lateinit var mEditTextIntensityGreen: EditText
    private lateinit var mEditTextIntensityBlue: EditText
    private lateinit var mEditTextIntensityAlpha: EditText
    private lateinit var mSeekBarIntensityRed: SeekBar
    private lateinit var mSeekBarIntensityGreen: SeekBar
    private lateinit var mSeekBarIntensityBlue: SeekBar
    private lateinit var mSeekBarIntensityAlpha: SeekBar
    private lateinit var mSwitchActivateHex: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.mEditTextIntensityRed = findViewById(R.id.main_edittext_intensity_red)
        this.mEditTextIntensityRed.inputType = InputType.TYPE_CLASS_NUMBER

        this.mEditTextIntensityGreen = findViewById(R.id.main_edittext_intensity_green)
        this.mEditTextIntensityBlue = findViewById(R.id.main_edittext_intensity_blue)
        this.mEditTextIntensityAlpha = findViewById(R.id.main_edittext_intensity_alpha)
        this.mSeekBarIntensityRed = findViewById(R.id.main_seekBar_intensity_red)
        this.mSeekBarIntensityGreen = findViewById(R.id.main_seekBar_intensity_green)
        this.mSeekBarIntensityBlue = findViewById(R.id.main_seekBar_intensity_blue)
        this.mSeekBarIntensityAlpha = findViewById(R.id.main_seekBar_intensity_alpha)
        this.mSwitchActivateHex = findViewById(R.id.main_switch_activate_hex)

        this.mSwitchActivateHex.setOnCheckedChangeListener{ buttonView, isChecked ->
            if (isChecked) {
                Toast.makeText(this, "Hexadecimal ativado !", Toast.LENGTH_LONG).show()
                this.mEditTextIntensityRed.hint = "FFF"
                this.mEditTextIntensityRed.keyListener = DigitsKeyListener.getInstance("ABCDEF")
                this.mEditTextIntensityRed.inputType = InputType.TYPE_CLASS_TEXT
            }
            else {
                Toast.makeText(this, "Hexadecimal desativado !", Toast.LENGTH_LONG).show()
                this.mEditTextIntensityRed.hint = "255"
                this.mEditTextIntensityRed.keyListener = DigitsKeyListener.getInstance("0123456789")
                this.mEditTextIntensityRed.inputType = InputType.TYPE_CLASS_NUMBER
            }

        }
        
        this.mSeekBarIntensityRed.setOnSeekBarChangeListener(SeekBarEvent())

        //this.window.decorView.setBackgroundColor(Color.parseColor("#2196F3"))

    }
}
