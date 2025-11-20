package com.example.crudalimentosmedicamentos.crud.ui.controllers;

import com.example.crudalimentosmedicamentos.crud.model.Alimento;
import com.example.crudalimentosmedicamentos.crud.model.Medicamento;
import com.example.crudalimentosmedicamentos.crud.service.AlimentoService;
import com.example.crudalimentosmedicamentos.crud.service.MedicamentoService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class MainController {
    @FXML private TabPane tabPane;

    // Alimentos
    @FXML private TableView<Alimento> tableAlimentos;
    @FXML private TableColumn<Alimento, Long> colAlId;
    @FXML private TableColumn<Alimento, String> colAlNombre;
    @FXML private TextField tfAlNombre;
    @FXML private TextField tfAlCategoria;
    @FXML private DatePicker dpAlVenc;
    @FXML private TextField tfAlStock;
    @FXML private TextArea taAlObs;
    @FXML private Button btnAlGuardar, btnAlNuevo, btnAlEliminar;

    // Medicamentos
    @FXML private TableView<Medicamento> tableMedicamentos;
    @FXML private TableColumn<Medicamento, Long> colMedId;
    @FXML private TableColumn<Medicamento, String> colMedNombre;
    @FXML private TextField tfMedNombre;
    @FXML private TextField tfMedPrincipio;
    @FXML private TextField tfMedDosis;
    @FXML private DatePicker dpMedVenc;
    @FXML private TextField tfMedStock;
    @FXML private TextArea taMedObs;
    @FXML private Button btnMedGuardar, btnMedNuevo, btnMedEliminar;

    private final AlimentoService alimentoService = new AlimentoService();
    private final MedicamentoService medicamentoService = new MedicamentoService();

    private ObservableList<Alimento> alimentos = FXCollections.observableArrayList();
    private ObservableList<Medicamento> medicamentos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // configurar tablas
        colAlId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAlNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colMedId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMedNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        loadAlimentos();
        loadMedicamentos();

        tableAlimentos.setOnMouseClicked((MouseEvent e) -> {
            Alimento sel = tableAlimentos.getSelectionModel().getSelectedItem();
            if (sel != null) populateAlimento(sel);
        });

        tableMedicamentos.setOnMouseClicked((MouseEvent e) -> {
            Medicamento sel = tableMedicamentos.getSelectionModel().getSelectedItem();
            if (sel != null) populateMedicamento(sel);
        });
    }

    private void loadAlimentos() {
        try {
            alimentos.setAll(alimentoService.listar());
            tableAlimentos.setItems(alimentos);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void loadMedicamentos() {
        try {
            medicamentos.setAll(medicamentoService.listar());
            tableMedicamentos.setItems(medicamentos);
        } catch (Exception ex) {
            showError(ex);
        }
    }

    private void populateAlimento(Alimento a) {
        tfAlNombre.setText(a.getNombre());
        tfAlCategoria.setText(a.getCategoria());
        dpAlVenc.setValue(a.getFechaVencimiento());
        tfAlStock.setText(a.getStock() == null ? "" : a.getStock().toString());
        taAlObs.setText(a.getObservaciones());
        btnAlGuardar.setUserData(a);
    }

    private void populateMedicamento(Medicamento m) {
        tfMedNombre.setText(m.getNombre());
        tfMedPrincipio.setText(m.getPrincipioActivo());
        tfMedDosis.setText(m.getDosis());
        dpMedVenc.setValue(m.getFechaVencimiento());
        tfMedStock.setText(m.getStock() == null ? "" : m.getStock().toString());
        taMedObs.setText(m.getObservaciones());
        btnMedGuardar.setUserData(m);
    }

    @FXML
    private void onAlNuevo() {
        tfAlNombre.clear(); tfAlCategoria.clear(); dpAlVenc.setValue(null); tfAlStock.clear(); taAlObs.clear();
        btnAlGuardar.setUserData(null);
    }

    @FXML
    private void onAlGuardar() {
        try {
            Alimento a = (Alimento) btnAlGuardar.getUserData();
            if (a == null) a = new Alimento();
            a.setNombre(tfAlNombre.getText());
            a.setCategoria(tfAlCategoria.getText());
            a.setFechaVencimiento(dpAlVenc.getValue());
            a.setStock(tfAlStock.getText().isEmpty() ? null : Integer.parseInt(tfAlStock.getText()));
            a.setObservaciones(taAlObs.getText());
            if (a.getId() == null) alimentoService.crear(a); else alimentoService.actualizar(a);
            loadAlimentos();
            onAlNuevo();
        } catch (Exception ex) { showError(ex); }
    }

    @FXML
    private void onAlEliminar() {
        Alimento sel = tableAlimentos.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        try { alimentoService.eliminar(sel.getId()); loadAlimentos(); } catch (Exception ex) { showError(ex); }
    }

    @FXML
    private void onMedNuevo() {
        tfMedNombre.clear(); tfMedPrincipio.clear(); tfMedDosis.clear(); dpMedVenc.setValue(null); tfMedStock.clear(); taMedObs.clear();
        btnMedGuardar.setUserData(null);
    }

    @FXML
    private void onMedGuardar() {
        try {
            Medicamento m = (Medicamento) btnMedGuardar.getUserData();
            if (m == null) m = new Medicamento();
            m.setNombre(tfMedNombre.getText());
            m.setPrincipioActivo(tfMedPrincipio.getText());
            m.setDosis(tfMedDosis.getText());
            m.setFechaVencimiento(dpMedVenc.getValue());
            m.setStock(tfMedStock.getText().isEmpty() ? null : Integer.parseInt(tfMedStock.getText()));
            m.setObservaciones(taMedObs.getText());
            if (m.getId() == null) medicamentoService.crear(m); else medicamentoService.actualizar(m);
            loadMedicamentos();
            onMedNuevo();
        } catch (Exception ex) { showError(ex); }
    }

    @FXML
    private void onMedEliminar() {
        Medicamento sel = tableMedicamentos.getSelectionModel().getSelectedItem();
        if (sel == null) return;
        try { medicamentoService.eliminar(sel.getId()); loadMedicamentos(); } catch (Exception ex) { showError(ex); }
    }

    private void showError(Exception ex) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("Error");
        a.setContentText(ex.getMessage());
        a.showAndWait();
    }
}