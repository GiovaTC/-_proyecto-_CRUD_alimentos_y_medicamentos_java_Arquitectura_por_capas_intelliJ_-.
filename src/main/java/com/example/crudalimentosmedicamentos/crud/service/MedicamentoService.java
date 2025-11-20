package com.example.crudalimentosmedicamentos.crud.service;

import com.example.crudalimentosmedicamentos.crud.dao.MedicamentoDAO;
import com.example.crudalimentosmedicamentos.crud.dao.MedicamentoDAOImpl;
import com.example.crudalimentosmedicamentos.crud.model.Medicamento;

import java.util.List;

public class MedicamentoService {
    private final MedicamentoDAO dao = new MedicamentoDAOImpl();

    public List<Medicamento> listar() throws Exception { return dao.findAll(); }
    public Medicamento obtener(Long id) throws Exception { return dao.findById(id); }
    public void crear (Medicamento m) throws Exception { dao.insert(m); }
    public void actualizar (Medicamento m) throws Exception { dao.update(m); }
    public void eliminar (Long id) throws Exception { dao.delete(id); }
}
