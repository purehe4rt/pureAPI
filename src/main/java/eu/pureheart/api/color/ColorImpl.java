package eu.pureheart.api.color;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorImpl implements BukkitColor {

    @Override
    public String hexToMinecraftColor(String text) {
        if (text == null) return "";

        Matcher gradientMatcher = Pattern.compile("\\{#([A-Fa-f0-9]{6})>#([A-Fa-f0-9]{6})}([^{}]*)").matcher(text);
        StringBuffer gradientConverted = new StringBuffer();

        while (gradientMatcher.find()) {
            String startHex = gradientMatcher.group(1);
            String endHex = gradientMatcher.group(2);
            String content = gradientMatcher.group(3);

            String gradientText = applyGradient(content, startHex, endHex);
            gradientMatcher.appendReplacement(gradientConverted, gradientText);
        }

        gradientMatcher.appendTail(gradientConverted);

        Matcher hexMatcher = Pattern.compile("#[a-fA-F0-9]{6}").matcher(gradientConverted.toString());
        StringBuffer hexConverted = new StringBuffer();

        while (hexMatcher.find()) {
            String hex = hexMatcher.group();
            String replacement = "§x" + hex.substring(1).replaceAll("(.)", "§$1");
            hexMatcher.appendReplacement(hexConverted, replacement);
        }

        hexMatcher.appendTail(hexConverted);

        return hexConverted.toString().replaceAll("&([0-9a-fA-Fk-oK-OrR])", "§$1");
    }

    private String applyGradient(String text, String startHex, String endHex) {
        int length = text.length();
        if (length == 0) return "";

        Color startColor = Color.decode("#" + startHex);
        Color endColor = Color.decode("#" + endHex);

        StringBuilder gradientText = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double ratio = (double) i / (length - 1);
            int red = (int) (startColor.getRed() + (endColor.getRed() - startColor.getRed()) * ratio);
            int green = (int) (startColor.getGreen() + (endColor.getGreen() - startColor.getGreen()) * ratio);
            int blue = (int) (startColor.getBlue() + (endColor.getBlue() - startColor.getBlue()) * ratio);

            String hexColor = String.format("#%02X%02X%02X", red, green, blue);
            String minecraftColor = "§x" + hexColor.substring(1).replaceAll("(.)", "§$1");

            gradientText.append(minecraftColor).append(text.charAt(i));
        }

        return gradientText.toString();
    }
}
