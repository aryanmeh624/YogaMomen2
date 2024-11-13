# YogaMomen2

**Description:**  
YogaMomen2 is the second iteration of an application developed for the 2024 MEGATHON Qualcomm problem, focusing on yoga pose detection and classification.

## Features
1. **Pose Detection:** Utilizes the open-source Movenet Thunder model for detecting yoga poses.
2. **Pose Classification:** A custom TFLite model (`pose_classifier.tflite`), created using a Python script on Google Colab, for classifying poses.

## Progress
1. Created a Python script to convert labeled data of three Surya Namaskar poses to a TFLite model, which can be loaded into the app.
2. Leveraged templates from the first trial [YogaMomen1](https://github.com/aryanmeh624/YogaMomen1), now with two models: one for detecting poses and another for checking the similarity between detected poses.

## Current Issues
- The setup process was time-consuming and encountered build failures due to outdated libraries.

## Requirements
- **Pose Detection Model:** Movenet Thunder model.
- **Pose Classification Model:** `pose_classifier.tflite`, a self-made model created from scratch using a Python script on Google Colab.
