package com.altech.reift.core.utils

import com.altech.reift.core.utils.Utils.toWordCase
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun toWordCase() {
        val expected = "Gaming Laptop"
        val input = "GAMING_LAPTOP"

        val expected2 = "Handphone"
        val input2 = "HANDPHONE"
        assertEquals(expected, input.toWordCase())
        assertEquals(expected2, input2.toWordCase())
    }
}