package com.example.javafx.life;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.function.Consumer;

public class Controller {
    private Stage stage;
    private Scene scene;
    private GameOfLife game;
    private long lastUpdate = 0;
    private long updateInterval = 500_000_000;
    private double cellSize = 7.5;
    private int rows = 75;
    private int cols = 75;
    private boolean isGameRunning = false;
    private boolean wrappingEdges = false;
    private boolean flipHoriz = false;
    private boolean flipVert = false;
    private boolean rotate90 = false;
    private boolean gridLinesOn = true;
    private boolean isRectCell = true;
    private boolean[][] selectedPattern = null;
    private Color backgroundColor = Color.WHITE;
    private Color cellColor = Color.BLACK;
    public final Object[][] colorSchemes = {
            {Color.BLACK, Color.WHITE, "Color Scheme: Mono"},
            {Color.WHITE, Color.BLACK, "Color Scheme: Invert"},
            {Color.rgb(228,239,240), Color.rgb(0,80,102), "Color Scheme: Cool"},
            {Color.rgb(248, 131, 121), Color.rgb(28,95,144), "Color Scheme: Coral"},
            {Color.rgb(255,90,0), Color.rgb(255,232,8), "Color Scheme: Flame"},
            {Color.rgb(90,139,93), Color.rgb(78,52,29), "Color Scheme: Earth"},
            {Color.rgb(222,255,117), Color.rgb(150,117,255), "Color Scheme: Lavender"},
            {Color.rgb(0,255,43), Color.rgb(0,0,0), "Color Scheme: Matrix"},
            {Color.rgb(153,141,224), Color.rgb(44,31,116), "Color Scheme: Midnight"},
            {Color.rgb(92,244,218), Color.rgb(0,128,255), "Color Scheme: Ocean"},
            {Color.rgb(223,175,180), Color.rgb(99,31,58), "Color Scheme: Velvet"},
            {Color.rgb(144,38,20), Color.rgb(20,144,100), "Color Scheme: Zombie"}
    };
    private int colorSchemeIndex = 0;
    private final AnimationTimer timer= new AnimationTimer() {
        @Override
        public void handle(long now) {
            if (now - lastUpdate >= updateInterval) {
                game.nextGeneration();
                updateGrid();
                lastUpdate = now;
            }
        }
    };
    @FXML
    private GridPane gameGrid;
    @FXML
    private Button PlayPause, WrapEdges, invertHorizButton, invertVertButton, colorScheme, gridLines, cellShape, rotate90Button;
    @FXML
    private Slider speedSlider, minThresholdSlider, maxThresholdSlider, birthThresholdSlider;
    @FXML
    public Label infoLabel;

    @FXML
    public void initialize() {
        setupGame();
        setupSliders();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setWidth(1082.0);
        stage.setHeight(724.0);
        stage.setResizable(false);
    }

    public void setScene(Scene scene) {
        this.scene = scene;
        setupKeyEvents();
    }

