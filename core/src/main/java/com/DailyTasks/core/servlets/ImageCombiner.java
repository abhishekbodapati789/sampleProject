package com.DailyTasks.core.servlets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.regex.*;

public class ImageCombiner {
    private static final String folderPath = "D:/images";
    private static final String outputPath = "C:/Users/pc/Downloads/NewCombinedImage.png";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Describe how to combine the images: ");
        String prompt = scanner.nextLine();
        
        combineImages(prompt);
        scanner.close();
    }

    public static void combineImages(String prompt) {
        File folder = new File(folderPath);
        File[] images = folder.listFiles((dir, name) -> name.matches(".*\\.(png|jpg|jpeg|webp)$"));
        if (images == null || images.length == 0) {
            System.out.println("No images found in the folder.");
            return;
        }
        
        Map<String, BufferedImage> imageMap = new HashMap<>();
        for (File imgFile : images) {
            try {
                imageMap.put(imgFile.getName(), ImageIO.read(imgFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Map<String, String> parsedPositions = parsePrompt(prompt, imageMap.keySet());
        BufferedImage background = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = background.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, background.getWidth(), background.getHeight());

        for (Map.Entry<String, String> entry : parsedPositions.entrySet()) {
            BufferedImage img = imageMap.get(entry.getKey());
            if (img != null) {
                Point position = getPosition(entry.getValue(), background, img);
                g.drawImage(img, position.x, position.y, null);
            }
        }
        g.dispose();

        try {
            File output = new File(outputPath);
            ImageIO.write(background, "PNG", output);
            System.out.println("Image saved at: " + output.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, String> parsePrompt(String prompt, Set<String> imageNames) {
        Map<String, String> positions = new HashMap<>();
        String[] positionKeywords = {"background", "top", "bottom", "left", "right", "center"};
        
        for (String img : imageNames) {
            for (String pos : positionKeywords) {
                if (prompt.toLowerCase().contains(img.toLowerCase()) && prompt.toLowerCase().contains(pos)) {
                    positions.put(img, pos);
                }
            }
        }
        return positions;
    }

    private static Point getPosition(String position, BufferedImage background, BufferedImage img) {
        int x = (background.getWidth() - img.getWidth()) / 2;
        int y = (background.getHeight() - img.getHeight()) / 2;
        
        switch (position) {
            case "top": y = 0; break;
            case "bottom": y = background.getHeight() - img.getHeight(); break;
            case "left": x = 0; break;
            case "right": x = background.getWidth() - img.getWidth(); break;
            case "background": x = 0; y = 0; break;
        }
        return new Point(x, y);
    }
}
