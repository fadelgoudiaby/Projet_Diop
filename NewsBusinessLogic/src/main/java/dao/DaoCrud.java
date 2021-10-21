/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author pbfall
 */
public interface DaoCrud<T> {
    public T create(T t);
    public T update(T t);
    public boolean delete(Long id);
    public List<T> getAll();
    public T getById(Long id);
}
