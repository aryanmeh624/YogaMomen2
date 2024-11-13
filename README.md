# YogaMomen2
THIS IS FOR THE 2024 MEGATHON Qualcomm problem:

This is the second iteration of trying to make the pose detection work along with the yoga poses.
    1. Made a python script to convert some labelled data of 3 poses of surya namaskar to a tflite model which could be loaded in the app.
    2. Used the templates from the first trial -> https://github.com/aryanmeh624/YogaMomen1. This time with 2 models one for detecting poses and other for checking the siilarityu between detected pose.

#Current Problems
The setup took a lot of the time and kept failing build because of some old outdated libraries used. 

#requirements
Uses the open source model: Movenet Thunder model in order to immplement the pose detection
The other tflite mdoel in teh assets is the pose_classifier.tft which was SELF MADE from sratch using python script on google collab.
