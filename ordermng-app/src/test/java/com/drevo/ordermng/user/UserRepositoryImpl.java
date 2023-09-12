package com.drevo.ordermng.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, UserEntity> userMap  = new HashMap<String, UserEntity>();

    @Override
    public <S extends UserEntity> S save(S entity) {
        UserEntity user = new UserEntity(entity.getName(), entity.getFullName(), entity.getEmail(), entity.getEmailConfirmed(), entity.getAskedConfimationSinse(), entity.getActive());

        userMap.put(user.getName(), user);

        return (S) user;
    }

    @Override
    public <S extends UserEntity> Iterable<S> saveAll(Iterable<S> entities) {
        List<UserEntity> list = new ArrayList<>();
        
        entities.forEach(s -> list.add(save(s)));

        return (Iterable<S>) list;
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        UserEntity entity = userMap.get(id);

        if(entity != null) {
            return Optional.of(entity);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public boolean existsById(String id) {
        return userMap.get(id) != null;
    }

    @Override
    public Iterable<UserEntity> findAll() {
        return userMap.values();
    }

    @Override
    public Iterable<UserEntity> findAllById(Iterable<String> ids) {
        List<UserEntity> list = new ArrayList<>();

        ids.forEach(s -> { 
            if(userMap.get(s) != null) list.add(userMap.get(s)); 
        });

        return list;
    }

    @Override
    public long count() {
        return userMap.size();
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    @Override
    public void delete(UserEntity entity) {
        userMap.remove(entity.getName());
    }

    @Override
    public void deleteAll(Iterable<? extends UserEntity> entities) {
        entities.forEach(s -> delete(s));
    }

    @Override
    public void deleteAll() {
        userMap.clear();
    }

    @Override
    public Optional<UserEntity> findActiveByName(String name) {
        UserEntity user = userMap.get(name);

        if(user != null && user.getActive()) {
            return Optional.of(user);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Iterable<UserEntity> findAllActive() {
        List<UserEntity> list = new ArrayList<>();
        
        userMap.values().stream().filter(s -> s.getActive()).forEach(s -> list.add(s));

        return list;
    }

    @Override
    public Optional<Integer> checkUserExists(String name) {
        if(userMap.get(name) != null) {
            return Optional.of(1);
        }
        else {
            return Optional.of(0);
        }
    }
}
