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

    public List<String[]> getData(String statement) throws DLException
    {
        return queryDB(statement, false);
    }

    public List<String[]> getData(String statement, boolean included) throws DLException
    {
        return queryDB(statement, included);
    }

    public List<String[]> getData(String statement, ArrayList<String> value, boolean included) throws DLException
    {
        return queryDB(prepare(statement, value), included);
    }

    public boolean setData(String statement) throws DLException
    {
        Statement stmt = null;
        int result = -1;
        try
        {
            stmt = connection.createStatement();

            result = stmt.executeUpdate(statement);

        }
        catch (SQLException e)
        {
            throw setExceptionData(e);
        }
        finally
        {
            try
            {
                terminateStatement(stmt);
            }
            catch (DLException e)
            {
                System.out.println("Error while closing the statement!");
            }
        }
        return true;
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

    public int executeProc(String statement, ArrayList<String> values) throws DLException
    {
        CallableStatement callableStatement = null;
        try
        {
            callableStatement = connection.prepareCall(statement);
            for (int i = 0; i < values.size(); i++)
            {
                callableStatement.setString(i + 1, values.get(i));
            }
            callableStatement.registerOutParameter(values.size() + 1, Types.INTEGER);

            callableStatement.execute();

            return callableStatement.getInt(values.size() + 1);

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

    private List<String[]> queryDB(String statement, boolean included) throws DLException
    {
        ResultSet rs = null;
        Statement stmt = null;

        try
        {
            stmt = connection.createStatement();

            rs = stmt.executeQuery(statement);

            return getResultSet(rs, included);

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
        /*finally
        {
            try
            {
                terminateResultSet(rs);
            }
            catch (DLException e)
            {
                System.out.println("Error while trying to close the result set!");
            }
        }*/
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

    public void printDBInfo() throws DLException
    {
        if (connection != null)
        {
            try
            {
                DatabaseMetaData dbMetaData = connection.getMetaData();

                String productName = dbMetaData.getDatabaseProductName();
                String productVersion = dbMetaData.getDatabaseProductVersion();
                String driverName = dbMetaData.getDriverName();
                String driverVersion = dbMetaData.getDriverVersion();
                System.out.println("Database Info:");
                System.out.println("Product name: " + productName + ", Product version: " + productVersion);
                System.out.println("Driver name: " + driverName + ", Driver version: " + driverVersion);

                ResultSet rs = dbMetaData.getTables(null, null, null, null);
                while (rs.next())
                {
                    System.out.println("Table name: " + rs.getString(3) + ", Table types: " + rs.getString(4));
                }
                System.out.println("Supports 'Group by': " + dbMetaData.supportsGroupBy());
                System.out.println("Supports 'Outer join': " + dbMetaData.supportsOuterJoins() + "\n");
            }
            catch (SQLException e)
            {
                throw setExceptionData(e);
            }
        }
        else
        {
            System.out.println("Not connected to a database!");
        }
    }

    public void printTableInfo(String tableName) throws DLException
    {
        if (connection != null)
        {
            try
            {
                DatabaseMetaData dbMetaData = connection.getMetaData();

                ResultSet rs = dbMetaData.getTables(null, null, tableName, null);

                ResultSetMetaData tableMetaData = rs.getMetaData();
                int numCols = tableMetaData.getColumnCount();

                ResultSet columnResults = dbMetaData.getColumns(null, null, tableName, null);
                System.out.println(tableName + " info: ");
                System.out.println("Column count: " + numCols);
                System.out.println("Column names: ");
                while (columnResults.next())
                {
                    System.out.println("Name: " + columnResults.getString(4) + ", type: " + columnResults.getInt(5));
                }

                ResultSet primaryKeys = dbMetaData.getPrimaryKeys(null, null, tableName);
                System.out.println("Primary keys: ");
                while (primaryKeys.next())
                {
                    System.out.println(primaryKeys.getString(4));
                }
                System.out.println();
            }
            catch (SQLException e)
            {
                throw setExceptionData(e);
            }
        }
        else
        {
            System.out.println("Not connected to a database!");
        }
    }

    public void printResultSetInfo(String query) throws DLException
    {
        try
        {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData queryMetaData = rs.getMetaData();
            int colNum = queryMetaData.getColumnCount();
            System.out.println("Query: " + query);
            System.out.println("Column count: " + colNum);
            for (int i = 1; i <= colNum; i++)
            {
                System.out.println("Column name: " + queryMetaData.getColumnName(i) + ", Column Type: " +
                        queryMetaData.getColumnTypeName(i) + ", Searchable: " + queryMetaData.isSearchable(i));
            }
            System.out.println();
        }
        catch (SQLException e)
        {
            throw setExceptionData(e);
        }
    }
}
