package com.chatcode.repository;

public interface WriteRepository<T> {
  T save(T entity);
}