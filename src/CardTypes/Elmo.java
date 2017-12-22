package CardTypes;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Elmo extends Card {
	private final String COLOR;
	private final int NUMBER = 4;
	private Image img;
	private ImageView cardView;
	private Image back = new Image("file:FinalProjectAssets\\back.jpg");
	private ImageView backView = new ImageView(back);
	
	public Elmo(String color){
		COLOR = color;
		if(color.equals("red")){
			img = new Image("file:FinalProjectAssets\\4_red.jpg");
		}else if(color.equals("yellow")){
			img = new Image("file:FinalProjectAssets\\4_yellow.jpg");
		}else if(color.equals("blue")){
			img = new Image("file:FinalProjectAssets\\4_blue.jpg");
		}else{
			img = new Image("file:FinalProjectAssets\\4_green.jpg");
		}
		
		cardView = new ImageView(img);
		cardView.setFitHeight(847.0/3);
		cardView.setFitWidth(583.0/3);
		backView.setFitHeight(847.0/3);
		backView.setFitWidth(583.0/3);
	}
	@Override
	public ImageView getBackView() {
		// TODO Auto-generated method stub
		return backView;
	}
	
	@Override
	public ImageView getImageView() {
		// TODO Auto-generated method stub
		return cardView;
	}
	
	public String toString(){
		return  NUMBER + " - " + COLOR;
	}
	
	public String getColor(){
		return COLOR;
	}
	
	public int getNumber(){
		return NUMBER;
	}
}
