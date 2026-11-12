# fMRI Image Segmentation Tool

A Java Swing desktop application that demonstrates a simplified brain-image processing pipeline:
image loading → grayscale preprocessing → threshold segmentation → ROI extraction → activity estimation.

> Educational project only. This is not a clinical fMRI analysis or diagnostic tool.

## Requirements
- JDK 8 or later
- No external libraries are required (uses Java AWT, Swing, and ImageIO)

## Run
Open a terminal in this folder and compile:

```bash
javac *.java
```

Run the application:

```bash
java Main
```

Use **Upload Image** to select a JPG, JPEG, PNG, or BMP image.

## Source files
- `Main.java` — application entry point
- `ResultDisplay.java` — Swing GUI and processing workflow
- `ImageLoader.java` — loads images
- `Preprocessing.java` — grayscale conversion
- `Segmentation.java` — threshold segmentation
- `ROIExtractor.java` — central ROI extraction
- `FeatureExtractor.java` — activity percentage and label

## Project authors
- Ananya V Shahapur
- Faheemah M
