/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.FileTrain;
import Model.ImagemTreinamento;
import Model.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marcu
 */
public class ImagensTreinamentoDAO extends SQLiteJDBC {
    
    public void inserirImagem(ImagemTreinamento img) throws SQLException {
        
        String sql = "INSERT INTO IMAGENS_TREINAMENTO (ITR_CAMINHO,ITR_TIPO_IMAGEM,USR_ID) VALUES (\n"
                + "'" + img.getCaminho() + "','raw'," + img.getUsuario().getId() + ")";
        
        execUpdateSQL(sql);
    }
    
    public List<ImagemTreinamento> getImagensByUsuario(int id) throws SQLException {
        String sql = "SELECT * FROM IMAGENS_TREINAMENTO WHERE USR_ID = " + id;
        List<ImagemTreinamento> l = new ArrayList<>();
        ResultSet rs = execQuery(sql);
        
        while (rs.next()) {
            ImagemTreinamento i = new ImagemTreinamento();
            i.setCaminho(rs.getString("ITR_CAMINHO"));
            i.setId(rs.getInt("ITR_ID"));
            i.setTipo(rs.getString("ITR_TIPO"));
            i.setUsuario(new UsuarioDAO().getUsuarioById(rs.getInt("USR_ID")));
            
            l.add(i);
        }
        
        return l;
    }
    
    public void deleteByUsuario(int id) throws SQLException {
        String sql = "DELETE FROM IMAGENS_TREINAMENTO WHERE USR_ID = " + id;
        execQuery(sql);
    }
    
}
