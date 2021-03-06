package application;

import java.util.Scanner;

import CardTypes.Card;
import CardTypes.ErnieAndBert;
import CardTypes.Oscar;
import CardTypes.Wild;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Text computerWon = new Text("GAME OVER" + "\nCOMPUTER WON");
			Text playerWon = new Text("GAME OVER" + "\nPLAYER WON");
			StackPane gameOver = new StackPane();

			Scene end = new Scene(gameOver, 1400, 100);

			CardDeck first = new CardDeck();
			first.reset();
			Player player = new Player();
			Player computer = new Player();

			primaryStage.setTitle("CSE-114: UNO Final Project");
			final double HEIGHT = 847.0 / 3;
			final double WIDTH = 583.0 / 3;

			// IMAGES and ImageViews
			Image cardBack = new Image("file:back.jpg");
			ImageView cardBackView = new ImageView(cardBack);
			cardBackView.setFitHeight(HEIGHT);
			cardBackView.setFitWidth(WIDTH);

			/** new scene for start of game(pick a card) */
			BorderPane start = new BorderPane();
			Scene startGame = new Scene(start, 1400, 1000);
			primaryStage.setScene(startGame);

			GridPane top = new GridPane();
			top.setAlignment(Pos.CENTER);

			Label intro = new Label("Pick a card");
			intro.setFont(new Font(70));
			top.add(intro, 1, 0);
			top.add(new Label("Enter an integer ( 0 - 35 ): "), 1, 1);
			TextField tf = new TextField();
			top.add(tf, 1, 2);

			GridPane bottom = new GridPane();
			bottom.setAlignment(Pos.CENTER);

			VBox containerCenter = new VBox();

			HBox drawCards = new HBox();

			StackPane userDraw = new StackPane();
			StackPane computerDraw = new StackPane();

			ImageView startUserBack = new ImageView(cardBack);
			ImageView startCompBack = new ImageView(cardBack);
			startUserBack.setFitHeight(HEIGHT);
			startUserBack.setFitWidth(WIDTH);
			startCompBack.setFitHeight(HEIGHT);
			startCompBack.setFitWidth(WIDTH);

			userDraw.getChildren().add(startUserBack);
			computerDraw.getChildren().add(startCompBack);

			drawCards.getChildren().add(userDraw);
			drawCards.getChildren().add(computerDraw);
			drawCards.setAlignment(Pos.CENTER);
			drawCards.setSpacing(200);

			HBox prompts = new HBox();
			Label userTemp = new Label("YOU DREW: ");
			Label compTemp = new Label("COMPUTER DREW: ");
			prompts.getChildren().add(userTemp);
			prompts.getChildren().add(compTemp);
			prompts.setAlignment(Pos.CENTER);
			prompts.setSpacing(200);

			containerCenter.getChildren().add(prompts);
			containerCenter.getChildren().add(drawCards);

			Button continue_0 = new Button("CONTINUE");

			tf.setOnAction(e -> {

				int index = Integer.parseInt(tf.getText());
				first.shuffle();
				Card userHigh = first.getDeck()[index];
				player.setHighCard(userHigh);
				first.discard(index);

				// computer draws card
				int compIndex = (int) (Math.random() * first.getDeck().length);
				Card compHigh = first.getDeck()[compIndex];
				computer.setHighCard(compHigh);
				first.discard(compIndex);

				// compareHighCard

				userDraw.getChildren().add(userHigh.getImageView());
				computerDraw.getChildren().add(compHigh.getImageView());
				bottom.getChildren().clear();

				Label both = new Label("Players both drew same card value");
				Label you = new Label("You go first!!");
				Label comp = new Label("Computer goes first!!");
				both.setFont(new Font(70));
				you.setFont(new Font(70));
				comp.setFont(new Font(70));

				if (userHigh.getNumber() == compHigh.getNumber()) {
					VBox temp = new VBox();
					temp.getChildren().add(both);
					bottom.getChildren().add(temp);

				} else if (userHigh.getNumber() > compHigh.getNumber()) {
					VBox temp = new VBox();

					temp.getChildren().add(continue_0); // delete and make
														// button outside scope
					temp.setAlignment(Pos.CENTER);
					temp.getChildren().add(you);
					bottom.getChildren().add(temp);

				} else {
					VBox temp = new VBox();

					temp.getChildren().add(continue_0);
					temp.setAlignment(Pos.CENTER);
					temp.getChildren().add(comp);
					player.setValid(false);
					computer.setTurn(true);
					bottom.getChildren().add(temp);

				}

			});

			start.setTop(top);
			start.setBottom(bottom);
			start.setCenter(containerCenter);

			/** new scene for gamestart */
			BorderPane game = new BorderPane();
			game.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Scene playGame = new Scene(game, 1400, 1000);

			HBox center = new HBox();

			StackPane drawPile = new StackPane(); // for main deck
			StackPane discardPile = new StackPane();

			ImageView tempView = new ImageView(cardBack);
			tempView.setFitHeight(HEIGHT);
			tempView.setFitWidth(WIDTH);

			center.setAlignment(Pos.CENTER);

			center.getChildren().add(drawPile);

			VBox middle = new VBox();
			Button draw = new Button("DRAW");
			Button endTurn = new Button("END TURN");
			Button uno = new Button("UNO");
			middle.getChildren().add(uno);
			HBox colorPicker = new HBox();

			Button red = new Button();
			red.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
			colorPicker.getChildren().add(red);

			Button yellow = new Button();
			yellow.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
			colorPicker.getChildren().add(yellow);

			Button blue = new Button();
			blue.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
			colorPicker.getChildren().add(blue);

			Button green = new Button();
			green.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
			colorPicker.getChildren().add(green);

			colorPicker.setSpacing(15);

			CardDeck deck = new CardDeck();
			deck.reset();
			deck.shuffle();
			deck.reverse();
			DiscardPile discard = new DiscardPile();

			drawPile.getChildren().add(cardBackView);
			discardPile.getChildren().add(tempView);
			discardPile.setStyle("-fx-background: white");

			Text deckNum = new Text(deck.getDeck().length + "");
			deckNum.setFont(new Font(100));
			deckNum.setStyle("-fx-fill: red;" + "-fx-stroke: black;" + "-fx-stroke-width: 4;");
			drawPile.getChildren().add(deckNum);

			for (int i = 0; i < 5; i++) {
				Card card = deck.drawTop(); // draw
				player.addCard(card);// add to player hand
				deck.discard(0);// discard card , will always be at the top\

				// repeat for computer
				card = deck.drawTop();
				computer.addCard(card);
				deck.discard(0);
			}

			HBox bottomContainer = new HBox();
			ScrollPane userScroll = new ScrollPane(bottomContainer);

			bottomContainer.setAlignment(Pos.CENTER);

			for (int i = 0; i < player.getHand().length; i++) {
				bottomContainer.getChildren().add(player.getHand()[i].getImageView());

			}

			HBox topContainer = new HBox();
			ScrollPane compScroll = new ScrollPane(topContainer);

			topContainer.setAlignment(Pos.CENTER);

			for (int i = 0; i < computer.getHand().length; i++) {
				topContainer.getChildren().add(computer.getHand()[i].getBackView());
			}

			// decide fist card to match
			while (true) {
				Card tempCard = deck.drawTop();
				deck.discard(0);
				discard.addCard(tempCard);
				discardPile.getChildren().add(tempCard.getImageView());
				deckNum.setText(deck.getDeck().length + "");

				if (tempCard.getNumber() <= 7) {
					System.out.println("MATCH: " + discard.getLastCard());
					break;
				}

			}

			draw.setOnAction(e -> {
				if (!player.isValid()) {
					checkDeck(deck, discard, discardPile);

					Card tempCard = deck.drawTop();
					deck.discard(0);

					deckNum.setText(deck.getDeck().length + "");

					bottomContainer.getChildren().add(tempCard.getImageView());
					player.addCard(tempCard);

					player.setValid(true);
					player.setDrewCard(true);
				}
			});

			userScroll.setOnMouseClicked(e -> {
				if (!player.isValid() || player.isDrewCard()) {
					ImageView temp = (ImageView) (e.getTarget());
					int i = bottomContainer.getChildren().indexOf(temp);

					Card selectedCard = player.getHand()[i];
					if (selectedCard instanceof Wild) {
						// add colorpicker
						middle.getChildren().add(colorPicker);

						red.setOnAction(ev -> {
							((Wild) selectedCard).setColor("red");
							discardPile.getChildren().add(temp);
							discard.addCard(selectedCard);
							player.discard(i);
							discardPile.setStyle("-fx-background-color: red");
							middle.getChildren().remove(colorPicker);
						});

						yellow.setOnAction(ev -> {
							((Wild) selectedCard).setColor("yellow");
							discardPile.getChildren().add(temp);
							discard.addCard(selectedCard);
							player.discard(i);
							discardPile.setStyle("-fx-background-color: yellow");
							middle.getChildren().remove(colorPicker);
						});

						blue.setOnAction(ev -> {
							((Wild) selectedCard).setColor("blue");
							discardPile.getChildren().add(temp);
							discard.addCard(selectedCard);
							player.discard(i);
							discardPile.setStyle("-fx-background-color: blue");
							middle.getChildren().remove(colorPicker);
						});

						green.setOnAction(ev -> {
							((Wild) selectedCard).setColor("green");
							discardPile.getChildren().add(temp);
							discard.addCard(selectedCard);
							player.discard(i);
							discardPile.setStyle("-fx-background-color: green");
							middle.getChildren().remove(colorPicker);
						});

						player.setValid(true);
						player.setDrewCard(false);
					} else if (selectedCard.equals(discard.getLastCard())) {
						discardPile.setStyle("-fx-background-color: white");
						discardPile.getChildren().add(temp);
						System.out.println(selectedCard);

						discard.addCard(selectedCard);
						player.discard(i);

						if (selectedCard instanceof ErnieAndBert) {
							checkDeck(deck, discard, discardPile);
							Card tempCard = deck.drawTop();
							deck.discard(0);

							deckNum.setText(deck.getDeck().length + "");

							topContainer.getChildren().add(tempCard.getBackView());
							computer.addCard(tempCard);
							player.setValid(false);
							player.setDrewCard(false);
						} else if (selectedCard instanceof Oscar) {
							for (int j = 0; j < 2; j++) {
								checkDeck(deck, discard, discardPile);

								Card tempCard = deck.drawTop();
								deck.discard(0);

								deckNum.setText(deck.getDeck().length + "");

								topContainer.getChildren().add(tempCard.getBackView());
								computer.addCard(tempCard);
							}
							if (player.getHand().length == 0) {
								player.setValid(true);
							} else {
								player.setValid(false);
							}
							player.setDrewCard(false);

						} else {
							player.setValid(true);
							player.setDrewCard(false);
						}

						// FIXME?
						if (player.getHand().length == 1) {
							if (player.getUno()) {
								player.setUno(false);
							} else {
								for (int j = 0; j < 2; j++) {
									checkDeck(deck, discard, discardPile);

									Card tempCard = deck.drawTop();
									deck.discard(0);
									deckNum.setText(deck.getDeck().length + "");
									bottomContainer.getChildren().add(tempCard.getImageView());

									player.addCard(tempCard);
								}
								player.setUno(false);
							}
						}

					}
					System.out.println("Player plays: " + selectedCard);

				}
			});

			// TODO implement UNO button
			endTurn.setOnAction(e -> {
				if (player.isValid()) {

					if (player.getHand().length == 0) {
						gameOver.getChildren().add(playerWon);
						primaryStage.setScene(end);
					} else if (player.getHand().length == 2) {
						uno.setOnAction(ev -> player.setUno(true));
					} else {
						try {
							middle.getChildren().set(2, null);
							player.setUno(false);
						} catch (Exception ex) {

						}
					}
					System.out.println("Match card: " + discard.getLastCard());

					boolean compValid = false;
					for (int i = 0; i < computer.getHand().length; i++) {
						Card temp = computer.getHand()[i];
						if (temp instanceof Wild) {
							int color = (int) (Math.random() * 4);
							if (color == 1) {
								discardPile.setStyle("-fx-background-color: red");
								((Wild) temp).setColor("red");
							} else if (color == 2) {
								discardPile.setStyle("-fx-background-color: yellow");
								((Wild) temp).setColor("yellow");
							} else if (color == 3) {
								discardPile.setStyle("-fx-background-color: blue");
								((Wild) temp).setColor("blue");
							} else {
								discardPile.setStyle("-fx-background-color: green");
								((Wild) temp).setColor("green");
							}
							discardPile.getChildren().add(temp.getImageView());
							topContainer.getChildren().remove(i);
							discard.addCard(temp);
							computer.discard(i);
							compValid = true;
							//FIXME here
							player.setValid(false);
							break;
						} else if (temp.equals(discard.getLastCard())) {
							System.out.println("Computer plays: " + temp);
							discardPile.setStyle("-fx-background-color: white");

							discardPile.getChildren().add(temp.getImageView());
							topContainer.getChildren().remove(i);
							discard.addCard(temp);
							computer.discard(i);
							if (temp instanceof ErnieAndBert) {
								checkDeck(deck, discard, discardPile);

								Card tempCard = deck.drawTop();
								deck.discard(0);

								deckNum.setText(deck.getDeck().length + "");

								bottomContainer.getChildren().add(tempCard.getImageView());
								player.addCard(tempCard);
								player.setValid(true);
								player.setDrewCard(false);
								// how to make player lose turn?
							} else if (temp instanceof Oscar) {
								for (int j = 0; j < 2; j++) {
									checkDeck(deck, discard, discardPile);

									Card tempCard = deck.drawTop();
									deck.discard(0);

									deckNum.setText(deck.getDeck().length + "");

									bottomContainer.getChildren().add(tempCard.getImageView());
									player.addCard(tempCard);
								}

								player.setValid(true);
								player.setDrewCard(false);
							} else {
								player.setValid(false);
							}

							compValid = true;
							break;
						}

						if (!compValid) {
							checkDeck(deck, discard, discardPile);

							Card tempCard = deck.drawTop();
							deck.discard(0);

							deckNum.setText(deck.getDeck().length + "");

							topContainer.getChildren().add(tempCard.getBackView());
							computer.addCard(tempCard);
							System.out.println("--computer draws--");
							player.setValid(false);
							compValid = true;
						}

					}
					if (computer.getHand().length == 0) {
						gameOver.getChildren().add(computerWon);
						primaryStage.setScene(end);
					}
				}
			});

			continue_0.setOnAction(e -> {
				if (computer.getTurn()) {
					boolean compValid = false;
					for (int i = 0; i < computer.getHand().length; i++) {
						Card temp = computer.getHand()[i];
						if (temp instanceof Wild) {
							System.out.println(temp + " matches the card");

							int color = (int) (Math.random() * 4);
							if (color == 1) {
								discardPile.setStyle("-fx-background-color: red");
								((Wild) temp).setColor("red");
							} else if (color == 2) {
								discardPile.setStyle("-fx-background-color: yellow");
								((Wild) temp).setColor("yellow");
							} else if (color == 3) {
								discardPile.setStyle("-fx-background-color: blue");
								((Wild) temp).setColor("blue");
							} else {
								discardPile.setStyle("-fx-background-color: green");
								((Wild) temp).setColor("green");
							}
							discardPile.getChildren().add(temp.getImageView());
							topContainer.getChildren().remove(i);
							discard.addCard(temp);
							computer.discard(i);
							compValid = true;
							//FIXME here
							player.setValid(false);
							break;
						} else if (temp.equals(discard.getLastCard())) {
							System.out.println(temp + " matches the card");
							discardPile.setStyle("-fx-background-color: white");

							discardPile.getChildren().add(temp.getImageView());
							topContainer.getChildren().remove(i);
							discard.addCard(temp);
							computer.discard(i);
							if (temp instanceof ErnieAndBert) {
								checkDeck(deck, discard, discardPile);

								Card tempCard = deck.drawTop();
								deck.discard(0);

								deckNum.setText(deck.getDeck().length + "");

								bottomContainer.getChildren().add(tempCard.getImageView());
								player.addCard(tempCard);
								player.setValid(true);
								// how to make player lose turn?
							} else if (temp instanceof Oscar) {
								for (int j = 0; j < 2; j++) {
									checkDeck(deck, discard, discardPile);

									Card tempCard = deck.drawTop();
									deck.discard(0);

									deckNum.setText(deck.getDeck().length + "");

									bottomContainer.getChildren().add(tempCard.getImageView());
									player.addCard(tempCard);
								}

								player.setValid(true);
							} else {
								player.setValid(false);
							}

							compValid = true;
							break;
						}
					}

					if (!compValid) {
						checkDeck(deck, discard, discardPile);
						Card tempCard = deck.drawTop();
						deck.discard(0);

						deckNum.setText(deck.getDeck().length + "");

						topContainer.getChildren().add(tempCard.getBackView());
						computer.addCard(tempCard);
					}

					player.setValid(false);
				}
				primaryStage.setScene(playGame);
			});

			middle.setAlignment(Pos.CENTER);
			middle.setSpacing(50);
			middle.getChildren().add(draw);
			middle.getChildren().add(endTurn);

			center.getChildren().add(middle);
			center.getChildren().add(discardPile);
			center.setSpacing(200);
			center.setStyle("-fx-background-color: white");

			game.setTop(compScroll);
			game.setCenter(center);
			game.setBottom(userScroll);

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static void checkDeck(CardDeck deck, DiscardPile discard, StackPane discardPile) {
		if (deck.getDeck().length == 0) {
			System.out.println("\nNEW DECK");
			System.out.println("Current deck size: " + deck.getDeck().length); // 0
			System.out.println("Current discard size: " + discard.getDeck().length); // ?

			for (int j = 0; j < discard.getDeck().length - 1; j++) {
				deck.addCard(discard.getDeck()[j]);
				discard.getDeck()[j] = null;
			}
			deck.shuffle();

			discard.removeNull();

			discardPile.getChildren().remove(0, discardPile.getChildren().size() - 1);

			System.out.println("\nNew Deck length: " + deck.getDeck().length); // same
																				// as
																				// discard
			System.out.println("New Discard length: " + discard.getDeck().length);

		}
	}

}
