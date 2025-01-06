package com.fcascan.newsreaderapp.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.fcascan.newsreaderapp.R

object MyFonts {
    val TimesNewRoman = FontFamily(
        Font(R.font.times_new_roman, FontWeight.Normal),
        Font(R.font.times_new_roman_bold, FontWeight.Bold),
        Font(R.font.times_new_roman_italic, FontWeight.Normal, FontStyle.Italic),
        Font(R.font.times_new_roman_bold_italic, FontWeight.Bold, FontStyle.Italic)
    )
}