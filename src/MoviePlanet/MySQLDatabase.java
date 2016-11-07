package MoviePlanet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLDatabase
{
    private String username;
    private String password;
    private String dbms;
    private Connection connection = null;

    public MySQLDatabase(String username, String password, String dbms)
    {
        this.username = username;
        this.password = password;
        this.dbms = dbms;
    }

    public boolean connect() throws DLException
    {
        try
        {
            connection = DriverManager.getConnection(this.dbms, this.username, this.password);
            return true;
        }
        catch (SQLException e)
        {
            throw setExceptionData(e);
        }

    }

    public boolean close() throws DLException
    {
        try
        {
            connection.close();
            return true;
        }
        catch (SQLException e)
        {
            throw setExceptionData(e);
        }
    }

    public List<String[]> getData(String statement, ArrayList<String> value, boolean included) throws DLException
    {
        return queryDB(prepare(statement, value), included);
    }

    public boolean setData(String statement, ArrayList<String> values) throws DLException
    {
        try
        {
            prepare(statement, values).executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            throw setExceptionData(e);
        }
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setDbms(String dbms)
    {
        this.dbms = dbms;
    }

    private void terminateResultSet(ResultSet rs) throws DLException
    {
        if (rs != null)
        {
            try
            {
                rs.close();
            }
            catch (SQLException e)
            {
                throw setExceptionData(e);
            }
        }
    }

    private void terminateStatement(Statement stmt) throws DLException
    {
        if (stmt != null)
        {
            try
            {
                stmt.close();
            }
            catch (SQLException e)
            {
                throw setExceptionData(e);
            }
        }
    }

    private DLException setExceptionData(SQLException originalException)
    {
        String description = originalException.getMessage();
        String errorCode = Integer.toString(originalException.getErrorCode());
        String stateCode = originalException.getSQLState();
        Throwable causeExec = originalException.getCause();
        ArrayList<String> causeList = new ArrayList<>();
        while (causeExec != null)
        {
            causeList.add(causeExec.toString());
        }
        return new DLException(originalException, causeList, description, errorCode, stateCode);
    }

    private List<String[]> queryDB(PreparedStatement statement, boolean included) throws DLException
    {
        ResultSet rs = null;

        try
        {
            statement.execute();
            rs = statement.getResultSet();

            return getResultSet(rs, included);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            throw setExceptionData(e);
        }
    }

    public List<String[]> getResultSet(ResultSet rs, boolean included) throws DLException
    {
        List<String[]> table = new ArrayList<>();

        try
        {
            ResultSetMetaData metaData = rs.getMetaData();
            int numCols = metaData.getColumnCount();
            if (included)
            {
                String[] columnHeaders = new String[numCols];
                for (int col = 1; col <= numCols; col++)
                {
                    columnHeaders[col - 1] = metaData.getColumnName(col);
                }
                table.add(columnHeaders);
            }

            while (rs.next())
            {
                String[] row = new String[numCols];
                for (int col = 1; col <= numCols; col++)
                {
                    Object obj = rs.getObject(col);
                    row[col - 1] = (obj == null) ? null : obj.toString();
                }
                table.add(row);
            }

            return table;
        }
        catch (SQLException e)
        {
            throw setExceptionData(e);
        }
        finally
        {
            try
            {
                terminateResultSet(rs);
            }
            catch (DLException e)
            {
                System.out.println("Error while trying to close the result set!");
            }
        }
    }

    public PreparedStatement prepare(String statement, ArrayList<String> values) throws DLException
    {
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement = connection.prepareStatement(statement);
            for (int i = 0; i < values.size(); i++)
            {
                preparedStatement.setString(i + 1, values.get(i));
            }

            return preparedStatement;
        }
        catch (SQLException e)
        {
            throw setExceptionData(e);
        }
    }
}
