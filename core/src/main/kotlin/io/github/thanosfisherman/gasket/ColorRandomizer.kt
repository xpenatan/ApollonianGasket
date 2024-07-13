package io.github.thanosfisherman.gasket

import com.badlogic.gdx.graphics.Color

private val colorArray =
    arrayOf(
        Color.BLUE,
        Color.CYAN,
        Color.MAGENTA,
        Color.VIOLET,
        Color.PURPLE,
        Color.PINK,
        Color.SALMON,
        Color.RED,
        Color.SCARLET,
        Color.CORAL,
        Color.FIREBRICK,
        Color.MAROON,
        Color.SKY,
        Color.TEAL,
        Color.NAVY,
        Color.SLATE,
        Color.ROYAL,
        Color.FOREST,
        Color.LIME,
        Color.CHARTREUSE,
        Color.GREEN,
        Color.OLIVE,
        Color.GRAY,
        Color.LIGHT_GRAY,
        Color.WHITE,
        Color.BROWN,
        Color.TAN,
        Color.YELLOW,
        Color.GOLD,
        Color.GOLDENROD,
        Color.ORANGE,
    )

class ColorRandomizer {

    private val isMultiColor = FirstScreen.GLOBAL_RANDOM.nextInt(2) == 0
    private val fixedColor = colorArray[FirstScreen.GLOBAL_RANDOM.nextInt(colorArray.size)]

    fun randomColor(): Color {
        if (isMultiColor)
            return colorArray[FirstScreen.GLOBAL_RANDOM.nextInt(colorArray.size)]
        return fixedColor
    }
}
