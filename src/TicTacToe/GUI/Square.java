package TicTacToe.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class Square extends Pane
{
	private ImageView imgV;
	int x,y;

	public Square(double dimension, int x, int y)
	{
		setPrefHeight(dimension);
		setPrefWidth(dimension);
		this.x = x;
		this.y = y;
		/*setOnMouseClicked(e -> {
			debugIdent();
		});*/
	}


	public void addImageView(ImageView iv)
	{
		getChildren().add(iv);
	}

	public void setImage(Image img){
		if(img!=null) {
			imgV = new ImageView();
			imgV.setImage(img);
			addImageView(imgV);
		};
	}

	public void debugIdent() {
		System.out.println("Hello i'm: [x,y]"+x+y);
	}
}
