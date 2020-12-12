package pet.photography.service;


public abstract class BaseService<T> {

    public abstract T getById(String id);

}
