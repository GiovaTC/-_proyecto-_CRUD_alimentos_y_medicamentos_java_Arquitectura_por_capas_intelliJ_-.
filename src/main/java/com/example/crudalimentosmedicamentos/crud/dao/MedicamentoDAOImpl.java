package com.example.crudalimentosmedicamentos.crud.dao;

import com.example.crudalimentosmedicamentos.crud.model.Medicamento;
import com.example.crudalimentosmedicamentos.crud.util.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicamentoDAOImpl implements MedicamentoDAO {
    @Override
    public Medicamento findById(Long id) throws Exception {
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement("SELECT * FROM MEDICAMENTOS WHERE ID = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    @Override
    public List<Medicamento> findAll() throws Exception {
        List<Medicamento> list = new ArrayList<>();
        try (Connection c = DbUtil.getConnection(); Statement s = c.createStatement(); ResultSet rs = s.executeQuery("SELECT * FROM MEDICAMENTOS ORDER BY ID")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    @Override
    public void insert(Medicamento m) throws Exception {
        String sql = "INSERT INTO MEDICAMENTOS (NOMBRE, PRINCIPIO_ACTIVO, DOSIS, FECHA_VENCIMIENTO, STOCK, OBSERVACIONES) VALUES (?,?,?,?,?,?)";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getPrincipioActivo());
            ps.setString(3, m.getDosis());
            ps.setDate(4, m.getFechaVencimiento() == null ? null : Date.valueOf(m.getFechaVencimiento()));
            ps.setObject(5, m.getStock(), Types.INTEGER);
            ps.setString(6, m.getObservaciones());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Medicamento m) throws Exception {
        String sql = "UPDATE MEDICAMENTOS SET NOMBRE=?, PRINCIPIO_ACTIVO=?, DOSIS=?, FECHA_VENCIMIENTO=?, STOCK=?, OBSERVACIONES=? WHERE ID=?";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setString(2, m.getPrincipioActivo());
            ps.setString(3, m.getDosis());
            ps.setDate(4, m.getFechaVencimiento() == null ? null : Date.valueOf(m.getFechaVencimiento()));
            ps.setObject(5, m.getStock(), Types.INTEGER);
            ps.setString(6, m.getObservaciones());
            ps.setLong(7, m.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement("DELETE FROM MEDICAMENTOS WHERE ID=?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        }
    }

    private Medicamento map(ResultSet rs) throws SQLException {
        Medicamento m = new Medicamento();
        m.setId(rs.getLong("ID"));
        m.setNombre(rs.getString("NOMBRE"));
        m.setPrincipioActivo(rs.getString("PRINCIPIO_ACTIVO"));
        m.setDosis(rs.getString("DOSIS"));
        Date d = rs.getDate("FECHA_VENCIMIENTO");
        if (d != null) m.setFechaVencimiento(d.toLocalDate());
        m.setStock((Integer) rs.getObject("STOCK"));
        m.setObservaciones(rs.getString("OBSERVACIONES"));
        return m;
    }
}