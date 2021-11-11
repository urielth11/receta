package com.cibertec.service.espec;

import java.util.List;

public interface CrudServ<T> {
	public abstract List<T> list();
	public abstract T save(T obj);
	public abstract T find(int id);
	public abstract void delete(int id);
}
