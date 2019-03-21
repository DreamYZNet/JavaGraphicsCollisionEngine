
package main;

public class StageReader extends DataReader {
	
	public Stage getStageAt (String path) { //improvable
		Stage stage = new Stage();
		openInputStream(path);
		
		if (inputStream.next().equals("width")) {
			stage.setWidth( inputStream.nextInt() );
		}
		if (inputStream.next().equals("height")) {
			stage.setHeight( inputStream.nextInt() );
		}
		if (inputStream.next().equals("tileWidth")) {
			stage.setTileWidth( inputStream.nextInt() );
		}
		if (inputStream.next().equals("tileHeight")) {
			stage.setTileHeight( inputStream.nextInt() );
		}
		if (inputStream.next().equals("layout")) {
			int[][] layout = new int[stage.getHeight()][stage.getWidth()];
			for (int y = 0; y < layout.length; y++) { //set latout n shit
				for (int x = 0; x < layout[0].length; x++) {
					layout[y][x] = inputStream.nextInt();
				}
			}
			stage.setLayout(layout);
		}
		System.out.println(stage.toString());
		
		closeInputStream();
		return stage;
	}
	
}
