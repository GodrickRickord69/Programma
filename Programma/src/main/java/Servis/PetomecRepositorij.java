package Servis;

import Modeli.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PetomecRepositorij implements Repositorij<Petomec>{

    private Creator petCreator;
    private Statement sqlST;
    private ResultSet resultSet;
    private String SQLstr;

    public PetomecRepositorij(){
        this.petCreator = new PetomecCreator();
    };

    @Override
    public List<Petomec> getAll(){
        List<Petomec> farm = new ArrayList<Petomec>();
        Petomec petomec;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()){
                sqlST = dbConnection.createStatement();
                SQLstr = "SELECT Tipe_Id, Id, Petomec_Name, Birthday FROM pet_lis ORDER BY Id";
                resultSet = sqlST.executeQuery(SQLstr);
                while (resultSet.next()) {

                    PetomecTipe tipe = PetomecTipe.getTipe(resultSet.getInt(1));
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();

                    petomec = petCreator.createPetomec(tipe, name, birthday);
                    petomec.setPetomecId(id);
                    farm.add(petomec);
                }
                return farm;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex){
            Logger.getLogger(PetomecRepositorij.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public Petomec getById(int petomecId){
        Petomec petomec = null;
        try{
            Class.forName("");
            try (Connection dbConnection = getConnection()){

                SQLstr = "SELECT Tipe_Id, Id, PetName, Birtday FROM pet_list WHERE Id = ?";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(SQLstr);
                preparedStatement.setInt(1, petomecId);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){

                    PetomecTipe tipe = PetomecTipe.getTipe(resultSet.getInt(1));
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    LocalDate birthday = resultSet.getDate(4).toLocalDate();

                    petomec = petCreator.createPetomec(tipe, name, birthday);
                    petomec.setPetomecId(id);
                }
                return petomec;
            }
        }catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetomecRepositorij.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public int create(Petomec petomec){

        int rows;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()){

                SQLstr = "INSERT INTO petomec_command (PetomecId, CommandId) SELECT ?, (SELECT Id FROM commands WHERE Command_name = ?)";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(SQLstr);
                preparedStatement.setString(1, petomec.getName());
                preparedStatement.setDate(2, Date.valueOf(petomec.getBirthday()));
                preparedStatement.setString(3, petomec.getClass().getSimpleName());

                rows = preparedStatement.executeUpdate();
                return rows;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetomecRepositorij.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void train (int id, String command){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                String SQLstr = "INSERT INTO petomec_command (PetomecId, CommandId) SELECT ?, (SELECT Id FROM commands WHERE Command_name = ?)";
                PreparedStatement preparedStatement = dbConnection.preparedStatement(SQLstr);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, command);

                preparedStatement.executeUpdate();
            }
        }catch (ClassNotFoundException | IOException | SQLException ex){
            Logger.getLogger(PetomecRepositorij.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<String> getCommandsById (int petomecId, int commands_tipe) {

        List <String> commands = new ArrayList <>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                if (commands_tipe == 1) {
                    SQLstr = "SELECT Command_name FROM pet_command pc JOIN commands c ON pc.CommandId = c.Id WHERE pc.PetId = ?";
                } else {
                    SQLstr = "SELECT Command_name FROM commands c JOIN Tipe_command gc ON c.Id = gc.CommandId WHERE gc.Tipe_Id = (SELECT GenusId FROM pet_list WHERE Id = ?)";
                }
                PreparedStatement preparedStatement = dbConnection.prepareStatement(SQLstr);
                preparedStatement.setInt(1, petomecId);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    commands.add(resultSet.getString(1));
                }
                return commands;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetomecRepositorij.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public int update (Petomec petomec){
        int rows;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()){
                SQLstr = "UPDATE petomec_list SET PetomecName = ?, Birthday = ? WHERE Id = ?";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(SQLstr);

                preparedStatement.setString(1, petomec.getName());
                preparedStatement.setDate(2, Date.valueOf(petomec.getBirthdayDate()));
                preparedStatement.setInt(3,petomec.getPetomecId());

                rows = preparedStatement.executeUpdate();
                return rows;
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetomecRepositorij.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete (int id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection dbConnection = getConnection()) {
                SQLstr = "DELETE FROM pet_list WHERE Id = ?";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(SQLstr);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (ClassNotFoundException | IOException | SQLException ex) {
            Logger.getLogger(PetomecRepositorij.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException, IOException{

        Properties properties = new Properties();
        try(FileInputStream fil = new FileInputStream("src/Resursy/database.properties")) {

            properties.load(fil);
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            return DriverManager.getConnection(url, username, password);

        }
    }

}