    private void setupKeyEvents() {
        if (scene != null) {
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.SPACE) {
                    togglePlayPause();
                } else if (event.getCode() == KeyCode.R) {
                    initialize();
                }
            });
        }
    }

    private void setupGame() {
        timer.stop();
        isGameRunning = false;
        PlayPause.setText("Play");
        gameGrid.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        String text = """
                Welcome to Life!
                This is a zero-player cellular automation game designed by John Horton Conway in 1970.

                Rules:
                1. Any live cell with two or three live neighbors lives on to the next generation.
                2. Any live cell with less than two live neighbors dies.
                3. Any live cell with more than three live neighbors dies.
                4. Any dead cell with exactly three live neighbors becomes a live cell.
                
                Try changing the parameters for different results!
                """;
        infoLabel.setText(text);
        game = new GameOfLife(rows, cols, 3, 2, 3, wrappingEdges);
        game.init();
        updateGrid();
    }

    private void setupSliders() {
        setupSlider(speedSlider, 1.0, this::updateSpeed);
        setupSlider(birthThresholdSlider, 3, val -> game.setBirthThreshold(val));
        setupLinkedSliders(minThresholdSlider, 2, maxThresholdSlider, val -> game.setSurvivalThresholdMin(val));
        setupLinkedSliders(maxThresholdSlider, 3, minThresholdSlider, val -> game.setSurvivalThresholdMax(val));
    }

    private void setupSlider(Slider slider, double initialValue, Consumer<Integer> updateFunction) {
        slider.setValue(initialValue);
        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int roundedValue = Math.round(newVal.floatValue());
            slider.setValue(roundedValue);
            updateFunction.accept(roundedValue);
        });
    }

    private void setupLinkedSliders(Slider primary, double initialValue, Slider dependent, Consumer<Integer> updateFunction) {
        primary.setValue(initialValue);
        primary.valueProperty().addListener((obs, oldVal, newVal) -> {
            int roundedValue = Math.round(newVal.floatValue());
            if ((primary == minThresholdSlider && roundedValue > dependent.getValue()) ||
                    (primary == maxThresholdSlider && roundedValue < dependent.getValue())) {
                dependent.setValue(roundedValue);
            }
            primary.setValue(roundedValue);
            updateFunction.accept(roundedValue);
        });
    }

    private void updateSpeed(int value) {
        updateInterval = (long) ((1.0 / value) * 500_000_000);
    }

    private void updateGrid() {
        boolean[][] grid = game.getGrid();
        gameGrid.getChildren().clear();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Shape cell = getCell(grid, i, j);
                GridPane.setRowIndex(cell, i);
                GridPane.setColumnIndex(cell, j);
                gameGrid.getChildren().add(cell);
            }
        }
    }

    private Shape getCell(boolean[][] grid, int i, int j) {
        Shape cell;
        if (!isRectCell) {
            double radius = cellSize / 2;
            cell = new Circle(radius);
            ((Circle) cell).setCenterX(radius);
            ((Circle) cell).setCenterY(radius);
        } else {
            cell = new Rectangle(cellSize, cellSize);
        }

        cell.setFill(grid[i][j] ? cellColor : backgroundColor);
        if(gridLinesOn) {
            cell.setStroke(grid[i][j] ? backgroundColor : cellColor);
            cell.setStrokeWidth(0.2);
        } else {
            cell.setStrokeWidth(0);
        }

        final int row = i;
        final int col = j;
        cell.setOnMouseClicked(event -> handleCellClick(row, col, cell));
        return cell;
    }

    private void handleCellClick(int row, int col, Shape cell) {
        if (selectedPattern != null) {
            game.placePattern(selectedPattern, row, col, flipHoriz, flipVert);
            selectedPattern = null;
            updateGrid();
        } else {
            game.toggleCell(row, col);
            cell.setFill(game.getGrid()[row][col] ? cellColor : backgroundColor);
        }
    }


    public void selectPattern(MouseEvent event) {
        Button btn = (Button) event.getSource();
        String patternName = btn.getText();
        selectedPattern = Patterns.getPattern(patternName);
        if (rotate90) {
            selectedPattern = Patterns.rotate90Deg(selectedPattern);
        }
    }

    public void randomize() {
        game.randomizeGrid();
        updateGrid();
    }

    public void gridSizeS() {
        cellSize = 11.25;
        rows = 50;
        cols = 50;
        setupGame();
    }

    public void gridSizeM() {
        cellSize = 7.5;
        rows = 75;
        cols = 75;
        setupGame();
    }

    public void gridSizeX() {
        cellSize = 5.625;
        rows = 100;
        cols = 100;
        setupGame();
    }

    public void gridSizeXL() {
        cellSize = 2.5;
        rows = 200;
        cols = 200;
        setupGame();
    }

    public void togglePlayPause() {
        if (isGameRunning) {
            timer.stop();
            PlayPause.setText("Play");
        } else {
            timer.start();
            PlayPause.setText("Pause");
        }
        isGameRunning = !isGameRunning;
    }

    public void toggleColorScheme() {
        colorSchemeIndex = (colorSchemeIndex + 1) % colorSchemes.length;
        cellColor = (Color) colorSchemes[colorSchemeIndex][0];
        backgroundColor = (Color) colorSchemes[colorSchemeIndex][1];
        colorScheme.setText((String) colorSchemes[colorSchemeIndex][2]);
        gameGrid.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        updateGrid();
    }

    public void toggleGridLines() {
        gridLinesOn = !gridLinesOn;
        gridLines.setText(gridLinesOn ? "Grid Lines: On" : "Grid Lines: Off");
        updateGrid();
    }

    public void toggleCellShape() {
        isRectCell = !isRectCell;
        cellShape.setText(isRectCell ? "Cell Shape: Square" : "Cell Shape: Circle");
        updateGrid();
    }

    public void toggleBoundaryMode() {
        wrappingEdges = !wrappingEdges;
        game.toggleWrapping();
        WrapEdges.setText(wrappingEdges ? "Boundary Mode: Wrap" : "Boundary Mode: Static");
    }

    public void toggleFlipHorizontal() {
        flipHoriz = !flipHoriz;
        invertHorizButton.setText(flipHoriz ? "Invert Horizontally: On" : "Invert Horizontally: Off");
    }

    public void toggleFlipVertical() {
        flipVert = !flipVert;
        invertVertButton.setText(flipVert ? "Invert Vertically: On" : "Invert Vertically: Off");
    }

    public void toggleRotate90Deg() {
        rotate90 = !rotate90;
        rotate90Button.setText(rotate90 ? "Rotate 90 deg: On" : "Rotate 90 deg: Off");
    }

    public void showCredits() {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Credits");

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        Label creditsLabel = new Label("""
                Developed and Programmed by Ethan Fischer
                Original Concept by John Horton Conway
                ©2023 All rights reserved.""");
        creditsLabel.setTextAlignment(TextAlignment.CENTER);

        Button closeButton = new Button("Close");
        closeButton.setFocusTraversable(false);
        closeButton.setOnAction(e -> popupStage.close());

        layout.getChildren().addAll(creditsLabel, closeButton);
        Scene scene = new Scene(layout, 300, 150);
        popupStage.setScene(scene);
        popupStage.initOwner(this.stage);
        popupStage.showAndWait();
    }

    public void Exit() {
        stage.close();
    }
}