package com.clinic.common.datarec;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class VConnection implements Connection {

	private Connection conn;
	private boolean trConn;

	public VConnection() {
		// TODO Auto-generated constructor stub
		conn = null;
		trConn = false;
	}

	public VConnection(Connection iconn, boolean itrConn) {
		// TODO Auto-generated constructor stub
		conn = iconn;
		trConn = itrConn;
	}

	@Override
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		// TODO Auto-generated method stub
		conn.abort(executor);

	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub
		
		conn.clearWarnings();

	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		if(!trConn) {conn.close();}

	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub

		conn.commit();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		// TODO Auto-generated method stub
		return conn.createArrayOf(typeName, elements);
	}

	@Override
	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return conn.createBlob();
	}

	@Override
	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return conn.createClob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return conn.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return conn.createSQLXML();
	}

	@Override
	public Statement createStatement() throws SQLException {
		// TODO Auto-generated method stub
		return conn.createStatement();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		// TODO Auto-generated method stub
		return conn.createStatement(resultSetType, resultSetConcurrency);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		// TODO Auto-generated method stub
		return conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		// TODO Auto-generated method stub
		return conn.createStruct(typeName, attributes);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getAutoCommit();
	}

	@Override
	public String getCatalog() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getCatalog();
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getClientInfo();
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		// TODO Auto-generated method stub
		return conn.getClientInfo(name);
	}

	@Override
	public int getHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getHoldability();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getMetaData();
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getNetworkTimeout();
	}

	@Override
	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getSchema();
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getTransactionIsolation();
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getTypeMap();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		return conn.getWarnings();
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return conn.isClosed();
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		// TODO Auto-generated method stub
		return conn.isReadOnly();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return conn.isValid(timeout);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return conn.nativeSQL(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareCall(sql);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareStatement(sql);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareStatement(sql, columnNames);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
			throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
			int resultSetHoldability) throws SQLException {
		// TODO Auto-generated method stub
		return conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		// TODO Auto-generated method stub
		conn.releaseSavepoint(savepoint);
	}

	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub
		conn.rollback();
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		// TODO Auto-generated method stub
		conn.rollback(savepoint);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		// TODO Auto-generated method stub
		conn.setAutoCommit(autoCommit);
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		// TODO Auto-generated method stub
		conn.setCatalog(catalog);
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		conn.setClientInfo(properties);
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		// TODO Auto-generated method stub
		conn.setClientInfo(name, value);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		// TODO Auto-generated method stub
		conn.setHoldability(holdability);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		// TODO Auto-generated method stub
		conn.setNetworkTimeout(executor, milliseconds);
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		// TODO Auto-generated method stub
		conn.setReadOnly(readOnly);
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		// TODO Auto-generated method stub
		return conn.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		// TODO Auto-generated method stub
		return conn.setSavepoint(name);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		// TODO Auto-generated method stub
		conn.setSchema(schema);
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		// TODO Auto-generated method stub
		conn.setTransactionIsolation(level);
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		// TODO Auto-generated method stub
		conn.setTypeMap(map);
	}

	public boolean trConn() {
		return trConn;
	}
	public void trConn( boolean itrConn) {
		trConn = itrConn;
	}
}
