package com.example.crudalimentosmedicamentos.crud.service;

import com.example.crudalimentosmedicamentos.crud.dao.AlimentoDAO;
import com.example.crudalimentosmedicamentos.crud.dao.AlimentoDAOImpl;
import com.example.crudalimentosmedicamentos.crud.model.Alimento;

import java.util.List;

public class AlimentoService {
    private final AlimentoDAO dao = new AlimentoDAOImpl();

    public List<Alimento> listar() throws Exception { return dao.findAll(); }
    public Alimento obtener(Long id) throws Exception { return dao.findById(id); }
    public void crear(Alimento a) throws Exception { dao.insert(a); }
    public void actualizar(Alimento a) throws Exception { dao.update(a); }
    public void eliminar(Long id) throws Exception { dao.delete(id); }
}
