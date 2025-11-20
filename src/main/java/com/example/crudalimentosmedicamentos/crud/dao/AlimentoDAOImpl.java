package com.example.crudalimentosmedicamentos.crud.dao;

import com.example.crudalimentosmedicamentos.crud.model.Alimento;
import com.example.crudalimentosmedicamentos.crud.util.DbUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlimentoDAOImpl implements AlimentoDAO {
    @Override
    public Alimento findById(Long id) throws Exception {
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT * FROM AlIMENTOS WHERE id = ?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
            }
        }
        return null;
    }

    @Override
    public List<Alimento> findAll() throws Exception {
        List<Alimento> list = new ArrayList<>();
        try (Connection c = DbUtil.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM AlIMENTOS ORDER BY ID")) {
            while (rs.next()) list.add(map(rs));
        }
        return list;
    }

    @Override
    public void insert(Alimento a) throws Exception {
        String sql = "INSERT INTO ALIMENTOS (NOMBRE, CATEGORIA, FECHA_VENCIMIENTO, STOCK, OBSERVACIONES) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
           ps.setString(1, a.getNombre());
           ps.setString(2, a.getCategoria());
           ps.setDate(3, a.getFechaVencimiento() == null ? null : Date.valueOf(a.getFechaVencimiento()));
           ps.setObject(4, a.getStock(), Types.INTEGER);
           ps.setString(5, a.getObservaciones());
           ps.executeUpdate();
        }
    }

    @Override
    public void update(Alimento a) throws Exception {
        String sql = "UPDATE ALIMENTOS SET NOMBRE=?, CATEGORIA=?, FECHA_VENCIMIENTO=?, STOCK=?, OBSERVACIONES=? WHERE ID=?";
        try (Connection c = DbUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getCategoria());
            ps.setDate(3, a.getFechaVencimiento() == null ? null : Date.valueOf(a.getFechaVencimiento()));
            ps.setObject(4, a.getStock(), Types.INTEGER);
            ps.setString(5, a.getObservaciones());
            ps.setLong(6, a.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try (Connection c = DbUtil.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM ALIMENTOS WHERE ID = ?")) {
             ps.setLong(1, id);
             ps.executeUpdate();
        }
    }

    private Alimento map(ResultSet rs) throws SQLException {
        Alimento a = new Alimento();
        a.setId(rs.getLong("ID"));
        a.setNombre(rs.getString("NOMBRE"));
        a.setCategoria(rs.getString("CATEGORIA"));
        Date d = rs.getDate("FECHA_VENCIMIENTO");
        if (d != null) a.setFechaVencimiento(d.toLocalDate());
        a.setStock((Integer) rs.getObject("STOCK"));
        a.setObservaciones(rs.getString("OBSERVACIONES"));
        return a;
    }
}
