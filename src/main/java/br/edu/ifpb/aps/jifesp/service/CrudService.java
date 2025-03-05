package br.edu.ifpb.aps.jifesp.service;

import java.util.List;

public interface CrudService<T, ID> {
    T save(T entity);  // Salva uma entidade e retorna ela
    T update(ID id, T entity);  // Atualiza a entidade com base no ID
    void delete(ID id);  // Deleta uma entidade pelo ID
    List<T> findAll();  // Retorna uma lista de todas as entidades
}
