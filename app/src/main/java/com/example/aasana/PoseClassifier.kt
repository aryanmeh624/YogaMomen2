// app/src/main/java/com/example/yogaposeclassifier/PoseClassifier.kt
package com.example.aasana

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class PoseClassifier(context: Context) {

    private var interpreter: Interpreter

    init {
        interpreter = Interpreter(loadModelFile(context, "pose_classifier.tflite"))
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
     * Classify the pose based on the keypoints.
     * @param keypoints A float array containing the keypoints data.
     * @return A Pair containing the asana name and performance score.
     */
    fun classifyPose(keypoints: FloatArray): Pair<String, Float> {
        // Assuming the model takes the keypoints as input and outputs probabilities
        val input = arrayOf(keypoints)
        val output = Array(1) { FloatArray(3) } // 3 asanas

        interpreter.run(input, output)

        // Find the asana with the highest probability
        val probabilities = output[0]
        val maxIndex = probabilities.indices.maxByOrNull { probabilities[it] } ?: -1
        val asana = when (maxIndex) {
            0 -> "Asana 1"
            1 -> "Asana 2"
            2 -> "Asana 3"
            else -> "Unknown"
        }

        val confidence = probabilities[maxIndex]

        // For performance, you might need another model or logic
        // Here, we'll just return the confidence as a placeholder
        return Pair(asana, confidence)
    }

    fun close() {
        interpreter.close()
    }
}
