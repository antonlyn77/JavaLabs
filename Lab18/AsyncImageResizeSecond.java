import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class AsyncImageResizeSecond implements Runnable {
    private File[] files;
    private String dstFolder;
    private long start;

    public AsyncImageResizeSecond(File[] file, String dstFolder, long start) {
        this.files = file;
        this.dstFolder = dstFolder;
        this.start = start;
    }

    @Override
    public void run() {
        try {
            for (File file : files) {
                BufferedImage image = ImageIO.read(file);

                int newWidth = image.getWidth() / 2;
                int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
                BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                int widthStep = image.getWidth() / newWidth;
                int heightStep = image.getHeight() / newHeight;

                for (int x = 0; x < newWidth; x++) {
                    for (int y = 0; y < newHeight; y++) {
                        int rgb = image.getRGB(x * widthStep, y * heightStep);
                        newImage.setRGB(x, y, rgb);
                    }
                }

                File newFile = new File(dstFolder + "/" + file.getName());
                ImageIO.write(newImage, "jpg", newFile);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}