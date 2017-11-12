package DAO;

import java.io.File;
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

        //Banco ja esta criado
        if (new File("APS6.db").exists()) {
            System.out.println("Banco ja criado");
            return true;
        }

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

        String sqlPropriedade = "CREATE TABLE PROPRIEDADE (\n"
                + "       PRP_ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "       PRP_DESCRICAO TEXT NOT NULL,\n"
                + "       PRP_PROPRIETARIO TEXT NOT NULL,\n"
                + "       PRP_AGROTOXICOS TEXT NOT NULL)";

        stmt.execute(sqlPropriedade);

        stmt.close();
    }

    private void inserirDadosIniciais() throws SQLException {
        stmt = c.createStatement();
        String sqlTipoUsuario = "INSERT INTO TIPO_USUARIO (TUS_DESCRICAO,TUS_NIVEL_ACESSO) VALUES ('Usuário comum',1),('Diretor de divisão',2),('Ministro do meio Ambiente',3),('Administrador',4)";
        stmt.executeUpdate(sqlTipoUsuario);
        String sqlAdmin = "INSERT INTO USUARIO (USR_NOME,USR_LOGIN,USR_SENHA,USR_TIPO_USUARIO_ID) VALUES ('Mr.President','adm','c4ca4238a0b923820dcc509a6f75849b',4)";
        stmt.executeUpdate(sqlAdmin);
        String sqlPropriedade = "INSERT INTO PROPRIEDADE (PRP_DESCRICAO,PRP_PROPRIETARIO,PRP_AGROTOXICOS)\n"
                + "VALUES ('Fazenda Santa Clara','Marcos Douglas','AAA111')";
        stmt.executeUpdate(sqlPropriedade);

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
