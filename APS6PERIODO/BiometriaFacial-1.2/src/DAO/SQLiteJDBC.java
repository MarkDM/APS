package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteJDBC {

    Connection c = null;
    Statement stmt = null;
    String mensagemErro;

    public SQLiteJDBC() {
        criarBanco();
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    private void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }

    private void openConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SQLiteJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        c = DriverManager.getConnection("jdbc:sqlite:APS6.db");
    }

    public void closeConnection() throws SQLException {
        if (!c.isClosed()) {
            if (!stmt.isClosed()) {
                stmt.close();
            }

            c.close();
        }
    }

    public boolean criarBanco() {
        try {
            openConnection();
            criarTabelas();
            inserirDadosIniciais();
            closeConnection();
            return true;
        } catch (Exception e) {
            setMensagemErro("Erro ao criar base de dados: " + e.getMessage());
            return false;
        }
    }

    private void criarTabelas() throws SQLException {
        stmt = c.createStatement();
        String sqlTipoUsuario = "CREATE TABLE TIPO_USUARIO ( \n"
                + "TUS_ID INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + "TUS_DESCRICAO TEXT NOT NULL, \n"
                + "TUS_NIVEL_ACESSO INT NOT NULL\n"
                + ")";
        stmt.execute(sqlTipoUsuario);

        String sqlUsuario = "CREATE TABLE USUARIO (\n"
                + "USR_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "USR_NOME TEXT NOT NULL,\n"
                + "USR_SENHA TEXT NOT NULL,\n"
                + "USR_LOGIN TEXT,\n"
                + "USR_TIPO_USUARIO_ID INT NOT NULL,\n"
                + "FOREIGN KEY(USR_TIPO_USUARIO_ID) REFERENCES TIPO_USUARIO(TUS_ID) )";
        stmt.execute(sqlUsuario);

        String sqlImagensTreinamento = "CREATE TABLE IMAGENS_TREINAMENTO (\n"
                + "ITR_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "ITR_CAMINHO TEXT NOT NULL,\n"
                + "ITR_TIPO_IMAGEM CHECK(ITR_TIPO_IMAGEM IN ('raw','bitmap')) NOT NULL,\n"
                + "USR_ID INT NOT NULL,\n"
                + "FOREIGN KEY (USR_ID) REFERENCES USUARIO(USR_ID))";
        stmt.execute(sqlImagensTreinamento);

        stmt.close();
    }

    private void inserirDadosIniciais() throws SQLException {
        stmt = c.createStatement();
        String sql = "INSERT INTO TIPO_USUARIO (TUS_DESCRICAO,TUS_NIVEL_ACESSO) VALUES ('Usuário comum',1),('Diretor de divisão',2),('Ministro do meio Ambiente',3),('Administrador',3)";
        stmt.executeUpdate(sql);
        stmt.close();
    }

    public ResultSet execQuery(String sql) throws SQLException {
        openConnection();
        stmt = c.createStatement();
        ResultSet s = stmt.executeQuery(sql);
        return s;
    }

    public void execUpdateSQL(String sql) throws SQLException {
        openConnection();
        stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        closeConnection();
    }

}
