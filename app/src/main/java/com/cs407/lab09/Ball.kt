package com.cs407.lab09

import android.util.Log

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = (backgroundWidth / 2) - (ballSize / 2)
    var posY = (backgroundHeight / 2) - (ballSize / 2)
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val xVelStart = velocityX
        val yVelStart = velocityY

        //Updating velocityX first
        velocityX += (0.5.toFloat() * (accX + xAcc) * dT)
        //Update velocityY now
        velocityY += (0.5.toFloat() * (accY + yAcc) * dT)

        //Now compute distance travelled, starting with X
        posX += xVelStart * dT + (1f / 6f) * (dT * dT) * (3 * accX + xAcc)
        //Now compute for Y
        posY += yVelStart * dT + (1f / 6f) * (dT * dT) * (3 * accY + yAcc)

        //Now I should update acceleration so that the steps are correct for next iteration
        accX = xAcc
        accY = yAcc
        checkBoundaries()
        Log.d("posX", posX.toString())
        Log.d("posY", posY.toString())
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        val leftBoundary = 0f
        val topBoundary = 0f
        val rightBoundary = backgroundWidth
        val bottomBoundary = backgroundHeight

        if(posX < leftBoundary){
            posX = 0f //leftBoundary + (ballSize / 2f)
            velocityX = 0f
            accX = 0f
        } else if(posX + ballSize> rightBoundary) {
            posX = rightBoundary - ballSize
            velocityX = 0f
            accX = 0f
        }

        if(posY < topBoundary){
            posY = 0f//topBoundary + (ballSize / 2f)
            velocityY = 0f
            accY = 0f
        } else if((posY + ballSize) > bottomBoundary){
            posY = bottomBoundary - (ballSize)
            velocityY = 0f
            accY = 0f
        }

    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = (backgroundWidth / 2f) - (ballSize / 2f)
        posY = (backgroundHeight / 2f) - (ballSize / 2f)
        Log.d("reset", posX.toString())
        Log.d("reset", posY.toString())
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}