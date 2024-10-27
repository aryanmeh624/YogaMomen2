// app/src/main/java/com/example/yogaposeclassifier/MoveNet.kt
package com.example.yogaposeclassifier

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class MoveNet(context: Context) {

    private var interpreter: Interpreter

    init {
        interpreter = Interpreter(loadModelFile(context, "movenet_thunder.tflite"))
    }

    @Throws(IOException::class)
    private fun loadModelFile(context: Context, modelName: String): MappedByteBuffer {
        val fileDescriptor = context.assets.openFd(modelName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declaredLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    /**
     * Detect pose keypoints from the input bitmap.
     * @param bitmap The input image bitmap.
     * @return A float array containing keypoints.
     */
    fun detectPose(bitmap: Bitmap): FloatArray {
        // Preprocess the image to match the model's input requirements
        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 256, 256, true)
        val tensorImage = TensorImage.fromBitmap(resizedBitmap)

        // Prepare input and output tensors
        val input = arrayOf(Array(256) { Array(256) { FloatArray(3) } })
        tensorImage.buffer.rewind()
        tensorImage.buffer.asFloatBuffer().get(input[0][0][0])

        // Output tensor shape for MoveNet Thunder is [1, 1, 17, 3]
        val output = Array(1) { Array(1) { Array(17) { FloatArray(3) } } }

        interpreter.run(input, output)

        // Extract keypoints
        val keypoints = FloatArray(51) // 17 keypoints * 3 (y, x, score)
        for (i in 0 until 17) {
            keypoints[i * 3] = output[0][0][i][0] // y
            keypoints[i * 3 + 1] = output[0][0][i][1] // x
            keypoints[i * 3 + 2] = output[0][0][i][2] // score
        }

        return keypoints
    }

    fun close() {
        interpreter.close()
    }
}
