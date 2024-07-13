package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.math.RandomXS128
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import ktx.app.clearScreen

//private val logger = logger<FirstScreen>()

class FirstScreen : KtxScreen {

    companion object {
        var GLOBAL_RANDOM = RandomXS128()
    }

    //private val camera = OrthographicCamera().apply { setToOrtho(false, 800f, 800f) }

    //private val vector = vec3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
    private lateinit var gasket: Gasket
    private val fps = FrameRate()

    var seed0: Long = 0
    var seed1: Long = 0

    private lateinit var preference: Preferences

    override fun show() {
        Gdx.input.inputProcessor = object : KtxInputAdapter {
            override fun keyUp(keycode: Int): Boolean {
                if (keycode == Keys.SPACE) {
                    retryGasket()
                }
                if (keycode == Keys.F3) {
                    fps.isRendered = !fps.isRendered
                }
                return true
            }

            override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
                newGasket()
                return true
            }
        }
        preference = Gdx.app.getPreferences("Gasker")

        seed0 = preference.getLong("seed0", 0L)
        seed1 = preference.getLong("seed1", 0L)
        if(seed0 != 0L || seed1 != 0L) {
            GLOBAL_RANDOM.setState(seed0, seed1)
        }
        else {
            seed0 = GLOBAL_RANDOM.getState(0)
            seed1 = GLOBAL_RANDOM.getState(1)
            preference.putLong("seed0", seed0)
            preference.putLong("seed1", seed1)
            preference.flush()
        }
        gasket = Gasket()
    }

    override fun render(delta: Float) {
        clearScreen(red = 0f, green = 0f, blue = 0f)
        fps.update()
//        vector.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
//        camera.unproject(vector)
//        Gdx.graphics.setTitle("DEBUG - X: ${vector.x} Y: ${vector.y}")

        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit()

        gasket.draw()
        fps.render()
    }

    private fun retryGasket() {
        GLOBAL_RANDOM = RandomXS128()
        GLOBAL_RANDOM.setState(seed0, seed1)
        gasket = Gasket()
    }

    private fun newGasket() {
        GLOBAL_RANDOM = RandomXS128()
        seed0 = GLOBAL_RANDOM.getState(0)
        seed1 = GLOBAL_RANDOM.getState(1)
        preference.putLong("seed0", seed0)
        preference.putLong("seed1", seed1)
        preference.flush()
        gasket = Gasket()
    }

    override fun resize(width: Int, height: Int) {
        fps.resize(width, height)
    }

    override fun dispose() {
        gasket?.dispose()
        fps.dispose()
    }
}