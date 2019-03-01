package br.unifor.rgbacalculator


import android.widget.SeekBar
import android.graphics.Color


class SeekBarEvent : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val lower = Color.argb(0xFF, progress, progress, progress)
        //val sharp = Bitmap.createBitmap(back) // create a copy
        //ManipBitmap.sharpen(sharp, lower)
        //image.setImageBitmap(sharp)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}