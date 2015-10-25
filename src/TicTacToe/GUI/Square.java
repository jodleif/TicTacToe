package TicTacToe.GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * Created by Jo Øivind Gjernes on 24.10.2015.
 */
public class Square extends Pane
{
	int x, y;
	private ImageView imgV;
	private Rectangle rec;
	private boolean marked;

	public Square(double dimension, int x, int y)
	{
		setPrefHeight(dimension);
		setPrefWidth(dimension);
		this.x = x;
		this.y = y;
		rec = new Rectangle(200, 200);
		getChildren().add(rec);
		rec.setFill(Color.AQUA);
		rec.setOpacity(0d);
		/*setOnMouseClicked(e -> {
			debugIdent();
		});*/
	}


	private void addImageView(ImageView iv)
	{
		getChildren().add(iv);
	}

	private void removeImageView(ImageView iv)
	{
		getChildren().remove(iv);
	}

	public void markSquare()
	{
		if (marked) {
			marked = false;
			rec.setOpacity(0d);
		} else {
			rec.setOpacity(0.5d);
			marked = true;
		}
	}

	public void removeImage()
	{
		if(imgV!=null){
			getChildren().remove(imgV);
			imgV=null;
		}
	}

	public void setImage(Image img){
		if(img!=null&&imgV==null) {
			imgV = new ImageView();
			imgV.setImage(img);
			addImageView(imgV);
		}
	}

	public void debugIdent() {
		System.out.println("Hello i'm: [x,y]"+x+y);
	}
}
