package feras;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.beans.property.SimpleStringProperty;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Die Hauptanwendung zur Verwaltung von Mitarbeiterdaten mithilfe von JavaFX.
 */
public class EmployeeManagementApp extends Application {

    public TableView<String> tabelle = new TableView<>();
    public VBox tableButtonsBox = new VBox(10);
    public String currentTable = "";
    public String currentPrimaryKeyName = "";
    public List<String> currentColumnNames = new ArrayList<>();
    public ComboBox<String> searchComboBox = new ComboBox<>();
    public Button btnSearch = new Button("Suche");

    /**
     * Erstellt eine Verbindung zur Datenbank über die DBHelper-Klasse.
     * @return Ein Connection-Objekt, das mit der Datenbank verbunden ist.
     * @throws Exception Falls ein Fehler während der Verbindung auftritt.
     */

    public  Connection verbindeDB() throws Exception {
        return DBHelper.getConnection();
    }

    /**
     * Lädt die Daten der angegebenen Tabelle und zeigt sie in der TableView an.
     * @param tabelleName Der Name der Tabelle, die geladen werden soll.
     */
    public void ladeDaten(String tabelleName) {
        // Durchführung des Ladens der Daten aus der Tabelle
        tabelle.getItems().clear();
        tabelle.getColumns().clear();
        currentColumnNames.clear();

        try (Connection conn = verbindeDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabelleName)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Aktuellen Tabellennamen festlegen und davon ausgehen, dass die erste Spalte der Primärschlüssel ist
            currentTable = tabelleName;
            if (columnCount > 0) {
                currentPrimaryKeyName = metaData.getColumnName(1);
            }

            // Dynamische Erstellung der Spalten und Speicherung der Spaltennamen
            for (int i = 1; i <= columnCount; i++) {
                String colName = metaData.getColumnName(i);
                currentColumnNames.add(colName);
                TableColumn<String, String> column = new TableColumn<>(colName);
                final int columnIndex = i;
                column.setCellValueFactory(data -> {
                    String[] values = data.getValue().split("\\|");
                    return new SimpleStringProperty(values[columnIndex - 1]);
                });
                tabelle.getColumns().add(column);
            }

            // Aktualisieren der Such-ComboBox, um die aktuellen Spaltennamen zu enthalten
            searchComboBox.getItems().clear();
            searchComboBox.getItems().addAll(currentColumnNames);
            searchComboBox.setPromptText("Suchen nach");

            // Befüllen der TableView mit Daten
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i) != null ? rs.getString(i) : "Nicht vorhanden").append("|");
                }
                tabelle.getItems().add(row.toString());
            }
        } catch (Exception e) {
            zeigeFehler("Fehler beim Laden der Daten: " + e.getMessage());
        }
    }

    /**
     * Funktion, um in der aktuellen Tabelle basierend auf einer Spalte und einem Suchwert zu suchen.
     * @param column Der Name der Spalte, in der gesucht werden soll.
     * @param searchValue Der zu suchende Wert.
     */
    public void searchInTable(String column, String searchValue) {
        // Durchführung der Suche in der Tabelle
        tabelle.getItems().clear();
        tabelle.getColumns().clear();

        try (Connection conn = verbindeDB();
             PreparedStatement pstmt = conn.prepareStatement(
                     "SELECT * FROM " + currentTable + " WHERE " + column + " LIKE ?")) {
            pstmt.setString(1, "%" + searchValue + "%");
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Dynamische Erstellung der Spalten
            for (int i = 1; i <= columnCount; i++) {
                TableColumn<String, String> tbColumn = new TableColumn<>(metaData.getColumnName(i));
                final int columnIndex = i;
                tbColumn.setCellValueFactory(data -> {
                    String[] values = data.getValue().split("\\|");
                    return new SimpleStringProperty(values[columnIndex - 1]);
                });
                tabelle.getColumns().add(tbColumn);
            }

            // Befüllen der TableView mit den Ergebnissen
            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= columnCount; i++) {
                    row.append(rs.getString(i) != null ? rs.getString(i) : "Nicht vorhanden").append("|");
                }
                tabelle.getItems().add(row.toString());
            }
        } catch (Exception e) {
            zeigeFehler("Fehler beim Suchen: " + e.getMessage());
        }
    }

    /**
     * Importiert die Daten aus einer XML-Datei in die Datenbank.
     * @param xmlFile Die XML-Datei mit den Quelldaten.
     */
    public void importiereDatenAusXML(File xmlFile) {
        // Durchführung des Imports der Daten aus XML
        try {
            if (!xmlFile.exists()) {
                zeigeFehler("XML-Datei nicht vorhanden");
                return;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            try (Connection conn = verbindeDB()) {
                Statement stmt = conn.createStatement();

                // Ausführen von Abfragen zum Erstellen der Tabellen aus dem <schema>-Abschnitt
                NodeList schemaNodes = doc.getElementsByTagName("schema");
                if (schemaNodes.getLength() > 0) {
                    String sqlSchema = schemaNodes.item(0).getTextContent();
                    for (String sql : sqlSchema.split(";")) {
                        if (!sql.trim().isEmpty()) {
                            stmt.execute(sql.trim() + ";");
                        }
                    }
                }
                zeigeErfolg("Die Tabellen wurden erfolgreich erstellt!");

                // Aufruf der Importfunktionen aus DataImporter
                DataImporter.importFirmen(conn, doc);
                DataImporter.importMitarbeiter(conn, doc);
                DataImporter.importExterneMitarbeiter(conn, doc);
                DataImporter.importKunden(conn, doc);
                DataImporter.importMitarbeiterKunden(conn, doc);

                zeigeErfolg("Alle Daten wurden erfolgreich importiert!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            zeigeFehler("Ein Fehler ist beim Import aufgetreten: " + e.getMessage());
        }
    }

    /**
     * Lädt die Tabellennamen und zeigt sie als Schaltflächen in der Benutzeroberfläche an.
     */
    private void ladeTabellenNamen() {
        // Laden der Tabellennamen
        tableButtonsBox.getChildren().clear();
        try (Connection conn = verbindeDB();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SHOW TABLES")) {

            List<String> tableNames = new ArrayList<>();
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
            if (tableNames.isEmpty()) {
                zeigeFehler("Es gibt keine Tabellen in der Datenbank!");
                return;
            }

            for (String tableName : tableNames) {
                Button btn = new Button(tableName);
                btn.setOnAction(e -> ladeDaten(tableName));
                tableButtonsBox.getChildren().add(btn);
            }
            Stage tableStage = new Stage();
            tableStage.setTitle("Verfügbare Tabellen");
            ScrollPane scrollPane = new ScrollPane(tableButtonsBox);
            scrollPane.setFitToWidth(true);
            Scene scene = new Scene(scrollPane, 300, 400);
            tableStage.setScene(scene);
            tableStage.show();
        } catch (Exception e) {
            zeigeFehler("Fehler beim Laden der Tabellennamen: " + e.getMessage());
        }
    }

    /**
     * Exportiert die Tabellendaten in eine XML-Datei.
     */
    private void exportiereDatenNachXML() {
        // Durchführung des Exports der Daten in XML
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            try (Connection conn = verbindeDB()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.newDocument();

                Element root = doc.createElement("export");
                doc.appendChild(root);

                exportiereTabelle(conn, doc, root, "Firmen");
                exportiereTabelle(conn, doc, root, "Mitarbeiter");
                exportiereTabelle(conn, doc, root, "Externe_Mitarbeiter");
                exportiereTabelle(conn, doc, root, "Kunden");
                exportiereTabelle(conn, doc, root, "Mitarbeiter_Kunden");

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(selectedFile);
                transformer.transform(source, result);

                zeigeErfolg("XML-Export erfolgreich!");
            } catch (Exception e) {
                zeigeFehler("Fehler beim Exportieren: " + e.getMessage());
            }
        }
    }

    /**
     * Exportiert die Daten einer bestimmten Tabelle in eine XML-Datei.
     * @param conn Die Datenbankverbindung.
     * @param doc Ein Document-Objekt zur Erstellung des XML.
     * @param root Das Wurzelelement im XML.
     * @param tabelleName Der Name der Tabelle, die exportiert werden soll.
     */
    private void exportiereTabelle(Connection conn, Document doc, Element root, String tabelleName) {
        // Durchführung des Exports
        String query = "SELECT * FROM " + tabelleName;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            Element tableElement = doc.createElement(tabelleName.toLowerCase());
            root.appendChild(tableElement);

            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()) {
                Element eintrag = doc.createElement("eintrag");
                tableElement.appendChild(eintrag);

                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String spaltenName = metaData.getColumnName(i);
                    String wert = rs.getString(i);
                    Element feld = doc.createElement(spaltenName);
                    feld.setTextContent(wert != null ? wert : "null");
                    eintrag.appendChild(feld);
                }
            }
        } catch (Exception e) {
            zeigeFehler("Fehler beim Abrufen der Daten aus " + tabelleName + ": " + e.getMessage());
        }
    }

    /**
     * Zeigt eine Fehlermeldung in einem Warnfenster an.
     * @param nachricht Der Text der Fehlermeldung.
     */
    private void zeigeFehler(String nachricht) {
        Alert alert = new Alert(Alert.AlertType.ERROR, nachricht, ButtonType.OK);
        alert.setTitle("Fehler");
        alert.showAndWait();
    }

    /**
     * Zeigt eine Erfolgsmeldung in einem Informationsfenster an.
     * @param nachricht Der Text der Erfolgsmeldung.
     */
    private void zeigeErfolg(String nachricht) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, nachricht, ButtonType.OK);
        alert.setTitle("Erfolg");
        alert.showAndWait();
    }

    /**
     * Die main-Methode start() zur Erstellung der grafischen Benutzeroberfläche in JavaFX.
     * @param primaryStage Die primäre Stage in JavaFX.
     */
    @Override
    public void start(Stage primaryStage) {
        // Durchführung der Erstellung der Benutzeroberfläche
        Button btnImport = new Button("XML zu Datenbank");
        Button btnExport = new Button("Datenbank zu XML");
        Button btnShowTables = new Button("Show Tables");
        Button btnBeenden = new Button("Beenden");

        btnImport.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                importiereDatenAusXML(selectedFile);
            }
        });

        btnShowTables.setOnAction(e -> ladeTabellenNamen());
        btnExport.setOnAction(e -> exportiereDatenNachXML());
        btnBeenden.setOnAction(e -> primaryStage.close());

        // Sidebar
        VBox sidebar = new VBox(15, btnShowTables, btnImport, btnExport, btnBeenden);
        sidebar.setPadding(new Insets(20));
        sidebar.setStyle("-fx-background-color: #f4f4f4;");

        // Löschen-Button
        Button btnLoeschen = new Button("Löschen");
        btnLoeschen.setOnAction(e -> {
            String selectedItem = tabelle.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Bestätigung");
                alert.setHeaderText("Sind Sie sicher?");
                alert.setContentText("Möchten Sie die ausgewählte Zeile wirklich löschen?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String[] values = selectedItem.split("\\|");
                    String primaryKeyValue = values[0];

                    try (Connection conn = verbindeDB()) {
                        String sql = "DELETE FROM " + currentTable + " WHERE " + currentPrimaryKeyName + " = ?";
                        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                            try {
                                int intValue = Integer.parseInt(primaryKeyValue);
                                pstmt.setInt(1, intValue);
                            } catch (NumberFormatException ex) {
                                pstmt.setString(1, primaryKeyValue);
                            }
                            int affected = pstmt.executeUpdate();
                            if (affected > 0) {
                                tabelle.getItems().remove(selectedItem);
                                tabelle.refresh();
                                zeigeErfolg("Datensatz erfolgreich gelöscht!");
                            } else {
                                zeigeFehler("Löschen fehlgeschlagen: Kein Datensatz betroffen!");
                            }
                        }
                    } catch (Exception ex) {
                        zeigeFehler("SQL Fehler: " + ex.getMessage());
                    }
                }
            } else {
                System.out.println("Bitte eine Zeile auswählen!");
            }
        });

        // Schaltfläche „Hinzufügen“
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> {
            if (currentTable.isEmpty()) {
                zeigeFehler("Bitte wählen Sie zuerst eine Tabelle aus!");
                return;
            }
            Stage addStage = new Stage();
            addStage.setTitle("Neuen Datensatz hinzufügen zu " + currentTable);
            VBox addForm = new VBox(10);
            addForm.setPadding(new Insets(10));

            List<TextField> textFields = new ArrayList<>();
            for (String col : currentColumnNames) {
                HBox row = new HBox(10);
                Label label = new Label(col + ": ");
                TextField textField = new TextField();
                textFields.add(textField);
                row.getChildren().addAll(label, textField);
                addForm.getChildren().add(row);
            }

            Button saveButton = new Button("Save");
            saveButton.setOnAction(ev -> {
                StringBuilder sqlBuilder = new StringBuilder("INSERT INTO " + currentTable + " (");
                for (int i = 0; i < currentColumnNames.size(); i++) {
                    sqlBuilder.append(currentColumnNames.get(i));
                    if (i < currentColumnNames.size() - 1) {
                        sqlBuilder.append(", ");
                    }
                }
                sqlBuilder.append(") VALUES (");
                for (int i = 0; i < currentColumnNames.size(); i++) {
                    sqlBuilder.append("?");
                    if (i < currentColumnNames.size() - 1) {
                        sqlBuilder.append(", ");
                    }
                }
                sqlBuilder.append(")");
                String sql = sqlBuilder.toString();

                try (Connection conn = verbindeDB();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    for (int i = 0; i < textFields.size(); i++) {
                        String value = textFields.get(i).getText();
                        pstmt.setString(i + 1, value);
                    }
                    int affected = pstmt.executeUpdate();
                    if (affected > 0) {
                        zeigeErfolg("Der Datensatz wurde erfolgreich hinzugefügt!");
                        ladeDaten(currentTable);
                    } else {
                        zeigeFehler("Die Einfügeoperation ist fehlgeschlagen!");
                    }
                } catch (Exception ex) {
                    zeigeFehler("SQL Error: " + ex.getMessage());
                }
                addStage.close();
            });

            addForm.getChildren().add(saveButton);
            Scene addScene = new Scene(addForm);
            addStage.setScene(addScene);
            addStage.show();
        });

        // Schaltfläche „Suchen“
        btnSearch.setOnAction(e -> {
            String selectedColumn = searchComboBox.getValue();
            if (selectedColumn == null || selectedColumn.isEmpty()) {
                zeigeFehler("Bitte wählen Sie eine Suchspalte aus!");
                return;
            }
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Suche in " + selectedColumn);
            dialog.setHeaderText("Geben Sie den Suchwert ein:");
            dialog.setContentText("Der Wert:");
            Optional<String> result = dialog.showAndWait();
            result.ifPresent(searchValue -> {
                searchInTable(selectedColumn, searchValue);
            });
        });

        HBox bottomButtons = new HBox(15, btnLoeschen, btnAdd, searchComboBox, btnSearch);
        bottomButtons.setPadding(new Insets(10));
        bottomButtons.setStyle("-fx-background-color: #dddddd;");

        VBox mainContent = new VBox(10, tabelle, bottomButtons);
        mainContent.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setLeft(sidebar);
        root.setCenter(mainContent);

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Employee Management System");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
//Zum Ausführen in PowerShell:
//cd "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\out\artifacts\ProjectJava_jar"
//java --module-path "C:\Users\FAlshaekhEbraheem\Desktop\ProjectJava\openjfx-23.0.2_windows-x64_bin-sdk\javafx-sdk-23.0.2\lib" --add-modules javafx.controls,javafx.fxml -jar ProjectJava.jar