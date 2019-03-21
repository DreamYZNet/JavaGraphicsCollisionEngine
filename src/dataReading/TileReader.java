
package main;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TileReader extends DataReader {
	
	public TileReader() {
		
	}
	
	public Tile getTileAt(String path) {
		
		Tile tile = new Tile();
		if (path == null)
			return tile;
		openInputStream(path);
		
		if (this.inputStream.next().equals("id")) {
			tile.setId( inputStream.nextInt() );
		}
		if (this.inputStream.next().equals("solid")) {
			String field = inputStream.next();
			if (field.equals("true")) {
				tile.setSolid(true);
			}else if (field.equals("false")) {
				tile.setSolid(false);
			}
		}
		if (this.inputStream.next().equals("path")) {
			String imagePath = inputStream.next();
			tile.setImagePath( imagePath );
			
			//Add image to tile
			try {
				BufferedImage image = ImageIO.read(new File(imagePath));
				tile.setImage(image);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			
		}
		
		closeInputStream();
		return tile;
		
	}
	
}
