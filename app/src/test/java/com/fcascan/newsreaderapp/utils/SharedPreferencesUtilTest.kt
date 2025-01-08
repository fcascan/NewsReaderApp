package com.fcascan.newsreaderapp.utils

import android.content.Context
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SharedPreferencesUtilTest {

    private lateinit var context: Context
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    @Before
    fun setUp() {
        context = mockk()
        sharedPreferences = mockk()
        editor = mockk()
        every { context.getSharedPreferences(any(), any()) } returns sharedPreferences
        every { sharedPreferences.edit() } returns editor
        sharedPreferencesUtil = SharedPreferencesUtil(context)
    }

    @Test
    fun `isDarkThemeEnabled should return false when not set`() {
        every { sharedPreferences.getBoolean("is_dark_theme_enabled", false) } returns false

        val result = sharedPreferencesUtil.isDarkThemeEnabled

        assertEquals(false, result)
    }

    @Test
    fun `isDarkThemeEnabled should return true when set to true`() {
        every { sharedPreferences.getBoolean("is_dark_theme_enabled", false) } returns true

        val result = sharedPreferencesUtil.isDarkThemeEnabled

        assertEquals(true, result)
    }

    @Test
    fun `setIsDarkThemeEnabled should save true`() {
        every { editor.putBoolean("is_dark_theme_enabled", true) } returns editor
        every { editor.apply() } returns Unit

        sharedPreferencesUtil.isDarkThemeEnabled = true

        verify { editor.putBoolean("is_dark_theme_enabled", true) }
        verify { editor.apply() }
    }

    @Test
    fun `setIsDarkThemeEnabled should save false`() {
        every { editor.putBoolean("is_dark_theme_enabled", false) } returns editor
        every { editor.apply() } returns Unit

        sharedPreferencesUtil.isDarkThemeEnabled = false

        verify { editor.putBoolean("is_dark_theme_enabled", false) }
        verify { editor.apply() }
    }
}