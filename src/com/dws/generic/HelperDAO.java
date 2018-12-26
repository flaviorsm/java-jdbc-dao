package com.dws.generic;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import com.dws.resources.CRUD;

public abstract class HelperDAO<T> extends Validation implements CRUD<T> {
			
	protected abstract void openConnection();
	protected abstract Optional<T> putEntity(ResultSet rs);
	
	private ResultSet rs;	
	private Connection connection;
	
	private Optional<T> result;
	private List<Optional<T>> list;
	
	private void setResult(Optional<T> result) {
		this.result = result;
	}
	
	private void addList(Optional<T> entity) {
		this.list.add(entity);
	}
	
	protected List<Optional<T>> getList() {
		return isValid ? list : null;
	}
	
	protected Optional<T> getUnique() {
		return isValid ? result : null;
	}
	
	protected void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	protected void execute(String sql) {		
		PreparedStatement ps;
		try {
			openConnection();
			if(!this.connection.isClosed()) {
				ps = this.connection.prepareStatement(sql);
				ps.execute();
			}			
			else {
				hasError("Conexão fechada!");
			}
		} catch (Exception e) {
			hasError(e);		
		}     
		finally {
			closeConnetion();
		}		
	}
	
	protected void executeUnique(String sql) {
		executeQuery(sql);
		setResult(getList().get(0));
	}
	
	protected void executeQuery(String sql) {	
		PreparedStatement ps;
		try {
			openConnection();
			if(!connection.isClosed()) {
				ps = connection.prepareStatement(sql);
				rs = ps.executeQuery();		
				while(rs.next()) {				
					addList(putEntity(rs));				
				}				
				if(list.isEmpty()) {
					hasError("Nenhum valor encontrado!");
				}
			}
			else {
				hasError("Conexão fechada");
			}
		}
		catch(Exception e) {
			hasError(e);
		}
		finally {
			closeConnetion();
		}		
	}
	
	private void closeConnetion() {
		try {
			if(!connection.isClosed())
				connection.close();
		} catch (Exception e) {
			hasError(e);
		}
	}
}
