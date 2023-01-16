import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageRedactor {
    public static void main(String[] args) {

        File folder = new File("src/main/resources/sourse images");
        for (File image : folder.listFiles()) {
            try {
                BufferedImage sourse = ImageIO.read(image);
                BufferedImage result = new BufferedImage(sourse.getWidth(), sourse.getHeight(), sourse.getType());

                for (int i = 0; i < sourse.getWidth(); i++) {
                    for (int j = 0; j < sourse.getHeight(); j++) {
                        Color color = new Color(sourse.getRGB(i, j));
                        int red = color.getRed();
                        int green = color.getGreen();
                        int blue = color.getBlue();
                        int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);
                        Color newColor = new Color(grey, grey, grey);
                        result.setRGB(i, j, newColor.getRGB());
                    }
                }
                ImageIO.write(result, "jpg",
                        new File("src/main/resources/result images/" + image.getName()));
                System.out.println("Готово! Теперь ваша картинка серая ");
            } catch (IOException e) {
                System.out.println("Не удалось изменить вашу картинку");
            }
        }
    }
}
