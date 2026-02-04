package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private static Map<String, BufferedImage> textures = new HashMap<>();

    public static BufferedImage getTexture(String path) {
        // se a imagem não foi carregada antes, carrega agora
        if (!textures.containsKey(path)) {
            try {
                textures.put(path, ImageIO.read(ResourceManager.class.getResourceAsStream(path)));
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        // retorna a imagem já carregada na memória
        return  textures.get(path);
    }
}
